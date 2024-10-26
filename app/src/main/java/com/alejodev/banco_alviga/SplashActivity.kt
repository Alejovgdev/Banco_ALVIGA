package com.alejodev.banco_alviga

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alejodev.banco_alviga.databinding.ActivityLoginBinding
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