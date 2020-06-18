package com.aris.covid19app.rest;

import com.aris.covid19app.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("/summary")
    Call<Response> country();


}
