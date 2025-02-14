package com.ojasare.entity;

public class Payment {

    private Long id;
    private FinancialTransaction financialTransaction;

    public Payment() {}

    public Payment(Long id, FinancialTransaction financialTransaction) {
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
