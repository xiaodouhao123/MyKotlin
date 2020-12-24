package com.example.mykotlin.gaojihanshu

class CountryApp {
    fun filterCountries(countries:List<Country>,test:(Country)->Boolean):List<Country>{
        val res= mutableListOf<Country>()
        for (c in countries){
            if (test(c)){
                res.add(c)
            }
        }
        return res

    }
}