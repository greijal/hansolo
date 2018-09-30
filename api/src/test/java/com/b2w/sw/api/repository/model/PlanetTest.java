package com.b2w.sw.api.repository.model;

import com.b2w.sw.api.controller.dto.PlanetDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PlanetTest {

    @Test
    public void testBuilder() {

        PlanetDTO planetDTO = new PlanetDTO();
        planetDTO.setId("TEST_ID");
        planetDTO.setLand("TEST_LAND");
        planetDTO.setName("TEST_NAME");
        planetDTO.setShowMovie(1);
        planetDTO.setWeather("TEST_WEATHER");

        Planet result = Planet.builde(planetDTO);

        assertEquals(planetDTO.getId(), result.getId());
        assertEquals(planetDTO.getLand(), result.getLand());
        assertEquals(planetDTO.getName(), result.getName());
        assertEquals(planetDTO.getShowMovie(), result.getShowMovie());
        assertEquals(planetDTO.getWeather(), result.getWeather());
    }

    @Test
    public void testBuilderNullPLanet() {
        Planet result = Planet.builde(null);
        assertNull(result);

    }
}
