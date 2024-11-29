package com.alejodev.banco_alviga.Activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alejodev.banco_alviga.R
import com.alejodev.banco_alviga.databinding.ActivityJokeBinding
import com.alejodev.banco_alviga.databinding.ActivitySplashBinding

class JokeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityJokeBinding.inflate(layoutInflater)
        setContentView(binding.root)


            val lottieAnim = binding.lottieAnimation
            lottieAnim.repeatCount = 0

            lottieAnim.playAnimation()

            // Configura el listener para detectar el final de la animación
            lottieAnim.addAnimatorListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    finish() // Finaliza SplashActivity
                }
            })
    }

}
