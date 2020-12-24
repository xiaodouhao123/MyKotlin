package com.example.mykotlin.learn.package1

import com.example.mykotlin.learn.package2.Point
import com.example.mykotlin.learn.package2.Test
import com.example.mykotlin.learn.package2.index
import kotlin.random.Random

fun main(){
    val point =Point(10, index)
    Test(true)
    //val intValue=10022222222
    val doubleValue=100.0
    //val longValue=100L
    //如果只读变量在声明时没有初始值，则必须指明变量类型
    val intValue2:Int
    if (false){
        intValue2=10
    }else{
        intValue2=20
    }
    println(intValue2)
    val intIndex:Int=100
    //val intIndex=100
    val doubleIndex:Double=intIndex.toDouble()
    val intValue:Int=1
    val longValue:Long=1
    val ch:Char='c'
    val charValue:Int=ch.toInt()
    val value1=0b00101
    val value2=0x123
    val array1= arrayOf("leavesC", "叶", "https://github.com/leavesC")
    array1[0]="leavesC"
    println(array1[1])
    println(array1.size)
    //val array2= arrayOfNulls<String>()
    val intArray=IntArray(5)
    val doubleArray=DoubleArray(5){
        Random.nextDouble()
    }
    val charArray= charArrayOf('H','e','l','l')
    //val any:Any=100
    val any:Any?=null
    computer(5,"5")
    computer(inex=5,value = "5")
    computet1("1","2")
    val maxValue=if (20>10){
        println("maxValue is 20")
        20
    }else{
        10
    }
    println(maxValue)//20
    val list= listOf(1,4,10,4,10,30)
    val value=if (list.size>0) list.size else null
    println(value)

}
fun computer(inex:Int,value:String){

}
fun computet1(vararg name:String){
    name.forEach { println(it) }
}