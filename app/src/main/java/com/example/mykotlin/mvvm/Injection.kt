package com.example.mykotlin.mvvm

import android.content.Context

object Injection {
    private var NET_MANAGER:NetManager?=null
    fun providerMainViewModelFactory(context: Context):MainViewModelFactory{
        val netManager=provideNetManager(context)
        val repositor=gitRepoRepository(netManager)
        return MainViewModelFactory(repositor)
    }
    private fun provideNetManager(context: Context):NetManager{
        if (NET_MANAGER==null){
            NET_MANAGER= NetManager(context)
        }
        return NET_MANAGER!!
    }
    private fun gitRepoRepository(netManager: NetManager):GitRepoRepository{
        return GitRepoRepository(netManager)
    }

}