package com.example.coderem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.databinding.FragmentHomeBinding


class Home : Fragment() {
    lateinit var vm: ContestViewModel


   lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        binding.shimmerLayout.startShimmer()
        vm = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(ContestViewModel::class.java)


        vm.myevent.observe(viewLifecycleOwner){
            if(it.size>0){
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility=View.GONE
                binding.recycler.visibility=View.VISIBLE

            }
            val ml:MutableList<CodeData> = mutableListOf()
            for(i in it){
                if(i.site!="HackerRank"&&i.site!="HackerEarth"){
                    ml.add(i);
                }
            }
            binding.recycler.adapter=DataAdapter(ml)
            binding.recycler.setHasFixedSize(true)
        }
        val list:MutableList<String> = mutableListOf("CODEFORCES","CODECHEF","LEETCODE","ATCODER","KICKSTART")
        binding.names.adapter=FilterAdapter(list)
        binding.names.setHasFixedSize(true)


        return binding.root
    }


}