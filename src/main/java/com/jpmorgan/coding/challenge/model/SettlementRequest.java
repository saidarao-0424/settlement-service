package com.jpmorgan.coding.challenge.model;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
public class SettlementRequest implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Trade ID is required")
    private long tradeId;

    @NotNull(message = "SSI Code is required")
    private String ssiCode;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "2",message = "Amount must be 2 decimals atleast")
    private BigDecimal amount;

    @NotNull(message = "Currency is required")
    private String currency;

    @NotNull(message = "Value Date is required")
    private String valueDate;

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        SettlementRequest that = (SettlementRequest) other;
        return tradeId == that.tradeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeId);
    }

    @Override
    public String toString() {
        return "SettlementRequest [\n"
                + " tradeId=" + tradeId + ", \n"
                + "\t ssiCode=" + ssiCode + ", \n"
                + "\t amount=" + amount + ", \n"
                + "\t currency=" + currency + ", \n"
                + "\t valueDate=" + valueDate + ", \t]";
    }

    @Override
    public SettlementRequest clone() {
        SettlementRequest settlementRequest = new SettlementRequest();
        settlementRequest.setTradeId(this.tradeId);
        settlementRequest.setAmount(this.amount);
        settlementRequest.setCurrency(this.currency);
        settlementRequest.setValueDate(this.valueDate);
        return settlementRequest;
    }

}
