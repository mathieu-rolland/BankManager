package com.perso.bank.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perso.bank.domain.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

	
	public List<Operation> dateBetween( LocalDateTime start , LocalDateTime end );
	
}
