package com.jpmorgan.coding.challenge.repository;

import com.jpmorgan.coding.challenge.model.SettlementResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettlementResponseRepository extends JpaRepository<SettlementResponse,String> {

    Optional<SettlementResponse> findByTradeId(long tradeId);
}
