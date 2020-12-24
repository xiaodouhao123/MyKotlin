package com.example.mykotlin.mvvm

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mykotlin.extensions.plusAssign
import com.example.mykotlin.learn.Repository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(private var repoModel:GitRepoRepository): ViewModel(){
    private val compositeDisposable = CompositeDisposable()
    var repositories= MutableLiveData<ArrayList<Repository>>()
    val text=ObservableField<String>()
    val isLoading=ObservableField<Boolean>()
    val onDataReadyCallback=object: OnDataReadyCallback{
        override fun onDataReady(data: String) {
            isLoading.set(false)
            text.set(data)
        }
    }
    fun refresh(){
        isLoading.set(true)
        repoModel.refreshData(onDataReadyCallback)
    }
    fun loadRepositories(){
        isLoading.set(true)
        compositeDisposable+=repoModel.getRepositories()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ArrayList<Repository>> (){
                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                    isLoading.set(false)
                }

                override fun onNext(data: ArrayList<Repository>) {
                    repositories.value=data
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }
}