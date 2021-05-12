package com.jpmorgan.coding.challenge.service;

import com.jpmorgan.coding.challenge.model.SettlementRequest;
import com.jpmorgan.coding.challenge.model.SettlementResponse;

public interface SettlementMessageService {

    public SettlementResponse createSettlementMessage(final SettlementRequest settlementRequest);

    public SettlementResponse findSettlementMessage(long tradeId);

}
