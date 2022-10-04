package com.example.coderem

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.navigation.NavigationView


class home : Fragment() {
    lateinit var vm: ViewModel
    lateinit var re: RecyclerView
    lateinit var myre: RecyclerView
    lateinit var shim: ShimmerFrameLayout



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_home, container, false)
        shim=v.findViewById(R.id.shimmerLayout)
        shim.startShimmer()
        re=v.findViewById(R.id.recycler)
        myre=v.findViewById(R.id.names)
        vm = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(ViewModel::class.java)


        vm.myevent.observe(viewLifecycleOwner){
            if(it.size>0){
                shim.stopShimmer()
                shim.visibility=View.GONE
                re.visibility=View.VISIBLE
            }
            re.adapter=DataAdapter(it)
            re.setHasFixedSize(true)
        }
        val list:MutableList<String> = mutableListOf("CODEFORCES","CODECHEF","LEETCODE","HACKEREARTH")
        myre.adapter=FilterAdapter(list)
        myre.setHasFixedSize(true)


        return v
    }


}