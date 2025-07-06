package com.weddingapp.repository;

import com.weddingapp.entity.Gift;
import org.springframework.data.repository.CrudRepository;

public interface GiftRepository extends CrudRepository<Gift, Long> {
}
