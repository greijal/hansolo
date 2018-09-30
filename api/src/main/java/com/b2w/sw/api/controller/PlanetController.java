package com.b2w.sw.api.controller;


import com.b2w.sw.api.controller.dto.PlanetDTO;
import com.b2w.sw.api.repository.model.Planet;
import com.b2w.sw.api.service.PlanetService;
import com.b2w.sw.client.SWClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@RestController
public class PlanetController {

    private final Logger log = LoggerFactory.getLogger(PlanetController.class);

    @Autowired
    private PlanetService planetService;
    @Autowired
    private SWClient swClient;

    @GetMapping(value = "/planet/all")
    public List<PlanetDTO> getAll() {

        List<Planet> planetLit = planetService.getAll();
        List<PlanetDTO> response = Collections.emptyList();

        planetLit.forEach(planet -> {
            response.add(PlanetDTO.builde(planet));
        });

        return response;
    }

    @GetMapping(value = "/planet/name/{name}")
    public ResponseEntity<PlanetDTO> getByName(@PathVariable(name = "name") String name) {
        Planet planet = planetService.getByName(name);
        return planet == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(PlanetDTO.builde(planet));
    }

    @GetMapping(value = "/planet/id/{id}")
    public ResponseEntity<PlanetDTO> getById(@PathVariable(name = "id") String id) {
        Planet planet = planetService.getById(id);
        return planet == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(PlanetDTO.builde(planet));
    }

    @PutMapping(value = "/planet")
    public ResponseEntity<PlanetDTO> save(@RequestBody(required = true) PlanetDTO planetDTO) throws IOException {

        if (StringUtils.isNotBlank(planetDTO.getId())) {
            log.warn("User can not set the planet ID DTO [{}]", planetDTO);
            return ResponseEntity.badRequest().build();
        }

        Planet result = planetService.save(planetDTO);

        return ResponseEntity.ok(PlanetDTO.builde(result));
    }

}
