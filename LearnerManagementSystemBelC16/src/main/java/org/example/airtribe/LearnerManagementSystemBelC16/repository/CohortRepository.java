package org.example.airtribe.LearnerManagementSystemBelC16.repository;

import org.example.airtribe.LearnerManagementSystemBelC16.entity.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CohortRepository extends JpaRepository<Cohort, Long> {
}
