package com.jpmorgan.coding.challenge.service.impl;

import com.jpmorgan.coding.challenge.advice.ResourceNotFoundException;
import com.jpmorgan.coding.challenge.model.CounterParty;
import com.jpmorgan.coding.challenge.model.SettlementRequest;
import com.jpmorgan.coding.challenge.model.SettlementResponse;
import com.jpmorgan.coding.challenge.model.StandardSettlementInstruction;
import com.jpmorgan.coding.challenge.repository.SettlementResponseRepository;
import com.jpmorgan.coding.challenge.repository.StandardSettlementInstructionRepository;
import com.jpmorgan.coding.challenge.service.SettlementMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettlementMessageServiceImpl implements SettlementMessageService {

    @Autowired
    private SettlementResponseRepository repository;

    @Autowired
    private StandardSettlementInstructionRepository instructionRepository;

    @Override
    public SettlementResponse createSettlementMessage(final SettlementRequest settlementRequest) {
        Optional<StandardSettlementInstruction> instruction = instructionRepository.findBySsiCode(settlementRequest.getSsiCode());
        return instruction.map(ins -> createAndEnrichSettlementMessage(ins, settlementRequest)).orElseThrow(() -> new ResourceNotFoundException("SSI Code is not found"));
    }

    private SettlementResponse createAndEnrichSettlementMessage(StandardSettlementInstruction instruction, SettlementRequest request) {
        final SettlementResponse response = new SettlementResponse();
        response.setAmount(request.getAmount());
        response.setTradeId(request.getTradeId());
        response.setCurrency(request.getCurrency());
        response.setValueDate(request.getValueDate());
        response.setPayerParty(new CounterParty(instruction.getPayerAccNumber(), instruction.getPayerBank()));
        response.setReceiverParty(new CounterParty(instruction.getReceiverAccNumber(), instruction.getReceiverBank()));
        response.setSupportingInformation(instruction.getSupportingInformation());
        repository.save(response);
        return response;
    }


    @Override
    public SettlementResponse findSettlementMessage(long tradeId) {
        Optional<SettlementResponse>  response  = repository.findByTradeId(tradeId);
        return response.map(res -> res).orElseThrow(() -> new ResourceNotFoundException("Market Settlement Message not found for given Trade Id"));
    }
}
