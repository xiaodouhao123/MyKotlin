package com.example.mykotlin.learn.package2
val index=10
fun Test(status: Boolean)=println(status)
class Point(val x:Int,val y:Int){
    val isEquals1:Boolean
    get() {
        return x==y
    }
    val isEquals2
    get() = x==y
    var isEquals3=false
    get() = x>y
    set(value) {
        field=!value
    }
}
