package com.example.mykotlin.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Transformations.map
import com.example.mykotlin.R
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.Flow

class NewActivity : AppCompatActivity() {
    lateinit var view : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        view=findViewById(R.id.tv_kotlin)
        /*val intArray= intArrayOf(1,2,3)
        val filter = intArray.map { i -> i +1 }
        filter.forEach { i->
            Log.w("xd",i.toString()+"  ")
        }
        val strList= listOf("a","b","c")
        val range:IntRange=0..1000
        for (i in 4 downTo 1 step 2)
        {Log.w("range","$i, ")}*/
        /*val sequence= sequenceOf(1,2,3,4)
        val result:Sequence<Int> = sequence.
        map{
            i->
            Log.w("xd","Map $i")
            i*2
        }.filter{
                i ->
            Log.w("xd","filter $i")

            i % 3  == 0
        }*/
        val sequence= listOf(1,2,3,4)
        val result:List<Int> = sequence.
        map{
                i->
            Log.w("xd","Map $i")
            i*2
        }.filter{
                i ->
            Log.w("xd","filter $i")

            i % 3  == 0
        }
        Log.w("xd",result.first().toString())
        test(view)
        //上游Flowable
        var upstream=Flowable.create(object :FlowableOnSubscribe<Integer>{
            override fun subscribe(emitter: FlowableEmitter<Integer>) {
                for (index in 1..10){
                    emitter.onNext(Integer(index))
                }
                emitter.onComplete()
            }

        },BackpressureStrategy.BUFFER)
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe(object: Subscriber<Integer> {
                override fun onComplete() {
                    Log.e("rxjava", "onComplete");
                }

                override fun onSubscribe(s: Subscription?) {
                    s?.request(java.lang.Long.MAX_VALUE)
                }

                override fun onNext(t: Integer?) {
                    // 一秒钟处理一条
                    Thread.sleep(1000)
                    Log.e("rxjava", "upstream's value=" + t);
                }

                override fun onError(p0: Throwable?) {
                }

            })
    }
    fun test(view:View?){
        Log.w("kotlin","viewGetID==="+view?.id)
    }
}