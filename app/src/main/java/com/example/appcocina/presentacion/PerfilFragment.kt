package com.example.appcocina.presentacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appcocina.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {

    private lateinit var binding : FragmentPerfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

}