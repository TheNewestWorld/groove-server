package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
