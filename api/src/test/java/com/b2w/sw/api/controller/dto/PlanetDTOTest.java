package com.b2w.sw.api.controller.dto;

import com.b2w.sw.api.repository.model.Planet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PlanetDTOTest {


    @Test
    public void testBuilder() {

        Planet planet = new Planet();
        planet.setId("TEST_ID");
        planet.setLand("TEST_LAND");
        planet.setName("TEST_NAME");
        planet.setShowMovie(1);
        planet.setWeather("TEST_WEATHER");

        PlanetDTO result = PlanetDTO.builde(planet);

        assertEquals(planet.getId(), result.getId());
        assertEquals(planet.getLand(), result.getLand());
        assertEquals(planet.getName(), result.getName());
        assertEquals(planet.getShowMovie(), result.getShowMovie());
        assertEquals(planet.getWeather(), result.getWeather());
    }

    @Test
    public void testBuilderNullPLanet() {
        PlanetDTO result = PlanetDTO.builde(null);
        assertNull(result);

    }
}
