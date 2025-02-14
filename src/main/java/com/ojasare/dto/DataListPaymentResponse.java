package com.ojasare.dto;

import com.ojasare.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.util.List;

public class DataListPaymentResponse {

    private List<Payment> payments;
    private List<Link> links;

    public DataListPaymentResponse() {}

    public DataListPaymentResponse(List<Payment> payments, List<Link> links) {
        this.payments = payments;
        this.links = links;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}
