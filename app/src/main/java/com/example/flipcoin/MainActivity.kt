package com.example.flipcoin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var roll_image:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            cointoss()

    }



    private fun cointoss() {

        val toss_button:Button=findViewById(R.id.toss_button)
        roll_image= findViewById(R.id.roll_image)
        val result_text:TextView=findViewById(R.id.result_text)
        toss_button.setOnClickListener {
            val random_number = Random.nextInt(2) + 1

            if (random_number == 1) {
                result_text.text="Hurray ! Heads"
                flipTheCoin(R.drawable.head)
            } else {
                result_text.text="Hurray ! Tails"
                flipTheCoin(R.drawable.tail)
            }
        }
    }
        /*fun flipTheCoin(imageid: Int) {
            roll_image.animate().apply{
                duration=350
                rotationYBy(1800f)
                //roll_image.isClickable=false
            }.withEndAction{
                roll_image.setImageResource(imageid)
                //Toast.makeText(this,coinside, Toast.LENGTH_SHORT).show()
                //roll_image.isClickable=true

            }.start()
        }*/

    fun flipTheCoin(imageid: Int) {
        val oa1= ObjectAnimator.ofFloat(roll_image,"scaleX",1f,0f)
        val oa2=ObjectAnimator.ofFloat(roll_image,"scaleX",0f,1f)
        oa1.interpolator= DecelerateInterpolator()
        oa2.interpolator= AccelerateDecelerateInterpolator()
        oa1.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                roll_image.setImageResource(imageid)
                oa2.start()
            }
        })
        oa1.start()
        oa1.setDuration(100)
        oa2.setDuration(100)
    }


}
