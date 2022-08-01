package com.sap.ledger.repository;

import org.springframework.stereotype.Repository;

import com.sap.ledger.entity.Loan;

public class BalanceRepository {
	
	@Repository
	public interface LoanRepository extends JpaRepository<Loan, Long>{

	}

}
