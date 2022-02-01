package com.example.appcocina.controladores

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appcocina.presentacion.MisListasFragment
import com.example.appcocina.presentacion.MisRecetasFragment

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return   when(position){
            0->{
                MisListasFragment()
            }
            1->{
                MisRecetasFragment()
            }
            else->{
                Fragment()
            }

        }
    }
}