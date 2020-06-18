package com.aris.covid19app.model;

import java.util.List;

public class Response {

    private GlobalModel Global;
    private List<CountryModel> Countries;
    private String Date;

    public Response() {

    }

    public GlobalModel getGlobal() {
        return Global;
    }

    public void setGlobal(GlobalModel global) {
        Global = global;
    }

    public List<CountryModel> getCountries() {
        return Countries;
    }

    public void setCountries(List<CountryModel> countries) {
        Countries = countries;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "Response{" +
                "Global=" + Global +
                ", Countries=" + Countries +
                ", Date='" + Date + '\'' +
                '}';
    }
}
