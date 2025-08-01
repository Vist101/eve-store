package com.pionw.eve_store.repo;

import com.pionw.eve_store.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
