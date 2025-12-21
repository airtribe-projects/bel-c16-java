package org.example.airtribe.LearnerManagementSystemBelC16.controller;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.airtribe.LearnerManagementSystemBelC16.dto.CohortDTO;
import org.example.airtribe.LearnerManagementSystemBelC16.dto.LearnerDTO;
import org.example.airtribe.LearnerManagementSystemBelC16.entity.Cohort;
import org.example.airtribe.LearnerManagementSystemBelC16.entity.Learner;
import org.example.airtribe.LearnerManagementSystemBelC16.exception.IllegalInputException;
import org.example.airtribe.LearnerManagementSystemBelC16.exception.LearnerNotFoundException;
import org.example.airtribe.LearnerManagementSystemBelC16.service.LearnerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LearnerController {

  @Autowired
  private LearnerManagementService learnerManagementService;

  // Jackson
  @PostMapping("/learners")
  public Learner createLearner(@Valid @RequestBody Learner learner) {

    return learnerManagementService.createLearner(learner);
  }

//  @GetMapping("/learners")
//  public List<Learner> fetchAllLearners() {
//    return learnerManagementService.getAllLearners();
//  }

  @GetMapping("/learners/{learnerId}")
  public Learner fetchLearnerById(@PathVariable("learnerId") long learnerId)
      throws LearnerNotFoundException, IllegalInputException {
    return learnerManagementService.fetchLearnerById(learnerId);
  }

//  @GetMapping("/learners/{learnerName}")
//  public List<Learner> findLearnerByName(@PathVariable("learnerName") String learnerName) {
//    return learnerManagementService.findLearnerByName(learnerName);
//  }

  @GetMapping("/learners")
  public List<LearnerDTO> fetchLearnersByName(
      @RequestParam(value = "learnerName", required = false) String learnerName,
      @RequestParam(value = "learnerId", required = false) Long learnerId)
      throws LearnerNotFoundException, IllegalInputException {

      List<Learner> learners = learnerManagementService.fetchLearnersDifferentParams(learnerName, learnerId);
      return converLearnersToLearnerDTOs(learners);
  }

  private List<LearnerDTO> converLearnersToLearnerDTOs(List<Learner> learners) {
    List<LearnerDTO> learnerDTOS = new ArrayList<>();
    for (Learner learner: learners) {
      LearnerDTO learnerDTO = new LearnerDTO();
      learnerDTO.setLearnerId(learner.getLearnerId());
      learnerDTO.setLearnerName(learner.getLearnerName());
      learnerDTO.setLearnerEmail(learner.getLearnerEmail());
      List<Cohort> cohorts = learner.getCohorts();
      List<CohortDTO> cohortDTOS = new ArrayList<>();
      for (Cohort cohort: cohorts) {
        CohortDTO cohortDTO = new CohortDTO();
        cohortDTO.setCohortId(cohort.getCohortId());
        cohortDTO.setCohortName(cohort.getCohortName());
        cohortDTO.setCohortDescription(cohort.getCohortDescription());
        cohortDTOS.add(cohortDTO);
      }
      learnerDTO.setCohorts(cohortDTOS);
      learnerDTOS.add(learnerDTO);
    }
    return learnerDTOS;
  }

  @ExceptionHandler(LearnerNotFoundException.class)
  public ResponseEntity handleLearnerNotFoundException(LearnerNotFoundException ex) {
    return ResponseEntity.status(404).body(ex.getMessage());
  }

  @ExceptionHandler(IllegalInputException.class)
  public ResponseEntity handleIllegalInputException(IllegalInputException ex) {
    return ResponseEntity.status(400).body(ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((org.springframework.validation.FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ResponseEntity.status(400).body(errors);
  }


}


// /cohorts -> POST

// Get me the learners associated with a specific cohort
// Get me all the cohorts that a particular learner is enrolled in

// "/cohorts/1/learners" // COMPLEX -> Relationship
// "/learners/1/cohorts" // COMPLEX -> Relationship

// "/courses/1/cohorts/1/learners"
// "/courses/1/learners"
