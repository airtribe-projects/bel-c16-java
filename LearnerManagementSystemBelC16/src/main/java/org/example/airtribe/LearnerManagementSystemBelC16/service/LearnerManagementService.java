package org.example.airtribe.LearnerManagementSystemBelC16.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.airtribe.LearnerManagementSystemBelC16.dto.LearnerDTO;
import org.example.airtribe.LearnerManagementSystemBelC16.entity.Cohort;
import org.example.airtribe.LearnerManagementSystemBelC16.entity.Course;
import org.example.airtribe.LearnerManagementSystemBelC16.entity.Learner;
import org.example.airtribe.LearnerManagementSystemBelC16.exception.CohortNotFoundException;
import org.example.airtribe.LearnerManagementSystemBelC16.exception.IllegalInputException;
import org.example.airtribe.LearnerManagementSystemBelC16.exception.LearnerNotFoundException;
import org.example.airtribe.LearnerManagementSystemBelC16.repository.CohortRepository;
import org.example.airtribe.LearnerManagementSystemBelC16.repository.CourseRepository;
import org.example.airtribe.LearnerManagementSystemBelC16.repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LearnerManagementService {
  @Autowired
  private LearnerRepository learnerRepository;

  @Autowired
  private CohortRepository cohortRepository;

  @Autowired
  private CourseRepository courseRepository;

  public Learner createLearner(Learner learner) {
    return learnerRepository.save(learner);
  }

  public List<Learner> getAllLearners() {
    return learnerRepository.findAll();
  }

  public Learner fetchLearnerById(long learnerId) throws LearnerNotFoundException, IllegalInputException {
    if (learnerId == 0) {
      throw new IllegalInputException("Learner ID cannot be null");
    }

    if (learnerRepository.findById(learnerId).isPresent()) {
      return learnerRepository.findById(learnerId).get();
    }

    throw new LearnerNotFoundException("Learner with Id " + learnerId + " not found");
  }

  public List<Learner> findLearnerByName(String learnerName) {
    return learnerRepository.findByLearnerName(learnerName);
  }

  public List<Learner> fetchLearnersDifferentParams(String learnerName, Long learnerId)
      throws LearnerNotFoundException, IllegalInputException {
    List<Learner> result = new ArrayList<>();
    if (learnerId != null) {
      result.add(fetchLearnerById(learnerId));
      return result;
    }

    if (learnerName != null)  {
      return findLearnerByName(learnerName);
    }

    return getAllLearners();

  }

  public Cohort createCohort(Cohort cohort) {
    return cohortRepository.save(cohort);
  }

  public Cohort assignLearnersToCohort(Long cohortId, Long learnerId)
      throws LearnerNotFoundException, CohortNotFoundException {
    Optional<Learner> learnerOptional = learnerRepository.findById(learnerId);
    if (!learnerOptional.isPresent()) {
      throw new LearnerNotFoundException("Learner with Id " + learnerId + " not found");
    }

    Optional<Cohort> cohortOpt = cohortRepository.findById(cohortId);
    if (!cohortOpt.isPresent()) {
      throw new CohortNotFoundException("Cannot find cohort with Id " + cohortId);
    }

    Cohort cohort = cohortOpt.get();
    List<Learner> learners = cohort.getLearners();
    Learner learner = learnerOptional.get();
    learners.add(learner);
    cohort.setLearners(learners);
    return cohortRepository.save(cohort);
  }

  public List<Cohort> fetchAllCohorts() {
    return cohortRepository.findAll();
  }

  public Course createCourse(Course course) {
    return courseRepository.save(course);
  }

  @Transactional
  public Cohort assignAndCreateLearners(Long cohortId, List<Learner> learners) throws CohortNotFoundException {
    Optional<Cohort> cohortOptional = cohortRepository.findById(cohortId);
    if (!cohortOptional.isPresent()) {
      throw new CohortNotFoundException("Cohort with Id " + cohortId + " not found");
    }

//    for (Learner learner: learners) {
//      Optional<Learner> existingLearner = learnerRepository.findByLearnerEmail(learner.getLearnerEmail());
//      if (!existingLearner.isPresent()) {
//        learnerRepository.save(learner);
//      }
//    }

    Cohort cohort = cohortOptional.get();
    cohort.getLearners().addAll(learners);
    return cohortRepository.save(cohort);
  }
}
