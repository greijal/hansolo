package com.b2w.sw.api.service;


import com.b2w.sw.api.controller.dto.PlanetDTO;
import com.b2w.sw.api.repository.PlanetRepository;
import com.b2w.sw.api.repository.model.Planet;
import com.b2w.sw.client.SWClient;
import com.b2w.sw.client.dto.SWPlanet;
import com.b2w.sw.client.dto.SWPlanetList;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    private final Logger log = LoggerFactory.getLogger(PlanetService.class);


    @Autowired
    private PlanetRepository planetRepository;
    @Autowired
    private SWClient swClient;

    public List<Planet> getAll() {
        return planetRepository.findAll();
    }

    public Planet getByName(String name) {

        if (StringUtils.isBlank(name)) {
            log.warn("It is not possible to search for a planet by name without defining a name");
            return null;
        }
        return planetRepository.findByName(name);
    }


    public Planet getById(String id) {

        if (StringUtils.isBlank(id)) {
            log.warn("It is not possible to search for a planet by ID without defining a ID");

            return null;
        }
        Optional<Planet> planet = planetRepository.findById(id);
        return planet.isPresent() ? planet.get() : null;
    }


    public Planet save(PlanetDTO planetDTO) throws IOException {

        if (planetDTO == null) {
            log.warn("Can not save an empty planet");
            return null;
        }

        Planet planet = Planet.builde(planetDTO);

        SWPlanet swPlanet = findPlanatInAPI(planet.getName(), 1);

        if (swPlanet != null) {
            planet.setShowMovie(swPlanet.getFilms().size());

        }
        return planetRepository.save(planet);
    }


    private SWPlanet findPlanatInAPI(String name, int page) throws IOException {

        if (StringUtils.isBlank(name) || page == 0) {
            return null;
        }

        SWPlanetList swPlanetList = swClient.searchPlanets(name, page).execute().body();

        SWPlanet planet = swPlanetList.getResults()
                .stream()
                .filter(swPlanet -> name.equalsIgnoreCase(swPlanet.getName()))
                .findFirst()
                .orElse(null);

        if (planet == null && swPlanetList != null && swPlanetList.hasNetxPage()) {
            page = page + 1;
            return findPlanatInAPI(name, page);
        }

        return planet;
    }


}
