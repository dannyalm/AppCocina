package com.example.appcocina.presentacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appcocina.R
import com.example.appcocina.databinding.FragmentMisListasBinding
import com.example.appcocina.databinding.FragmentMisRecetasBinding

class MisRecetasFragment : Fragment() {

    private  lateinit var binding : FragmentMisRecetasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMisRecetasBinding.inflate(inflater, container, false)
        return binding.root
    }

}