package com.jpmorgan.coding.challenge;

import com.jpmorgan.coding.challenge.controller.SettlementMessageController;
import com.jpmorgan.coding.challenge.repository.StandardSettlementInstructionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private SettlementMessageController controller;

    @Autowired
    private StandardSettlementInstructionRepository instructionRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
        assertEquals(5, instructionRepository.findAll().stream().count());
    }
}
