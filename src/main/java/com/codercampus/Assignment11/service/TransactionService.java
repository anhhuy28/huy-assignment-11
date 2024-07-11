package com.codercampus.Assignment11.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
    public Transaction findByID(Integer transactionID) {
        return null;
    }

	public List<Transaction> findAllTransactions() {
		return transactionRepository.findAll()
        .stream()
        .sorted((t1, t2) -> t1.getDate().compareTo(t2.getDate()))
        .collect(Collectors.toList());
}

    public Optional<Transaction> findTransactionById(Long transactionId) {
        return transactionRepository.findAll()
                .stream()
                .filter(transaction -> transaction.getId().equals(transactionId))
                .findFirst();
    }
	
}
