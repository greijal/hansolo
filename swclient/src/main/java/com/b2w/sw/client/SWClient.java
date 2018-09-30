package com.b2w.sw.client;

import com.b2w.sw.client.dto.SWPlanetList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SWClient {

    @GET("planets/")
    Call<SWPlanetList> searchPlanets(@Query("search") String search,
                                     @Query("page") int page);

}