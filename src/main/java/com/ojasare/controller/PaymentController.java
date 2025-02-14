package com.ojasare.controller;

import com.ojasare.dto.DataListPaymentResponse;
import com.ojasare.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/filter")
    public Mono<ResponseEntity<DataListPaymentResponse>> getFilteredPayments(
            @RequestParam LocalDateTime dateFrom,
            @RequestParam LocalDateTime dateTo,
            @RequestParam Long userId,
            @RequestParam String service,
            @RequestParam String status,
            @RequestParam String reference,
            @RequestParam int offset,
            @RequestParam int limit) {

        return paymentService.getFilteredPayments(dateFrom, dateTo, userId, service, status, reference, offset, limit);
    }
}
