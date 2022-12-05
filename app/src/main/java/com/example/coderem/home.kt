package com.example.coderem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coderem.databinding.FragmentHomeBinding
import com.example.coderem.databinding.FragmentUpcomingBinding


class home : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater, container, false)
        val adapter= PagerAdapter(requireActivity().supportFragmentManager)
        adapter.addfrag(upcoming(),"FIRST")
//        binding.viewpager.adapter=adapter
//        binding.tabs.setupWithViewPager(binding.viewpager)

        return binding.root
    }


}