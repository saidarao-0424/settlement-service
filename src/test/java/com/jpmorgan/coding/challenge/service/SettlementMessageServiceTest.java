package com.jpmorgan.coding.challenge.service;

import com.jpmorgan.coding.challenge.advice.ResourceNotFoundException;
import com.jpmorgan.coding.challenge.controller.util.TestUtil;
import com.jpmorgan.coding.challenge.model.SettlementRequest;
import com.jpmorgan.coding.challenge.model.SettlementResponse;
import com.jpmorgan.coding.challenge.repository.SettlementResponseRepository;
import com.jpmorgan.coding.challenge.repository.StandardSettlementInstructionRepository;
import com.jpmorgan.coding.challenge.service.impl.SettlementMessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SettlementMessageServiceImpl.class})
public class SettlementMessageServiceTest {

    @Autowired
    private SettlementMessageService service;

    @MockBean
    private SettlementResponseRepository repository;

    @MockBean
    private StandardSettlementInstructionRepository instructionRepository;

    @Test
    public void createSettlementMessageOk() {
        SettlementRequest request = TestUtil.mockSettlementRequest();
        Mockito.when(instructionRepository.findBySsiCode("OCBC_DBS_1")).thenReturn(Optional.of(TestUtil.mockInstruction("OCBC_DBS_1")));
        SettlementResponse response = service.createSettlementMessage(request);
        assertNotNull(response);
        assertEquals(request.getTradeId(), response.getTradeId());
        assertEquals(request.getValueDate(), response.getValueDate());
        assertNotNull(response.getPayerParty());
        assertNotNull(response.getReceiverParty());
        assertEquals("Dummy-1", response.getPayerParty().getAccountNumber());
    }

    @Test
    public void createSettlementMessageKo() {
        SettlementRequest request = TestUtil.mockSettlementRequest();
        Mockito.when(instructionRepository.findBySsiCode("OCBC_DBS_1")).thenReturn(Optional.ofNullable(null));
        assertThrows(ResourceNotFoundException.class, () -> service.createSettlementMessage(request));
    }

    @Test
    public void findSettlementMessageOk() {
        Mockito.when(repository.findByTradeId(16846548)).thenReturn(Optional.of(TestUtil.mockResponse(16846548)));
        SettlementResponse response = service.findSettlementMessage(16846548);
        assertNotNull(response);
        assertEquals(16846548, response.getTradeId());
        assertEquals("Dummy-5", response.getSupportingInformation());
    }

    @Test
    public void findSettlementMessageKo() {
        Mockito.when(repository.findByTradeId(16846548)).thenReturn(Optional.ofNullable(null));
        assertThrows(ResourceNotFoundException.class, () -> service.findSettlementMessage(16846548));
    }
}
