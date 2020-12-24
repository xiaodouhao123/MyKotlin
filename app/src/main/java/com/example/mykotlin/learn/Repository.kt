package com.example.mykotlin.learn

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class Repository (repositoryName:String,respositoryOwner:String,var numberOfStarts:Int?,var hasIssues:Boolean=false):
    BaseObservable() {
    @get:Bindable
    var repositoryName :String ="Medium Android Repository Article"
    set(value) {
        field=value
        notifyPropertyChanged(BR.repositoryName)
    }

    @get:Bindable
    var respositoryOwner :String ="old owner"
        set(value) {
            field=value
            notifyPropertyChanged(BR.respositoryOwner)
        }

}