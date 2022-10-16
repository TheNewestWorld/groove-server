package org.bogus.groove.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAuthenticationRepository extends JpaRepository<EmailAuthenticationEntity, Long> {

}
