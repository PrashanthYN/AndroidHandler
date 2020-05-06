package com.pyn.androidhandler

import android.os.Bundle
import android.os.Message

/**
 * Created by Prashant on 5/6/2020.
 */
class CounterThread(var parentActivity:MainActivity, var counter:Int):Thread() {
    override fun run() {
        super.run()
        for(i in 1..counter){
            sleep(1000)
            val bundle:Bundle=Bundle()
            bundle.putString("count",i.toString())
            val message=Message()
            message.what=1
            message.data=bundle
            parentActivity.handler.sendMessage(message)
        }
    }
}