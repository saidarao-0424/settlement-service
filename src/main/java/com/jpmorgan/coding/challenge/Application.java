package com.jpmorgan.coding.challenge;

import com.jpmorgan.coding.challenge.model.StandardSettlementInstruction;
import com.jpmorgan.coding.challenge.repository.StandardSettlementInstructionRepository;
import com.jpmorgan.coding.challenge.util.CsvUtil;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Application {

    private static final String SSI_PATH = "data/ssi.csv";

    @Value("classpath:data/ssi.csv")
    Resource resource;

    @Autowired
    private StandardSettlementInstructionRepository instructionRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadStandardSettlementInstructions() {
        List<StandardSettlementInstruction> list = CsvUtil.read(StandardSettlementInstruction.class, resource);
        log.info("Standard Settlement Instructions are loaded \n {} ", list);
        instructionRepository.saveAll(list);
        log.info(" Standard Settlement Instructions Count | {}  ", list.size());
    }

    @Bean
    public OpenAPI settlementServiceAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Settlement Message API").description(
                        "this API is capable of receiving trade requests, performing the necessary enrichment using SSI reference data and returning the corresponding market settlement messages."));
    }
}
