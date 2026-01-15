package org.airtribe.repository;

import org.airtribe.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
  VerificationToken findByToken(String verificationToken);
}
