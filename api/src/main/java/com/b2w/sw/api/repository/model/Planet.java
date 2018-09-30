package com.b2w.sw.api.repository.model;


import com.b2w.sw.api.controller.dto.PlanetDTO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;

import java.util.Objects;


public class Planet {

    @Id
    private String id;
    private String name;
    private String weather;
    private String land;
    private int showMovie;

    public static Planet builde(PlanetDTO planetDTO) {

        if (planetDTO == null) {
            return null;
        }

        Planet planet = new Planet();
        planet.setLand(planetDTO.getLand());
        planet.setName(planetDTO.getName());
        planet.setShowMovie(planetDTO.getShowMovie());
        planet.setWeather(planetDTO.getWeather());
        planet.setId(planetDTO.getId());
        return planet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return showMovie == planet.showMovie &&
                Objects.equals(id, planet.id) &&
                Objects.equals(name, planet.name) &&
                Objects.equals(weather, planet.weather) &&
                Objects.equals(land, planet.land);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, weather, land, showMovie);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
