package com.ojasare.dto;

import com.ojasare.entity.FinancialTransaction;

public class PaymentResponse {

    private Long id;
    private FinancialTransaction financialTransaction;

    public PaymentResponse() {}

    public PaymentResponse(Long id, FinancialTransaction financialTransaction) {
        this.id = id;
        this.financialTransaction = financialTransaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FinancialTransaction getFinancialTransaction() {
        return financialTransaction;
    }

    public void setFinancialTransaction(FinancialTransaction financialTransaction) {
        this.financialTransaction = financialTransaction;
    }
}
