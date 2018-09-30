package com.b2w.sw.api.service;

import com.b2w.sw.api.controller.dto.PlanetDTO;
import com.b2w.sw.api.repository.PlanetRepository;
import com.b2w.sw.api.repository.model.Planet;
import com.b2w.sw.client.SWClient;
import com.b2w.sw.client.dto.SWPlanet;
import com.b2w.sw.client.dto.SWPlanetList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlanetServiceTest {

    @Mock
    private SWClient swClient;
    @Mock
    private PlanetRepository planetRepository;
    @InjectMocks
    private PlanetService planetService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getAll() {
        when(planetRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        List<Planet> planetList = planetService.getAll();
        verify(planetRepository, times(1)).findAll();
        assertEquals(0, planetList.size());
    }

    @Test
    public void getByName() {

        String name = "NAME_PLANET";
        Planet planet = new Planet();

        when(planetRepository.findByName(name)).thenReturn(planet);

        Planet result = planetService.getByName(name);

        verify(planetRepository, times(1)).findByName(name);

        assertEquals(planet, result);
    }

    @Test
    public void getByNameAndNameisNull() {

        String name = null;
        Planet result = planetService.getByName(name);

        verify(planetRepository, times(0)).findByName(any());

        assertEquals(null, result);
    }

    @Test
    public void getByNameAndNameisBlank() {

        String name = " ";
        Planet result = planetService.getByName(name);

        verify(planetRepository, times(0)).findByName(any());

        assertEquals(null, result);
    }

    @Test
    public void getById() {

        String id = "ID_PLANET";
        Planet planet = new Planet();
        Optional<Planet> optionalPlanet = Optional.of(planet);

        when(planetRepository.findById(id)).thenReturn(optionalPlanet);

        Planet result = planetService.getById(id);

        verify(planetRepository, times(1)).findById(id);

        assertEquals(planet, result);
    }


    @Test
    public void getByIdNotFound() {

        String id = "ID_PLANET";
        Optional<Planet> optionalPlanet = Optional.empty();

        when(planetRepository.findById(id)).thenReturn(optionalPlanet);

        Planet result = planetService.getById(id);

        verify(planetRepository, times(1)).findById(id);

        assertEquals(null, result);
    }

    @Test
    public void getByNameAndIdisNull() {

        String id = null;
        Planet result = planetService.getById(id);

        verify(planetRepository, times(0)).findById(any());

        assertEquals(null, result);
    }

    @Test
    public void getByNameAndIdisBlank() {

        String id = " ";
        Planet result = planetService.getById(id);

        verify(planetRepository, times(0)).findByName(any());

        assertEquals(null, result);
    }


    @Test
    public void save() throws IOException {

        PlanetDTO planetDTO = new PlanetDTO();
        planetDTO.setWeather("TEST_WEATHER");
        planetDTO.setName("TEST_NAME");
        planetDTO.setLand("TEST_LAND");

        Planet planet = new Planet();
        planet.setWeather("TEST_WEATHER");
        planet.setName("TEST_NAME");
        planet.setLand("TEST_LAND");
        planet.setShowMovie(1);


        SWPlanet swPlanet = new SWPlanet();
        swPlanet.setName("TEST_NAME");
        swPlanet.getFilms().add("FILMS");

        SWPlanetList swPlanetList = new SWPlanetList();
        swPlanetList.getResults().add(swPlanet);

        Call<SWPlanetList> swPlanetListCall = mock(Call.class);
        Response<SWPlanetList> planetListResponse = Response.success(swPlanetList);


        when(swClient.searchPlanets("TEST_NAME", 1)).thenReturn(swPlanetListCall);
        when(swPlanetListCall.execute()).thenReturn(planetListResponse);
        verify(planetRepository, times(0)).save(eq(planet));

        planetService.save(planetDTO);


    }


    @Test
    public void saveNull() throws IOException {
        planetService.save(null);
        verify(planetRepository, times(0)).save(any(Planet.class));
    }


    @Test(expected = IOException.class)
    public void saveException() throws IOException {

        PlanetDTO planetDTO = new PlanetDTO();
        planetDTO.setWeather("TEST_WEATHER");
        planetDTO.setName("TEST_NAME");
        planetDTO.setLand("TEST_LAND");

        Planet planet = new Planet();
        planet.setWeather("TEST_WEATHER");
        planet.setName("TEST_NAME");
        planet.setLand("TEST_LAND");
        planet.setShowMovie(1);


        SWPlanet swPlanet = new SWPlanet();
        swPlanet.setName("TEST_NAME");
        swPlanet.getFilms().add("FILMS");

        SWPlanetList swPlanetList = new SWPlanetList();
        swPlanetList.getResults().add(swPlanet);

        Call<SWPlanetList> swPlanetListCall = mock(Call.class);


        when(swClient.searchPlanets("TEST_NAME", 1)).thenReturn(swPlanetListCall);
        when(swPlanetListCall.execute()).thenThrow(new IOException(""));
        verify(planetRepository, times(0)).save(eq(planet));

        planetService.save(planetDTO);


    }
}
