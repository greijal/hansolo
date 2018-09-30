package com.b2w.sw.api.controller.dto;

import com.b2w.sw.api.repository.model.Planet;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class PlanetDTO {

    private String id;
    private String name;
    private String weather;
    private String land;
    private int showMovie;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public int getShowMovie() {
        return showMovie;
    }

    public void setShowMovie(int showMovie) {
        this.showMovie = showMovie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanetDTO planetDTO = (PlanetDTO) o;
        return showMovie == planetDTO.showMovie &&
                Objects.equals(id, planetDTO.id) &&
                Objects.equals(name, planetDTO.name) &&
                Objects.equals(weather, planetDTO.weather) &&
                Objects.equals(land, planetDTO.land);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weather, land, showMovie);
    }

    public static PlanetDTO builde(Planet planet) {

        if (planet == null) {
            return null;
        }

        PlanetDTO planetDTO = new PlanetDTO();
        planetDTO.setLand(planet.getLand());
        planetDTO.setName(planet.getName());
        planetDTO.setShowMovie(planet.getShowMovie());
        planetDTO.setWeather(planet.getWeather());
        planetDTO.setId(planet.getId());
        return planetDTO;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
