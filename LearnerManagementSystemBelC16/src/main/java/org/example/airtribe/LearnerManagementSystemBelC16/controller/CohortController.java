package org.example.airtribe.LearnerManagementSystemBelC16.controller;

import java.util.List;
import org.apache.coyote.Response;
import org.example.airtribe.LearnerManagementSystemBelC16.entity.Cohort;
import org.example.airtribe.LearnerManagementSystemBelC16.entity.Learner;
import org.example.airtribe.LearnerManagementSystemBelC16.exception.CohortNotFoundException;
import org.example.airtribe.LearnerManagementSystemBelC16.exception.LearnerNotFoundException;
import org.example.airtribe.LearnerManagementSystemBelC16.service.LearnerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CohortController {
  @Autowired
  private LearnerManagementService _learnerManagementService;

  @PostMapping("/cohorts")
  public Cohort createCohort(@RequestBody Cohort cohort) {
    return _learnerManagementService.createCohort(cohort);
  }

  @GetMapping("/cohorts")
  public List<Cohort> getAllCohorts() {
    return _learnerManagementService.fetchAllCohorts();
  }

  @PostMapping("/assignLearnersToCohorts")
  public Cohort assignLearnersToCohorts(@RequestParam("cohortId") Long cohortId,
      @RequestParam("learnerId") Long learnerId) throws CohortNotFoundException, LearnerNotFoundException {
    return _learnerManagementService.assignLearnersToCohort(cohortId, learnerId);
  }

  @PostMapping("/cohorts/{cohortId}/learners")
  public Cohort assignAndCreateLearners(@PathVariable("cohortId") Long cohortId, @RequestBody List<Learner> learners)
      throws CohortNotFoundException {
    return _learnerManagementService.assignAndCreateLearners(cohortId, learners);
  }


  @ExceptionHandler(CohortNotFoundException.class)
  public ResponseEntity handleCohortNotFoundException(CohortNotFoundException e) {
    return ResponseEntity.status(404).body(e.getMessage());
  }

  @ExceptionHandler(LearnerNotFoundException.class)
  public ResponseEntity handleLearnerNotFoundException(LearnerNotFoundException e) {
    return ResponseEntity.status(404).body(e.getMessage());
  }

}
// POST
// PRE_REQISOTOE -. Cohort Will be existing and learner will be existing
// /assignLearnersToCohorts?learnerId=1&cohortId=2
// /assignLearnersToCohorts?learnerIds=1,2,3&cohortId=2
// /assignLearnersToCohorts -> {learnerIds: [1,2,3], cohortId: 2}
// /cohorts/1/learners -> {learnerIds: [1,2,3]} -> POST / PUT
// /cohorts/1/learners -> [{learner1}, {learner2}] -> POST
// cobine -> creation of the learners + Assignment of them to the cohorts


// Maps courses to cohorts
// /courses/{courseId}/cohorts -> {cohortIds: [1,2,3]}

// Given a cohortId fetch me the learners
// /cohorts/{cohortId}/learners -> GET
// /cohorts/{cohortId} -> GET -> Cohort

// Given a course id fetch me the learners
// /courses/{courseId}/learners -> LOD
// /course/{couurseId}/cohorts -> GET

//
//course -> cohorts -> learners