package com.gems;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GemRepository extends MongoRepository<Gem, String> {
    List<Gem> findByReserved(boolean reserved);
    List<Gem> findByTitleContaining(String title);
}
