package com.sap.ledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sap.ledger.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

	Loan getLoanByBankAndBorrower(String bankName, String borrowerName);

}
