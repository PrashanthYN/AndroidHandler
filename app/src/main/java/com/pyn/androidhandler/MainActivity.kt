package com.pyn.androidhandler

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var counter:Int=30

    companion object{
        val TAG=MainActivity.javaClass.name
    }

    val handler:Handler=object :Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            Log.d(TAG,"Message received")
            Log.d(TAG,"Msg what="+msg.what)
            val bundle=msg.data
            val count=bundle.getString("count")
            if (count.equals(counter.toString())){
                counterTextViewId.text="0"
                button.isEnabled=true
                val tg = ToneGenerator(AudioManager.STREAM_SYSTEM, 100)
                tg.startTone(ToneGenerator.TONE_SUP_CONFIRM)
            }else {
                counterTextViewId.text = count
                button.isEnabled=false
                val tg = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100)
                tg.startTone(ToneGenerator.TONE_PROP_BEEP)
            }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        totalTimerText.setText(counter.toString())
        setTimerButton.setOnClickListener(View.OnClickListener {
           if(!timerText.text.toString().equals("") || !timerText.text.toString().equals("0")){
               totalTimerText.text=timerText.text.toString()
               counter=Integer.parseInt(timerText.text.toString())
           }else{
               timerText.clearComposingText()
               totalTimerText.text="0"
           }
        })


        button.setOnClickListener(View.OnClickListener {
            CounterThread(this,counter).start()
        })
    }
}
