package org.example.airtribe.LearnerManagementSystemBelC16.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;


public class LearnerDTO {
  private Long learnerId;
  private String learnerName;
  private String learnerEmail;
  private List<CohortDTO> cohorts;

  public LearnerDTO(Long learnerId, String learnerName, String learnerEmail, List<CohortDTO> cohorts) {
    this.learnerId = learnerId;
    this.learnerName = learnerName;
    this.learnerEmail = learnerEmail;
    this.cohorts = cohorts;
  }

  public LearnerDTO() {

  }

  public Long getLearnerId() {
    return learnerId;
  }

  public void setLearnerId(Long learnerId) {
    this.learnerId = learnerId;
  }

  public String getLearnerName() {
    return learnerName;
  }

  public void setLearnerName(String learnerName) {
    this.learnerName = learnerName;
  }

  public String getLearnerEmail() {
    return learnerEmail;
  }

  public void setLearnerEmail(String learnerEmail) {
    this.learnerEmail = learnerEmail;
  }

  public List<CohortDTO> getCohorts() {
    return cohorts;
  }

  public void setCohorts(List<CohortDTO> cohorts) {
    this.cohorts = cohorts;
  }
}
