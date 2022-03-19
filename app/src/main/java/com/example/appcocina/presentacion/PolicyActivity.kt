package com.example.appcocina.presentacion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appcocina.databinding.ActivityPoliciesBinding

class PolicyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityPoliciesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAtras.setOnClickListener() {
            onBackPressed()
        }

    }
}