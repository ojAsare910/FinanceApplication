package com.ojasare.service;

import com.ojasare.dto.DataListPaymentResponse;
import com.ojasare.dto.PaymentResponse;
import com.ojasare.entity.FinancialTransaction;
import com.ojasare.entity.Payment;
import com.ojasare.repository.FinancialTransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {


    private final FinancialTransactionRepository financialTransactionRepository;
    private final WebClient webClient;

    public PaymentService(FinancialTransactionRepository financialTransactionRepository, WebClient webClient) {
        this.financialTransactionRepository = financialTransactionRepository;
        this.webClient = webClient;
    }

    public Mono<ResponseEntity<DataListPaymentResponse>> getFilteredPayments(
            LocalDateTime dateFrom, LocalDateTime dateTo, Long userId, String service, String status, String reference, int offset, int limit) {

        Pageable pageable = PageRequest.of(offset, limit);
        Page<FinancialTransaction> financialTransactionsPage = financialTransactionRepository
                .findByTransactionDateBetweenAndUserIdAndServiceAndStatusAndReference(dateFrom, dateTo, userId, service, status, reference, pageable);

        List<FinancialTransaction> financialTransactions = financialTransactionsPage.getContent();

        Flux<Payment> paymentFlux = Flux.fromIterable(financialTransactions)
                .flatMap(transaction -> retrieveFinancialTransaction(transaction.getId()))
                .map(paymentResponse -> new Payment(paymentResponse.getId(), paymentResponse.getFinancialTransaction()));

        List<Payment> sortedPayments = paymentFlux.collectList().block().stream()
                .sorted(Comparator.comparing(Payment::getId).reversed())
                .collect(Collectors.toList());

        DataListPaymentResponse response = new DataListPaymentResponse(sortedPayments, createHateoasLinks(offset, limit, financialTransactionsPage.getTotalPages()));

        return Mono.just(ResponseEntity.ok(response));
    }

    private Mono<PaymentResponse> retrieveFinancialTransaction(Long paymentId) {
        return webClient.get()
                .uri("/payments/{id}", paymentId)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .onErrorResume(WebClientResponseException.class, ex -> Mono.error(new RuntimeException("Error retrieving payment", ex)));
    }

    private List<Link> createHateoasLinks(int offset, int limit, int totalPages) {
        return List.of();
    }
}
