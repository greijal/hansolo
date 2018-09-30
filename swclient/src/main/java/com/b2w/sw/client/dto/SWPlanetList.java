package com.b2w.sw.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SWPlanetList {

    @JsonProperty("count")
    private int count;
    @JsonProperty("next")
    private String next;
    @JsonProperty("previous")
    private String previous;
    @JsonProperty("results")
    private List<SWPlanet> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<SWPlanet> getResults() {
        if (this.results == null) {
            this.results = new ArrayList<>();
        }
        return this.results;
    }

    public void setResults(List<SWPlanet> results) {
        if (results == null) {
            results = new ArrayList<>();
        }
        this.results = results;
    }

    public boolean hasNetxPage() {
        return !(this.next == null);
    }
}
