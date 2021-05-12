package com.jpmorgan.coding.challenge.util;

import com.jpmorgan.coding.challenge.model.StandardSettlementInstruction;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CsvUtil {

    public static <T> List<T> read(Class<StandardSettlementInstruction> t, Resource resource) {

        try (Reader reader = Files.newBufferedReader(resource.getFile().toPath());) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(t)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();

        } catch (IOException ex) {
            log.error("Error while reading CSV ", ex);
        }
        return Collections.emptyList();
    }
}
