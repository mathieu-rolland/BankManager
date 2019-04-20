package com.perso.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perso.bank.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
