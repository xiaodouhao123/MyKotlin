package com.example.mykotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykotlin.databinding.ActivityMainBinding
import com.example.mykotlin.gaojihanshu.Country
import com.example.mykotlin.gaojihanshu.CountryApp
import com.example.mykotlin.gaojihanshu.CountryTest
import com.example.mykotlin.mvvm.*

class MainActivity : AppCompatActivity(), RepositoryRecyclerViewAdapter.OnItemClickListener {
    private lateinit var binding:ActivityMainBinding
    private var repositoryRecyclerViewAdapter=RepositoryRecyclerViewAdapter(arrayListOf(),this)
    //var mainViewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        // setContentView(R.layout.activity_main)
        /*binding.repositoryName.text="Modern Android Medium Article"
        binding.repositoryOwner.text="Mladen Rakonjac"
        binding.numberOfStarts.text="1000 stars"*/
        /*var repository=Repository("Medium Android Repository Article",
            "old owener", 1000, true)
        binding.repository=repository
        binding.executePendingBindings()
        Handler().postDelayed({repository.repositoryName="New Name"
            repository.respositoryOwner="new owener"
                              },2000)*/
        val mainViewModelFactory=Injection.providerMainViewModelFactory(applicationContext)
        val viewModel=ViewModelProviders.of(this,mainViewModelFactory).get(MainViewModel::class.java)
        binding.viewModel=viewModel
        binding.repositoryRv.layoutManager=LinearLayoutManager(this)
        binding.repositoryRv.adapter=repositoryRecyclerViewAdapter
        viewModel.repositories.observe(this, Observer { it?.let { repositoryRecyclerViewAdapter.replaceData(it) } })
        val countryApp=CountryApp()
        val countryTest=CountryTest()
        val countries= listOf<Country>(Country("1","2",3))
        countryApp.filterCountries(countries,fun(country:Country):Boolean{
            return country.continient=="EU"&&country.population>10000
        })
        countryApp.filterCountries(countries) { country -> country.continient=="EU"&&country.population>10000 }

    }

    override fun onItemClick(position: Int) {
        Log.w("onItemClick"," 点击了第"+position+"条");
    }
}