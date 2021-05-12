package com.jpmorgan.coding.challenge.controller;

import com.jpmorgan.coding.challenge.api.SettlementMessageServiceApi;
import com.jpmorgan.coding.challenge.model.SettlementRequest;
import com.jpmorgan.coding.challenge.model.SettlementResponse;
import com.jpmorgan.coding.challenge.service.SettlementMessageService;
import com.jpmorgan.coding.challenge.util.ResponseEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettlementMessageController implements SettlementMessageServiceApi {

    @Autowired
    private SettlementMessageService service;

    @Override
    public ResponseEntity<SettlementResponse> createSettlementMessage(SettlementRequest settlementRequest) {
        final SettlementResponse settlementResponse = this.service.createSettlementMessage(settlementRequest);
        return ResponseEntityUtil.created("/find/{tradeId}", settlementResponse.getTradeId(), settlementResponse);
    }

    @Override
    public ResponseEntity<SettlementResponse> findSettlementMessage(long tradeId) {
        final SettlementResponse settlementResponse = this.service.findSettlementMessage(tradeId);
        return ResponseEntity.ok(settlementResponse);
    }

}
