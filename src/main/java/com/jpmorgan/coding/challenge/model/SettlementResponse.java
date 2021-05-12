package com.jpmorgan.coding.challenge.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "SETTLEMENT_RESPONSE")
@Data
public class SettlementResponse implements Serializable {

    private static final long serialVersionUID = -4637069285091004301L;

    @Id
    @Column(name = "MSG_ID")
    private final String messageId;

    @Column(name = "TRADE_ID",unique = true, updatable = false)
    private long tradeId;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "VALUE_DATE")
    private String valueDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "accountNumber", column = @Column(name = "PAYER_ACC_NUM")),
            @AttributeOverride( name = "bankCode", column = @Column(name = "PAYER_BANK_CODE")),
    })
    private CounterParty payerParty;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "accountNumber", column = @Column(name = "RECEIVER_ACC_NUM")),
            @AttributeOverride( name = "bankCode", column = @Column(name = "RECEIVER_BANK_CODE")),
    })
    private CounterParty receiverParty;

    @Column(name = "SUPPORTING_INFO")
    private String supportingInformation;

    public SettlementResponse() {
        this.messageId = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "SettlementResponse [\n"
                + " tradeId=" + tradeId + ", \n"
                + "\t messageId=" + messageId + ", \n"
                + "\t amount=" + amount + ", \n"
                + "\t currency=" + currency + ", \n"
                + "\t payerParty=" + payerParty + ", \n"
                + "\t receiverParty=" + receiverParty + ", \n"
                + "\t supportingInformation=" + supportingInformation + ", \n"
                + "\t valueDate=" + valueDate + ", \t]";
    }

}
