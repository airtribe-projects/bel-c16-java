package org.example.airtribe.LearnerManagementSystemBelC16.repository;

import java.util.List;
import java.util.Optional;
import org.example.airtribe.LearnerManagementSystemBelC16.entity.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {
  List<Learner> findByLearnerName(String learnerName);

  @Query("SELECT l FROM Learner l WHERE l.learnerName = ?1")
  List<Learner> fetchMeLearner(String learnerName);

  Optional<Learner> findByLearnerEmail(String learnerEmail);
}
