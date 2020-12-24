package com.example.mykotlin.gaojihanshu

class CountryTest {
    fun isBigEuropeanCountry(country: Country):Boolean{
        return country.continient=="EU"&&country.population>10000
    }

}