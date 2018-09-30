package com.b2w.sw.api.repository;


import com.b2w.sw.api.repository.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository extends MongoRepository<Planet, String> {
    public Planet findByName(String name);
}
