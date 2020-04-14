package com.kakaopaysec.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kakaopaysec.transaction.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}
