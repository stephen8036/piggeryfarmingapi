package com.piggery.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piggery.models.FeedsAuditor;

public interface FeedsAuditorRepository extends JpaRepository<FeedsAuditor, Long> {

}
