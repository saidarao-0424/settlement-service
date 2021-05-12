package com.jpmorgan.coding.challenge.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SETTLEMENT_INSTRUCTIONS")
@Data
public class StandardSettlementInstruction implements Serializable {

    private static final long serialVersionUID = -4637069285091004301L;

    @Id
    @Column(name = "SSI_CODE")
    @CsvBindByName(column = "SSI Code")
    private String ssiCode;

    @Column(name = "PAYER_ACC_NUM")
    @CsvBindByName(column = "Payer Account Number")
    private String payerAccNumber;

    @Column(name = "PAYER_BANK")
    @CsvBindByName(column = "Payer Bank")
    private String payerBank;

    @Column(name = "RECEIVER_ACC_NUM")
    @CsvBindByName(column = "Receiver Account Number")
    private String receiverAccNumber;

    @Column(name = "RECEIVER_BANK")
    @CsvBindByName(column = "Receiver Bank")
    private String receiverBank;

    @Column(name = "SUPPORTING_INFO")
    @CsvBindByName(column = "Supporting Information")
    private String supportingInformation;

    @Override
    public String toString() {
        return "StandardSettlementInstruction [\n"
                + " ssiCode=" + ssiCode + ", \n"
                + "\t payerAccNumber=" + payerAccNumber + ", \n"
                + "\t payerBank=" + payerBank + ", \n"
                + "\t receiverAccNumber=" + receiverAccNumber + ", \n"
                + "\t receiverBank=" + receiverBank + ", \n"
                + "\t supportingInformation=" + supportingInformation + ", \t]";
    }
}
