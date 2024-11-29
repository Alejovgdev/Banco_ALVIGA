package com.alejodev.banco_alviga.Activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.alejodev.banco_alviga.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lottieAnim = binding.lottieAnimation
        lottieAnim.repeatCount = 0

        lottieAnim.playAnimation()

        // Configura el listener para detectar el final de la animación
        lottieAnim.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // Lanza la MainActivity al finalizar la animación
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish() // Finaliza SplashActivity
            }
        })
    }
}