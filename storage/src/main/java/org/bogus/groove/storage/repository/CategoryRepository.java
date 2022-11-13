package org.bogus.groove.storage.repository;

import java.util.List;
import org.bogus.groove.common.enumeration.CategoryGroup;
import org.bogus.groove.storage.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByCategoryGroupAndIsDeletedFalse(CategoryGroup categoryGroup);
}
