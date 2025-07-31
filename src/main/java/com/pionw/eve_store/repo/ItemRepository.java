package com.pionw.eve_store.repo;

import com.pionw.eve_store.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
