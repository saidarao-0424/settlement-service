package com.jpmorgan.coding.challenge.controller.util;

import com.jpmorgan.coding.challenge.model.SettlementRequest;
import com.jpmorgan.coding.challenge.model.SettlementResponse;
import com.jpmorgan.coding.challenge.model.StandardSettlementInstruction;

import java.math.BigDecimal;

public class TestUtil {

    public static SettlementRequest mockSettlementRequest() {
        SettlementRequest request = new SettlementRequest();
        request.setTradeId(16846548);
        request.setSsiCode("OCBC_DBS_1");
        request.setAmount(new BigDecimal(12894.65));
        request.setCurrency("USD");
        request.setValueDate("20022020");
        return request;
    }

    public static StandardSettlementInstruction mockInstruction(String ssiCode) {

        StandardSettlementInstruction instruction = new StandardSettlementInstruction();
        instruction.setSsiCode(ssiCode);
        instruction.setPayerAccNumber("Dummy-1");
        instruction.setPayerBank("Dummy-2");
        instruction.setReceiverBank("Dummy-3");
        instruction.setReceiverAccNumber("Dummy-4");
        instruction.setSupportingInformation("Dummy-5");

        return instruction;
    }

    public static SettlementResponse mockResponse(long tradeId) {

        SettlementResponse response = new SettlementResponse();
        response.setTradeId(tradeId);
        response.setAmount(new BigDecimal(12894.65));
        response.setCurrency("USD");
        response.setValueDate("20022020");
        response.setSupportingInformation("Dummy-5");
        return response;
    }
}