package com.perso.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perso.bank.domain.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {

}
