package com.gitzblitz.breathe

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import com.github.florent37.viewanimator.ViewAnimator
import com.gitzblitz.breathe.util.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import java.text.MessageFormat

class MainActivity : AppCompatActivity() {

    lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = Prefs(this)

        breathsTaken.text = MessageFormat.format("{0} min today", prefs.sessions)
        todayMinusText.text = MessageFormat.format("{0} breaths", prefs.breaths)
        lastBreath.text = prefs.date


        startButton.setOnClickListener {

            startAnimation()

        }
    }

    private fun startAnimation() {
        ViewAnimator
            .animate(lotusImageView)
            .alpha(0f, 1f)
            .onStart {
                guideTxt.text = getString(R.string.inhale_exhale)
            }
            .decelerate()
            .duration(1000)
            .thenAnimate(lotusImageView)
            .scale(0.02f, 1.5f, 0.02f)
            .rotation(360f)
            .repeatCount(5)
            .accelerate()
            .duration(5000)
            .onStop {
                guideTxt.text = getString(R.string.good_job)
                lotusImageView.scaleX = 1.0f
                lotusImageView.scaleY = 1.0f
                //refresh the activity

                prefs.sessions = prefs.sessions + 1
                prefs.breaths = prefs.breaths +1
                prefs.setDate(System.currentTimeMillis())

                object : CountDownTimer(2000, 1000){
                    override fun onFinish() {
                       startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    }

                    override fun onTick(millisUntilFinished: Long) {

                    }
                }.start()
            }
            .start()
    }

    private fun startIntroAnimation() {
        ViewAnimator.animate(guideTxt)
            .scale(0.0f, 1f)
            .duration(1500)
            .onStart {
                guideTxt.text = getString(R.string.breathe)
            }
            .start()
    }


}
