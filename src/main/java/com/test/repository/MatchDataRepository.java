package com.test.repository;

import com.test.entity.MatchData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MatchDataRepository extends JpaRepository<MatchData, String> {

    Page<MatchData> findAll(Specification<MatchData> specification, Pageable pageable);
}
