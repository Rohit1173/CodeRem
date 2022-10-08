package com.example.coderem

import android.os.Bundle
import android.system.Os.remove
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout


class Home : Fragment() {
    lateinit var vm: ContestViewModel
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
            .create(ContestViewModel::class.java)


        vm.myevent.observe(viewLifecycleOwner){
            if(it.size>0){
                shim.stopShimmer()
                shim.visibility=View.GONE
                re.visibility=View.VISIBLE

            }
            val ml:MutableList<CodeData> = mutableListOf()
            for(i in it){
                if(i.site!="HackerRank"&&i.site!="HackerEarth"){
                    ml.add(i);
                }
            }
            re.adapter=DataAdapter(ml)
            re.setHasFixedSize(true)
        }
        val list:MutableList<String> = mutableListOf("CODEFORCES","CODECHEF","LEETCODE","ATCODER","KICKSTART")
        myre.adapter=FilterAdapter(list)
        myre.setHasFixedSize(true)


        return v
    }


}