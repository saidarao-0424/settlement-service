package com.jpmorgan.coding.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CounterParty implements Serializable {

    private static final long serialVersionUID = -6124300845351191894L;
    private String accountNumber;

    private String bankCode;

    @Override
    public String toString() {
        return "CounterParty{" +
                "accountNumber='" + accountNumber + '\'' +
                ", bankCode='" + bankCode + '\'' +
                '}';
    }
}
