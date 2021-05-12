package com.jpmorgan.coding.challenge.repository;

import com.jpmorgan.coding.challenge.model.StandardSettlementInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StandardSettlementInstructionRepository extends JpaRepository<StandardSettlementInstruction, String> {

    Optional<StandardSettlementInstruction> findBySsiCode(String ssiCode);
}
