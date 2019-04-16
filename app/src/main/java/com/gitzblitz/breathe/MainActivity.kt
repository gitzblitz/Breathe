package com.gitzblitz.breathe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        startButton.setOnClickListener {

            startAnimation()

        }
    }

    private fun startAnimation() {
        ViewAnimator
            .animate(lotusImageView)
            .alpha(0f,1f)
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
            .onStop { guideTxt.text = getString(R.string.good_job)
            lotusImageView.scaleX = 1.0f
            lotusImageView.scaleY = 1.0f}
            .start()
    }

    private fun startIntroAnimation() {
        ViewAnimator.animate(guideTxt)
            .scale(0.0f,1f)
            .duration(1500)
            .onStart {
                guideTxt.text = getString(R.string.breathe)
            }
            .start()
    }


}
