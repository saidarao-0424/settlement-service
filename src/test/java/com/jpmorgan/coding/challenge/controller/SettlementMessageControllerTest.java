package com.jpmorgan.coding.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmorgan.coding.challenge.advice.ResourceNotFoundException;
import com.jpmorgan.coding.challenge.controller.util.TestUtil;
import com.jpmorgan.coding.challenge.model.SettlementRequest;
import com.jpmorgan.coding.challenge.model.SettlementResponse;
import com.jpmorgan.coding.challenge.service.SettlementMessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class SettlementMessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SettlementMessageService service;


    @Test
    public void findSettlementMessageOk() throws Exception {
        SettlementResponse response = TestUtil.mockResponse(16846548);
        Mockito.when(service.findSettlementMessage(16846548)).thenReturn(response);

        this.mockMvc.perform(get("/api/v1/settlements/find/" + 16846548))
                .andExpect(status().isOk())
                .andExpect(jsonPath("tradeId").value(response.getTradeId()));
    }
    @Test
    public void findSettlementMessageKo() throws Exception {
        SettlementResponse response = TestUtil.mockResponse(16846548);
        Mockito.when(service.findSettlementMessage(16846548)).thenThrow(new ResourceNotFoundException("Trade Id is Invalid"));

        this.mockMvc.perform(get("/api/v1/settlements/find/" + 16846548))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createSettlementMessageOk() throws Exception {

        SettlementRequest request = TestUtil.mockSettlementRequest();
        Mockito.when(service.createSettlementMessage(request)).thenReturn(TestUtil.mockResponse(16846548));

        mockMvc.perform(post("/api/v1/settlements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("tradeId").value(request.getTradeId()));
    }

    @Test
    public void createSettlementMessageKo() throws Exception {

        SettlementRequest request = TestUtil.mockSettlementRequest();
        Mockito.when(service.createSettlementMessage(request)).thenThrow(new ResourceNotFoundException("SSI Code is not found"));

        mockMvc.perform(post("/api/v1/settlements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }


}
