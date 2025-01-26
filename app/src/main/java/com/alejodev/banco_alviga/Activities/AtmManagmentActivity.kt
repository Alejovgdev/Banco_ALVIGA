package com.alejodev.banco_alviga.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alejodev.banco_alviga.R
import com.alejodev.banco_alviga.databinding.ActivityAtmManagmentBinding

class AtmManagmentActivity : BaseActivity() {

    private lateinit var binding: ActivityAtmManagmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAtmManagmentBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnlistar.setOnClickListener {
            val intent = Intent(this, AtmListActivity::class.java)
            startActivity(intent)
        }

        binding.btnaAdir.setOnClickListener {
            val intent = Intent(this, AtmFormActivity::class.java)
            startActivity(intent)
        }
    }
}