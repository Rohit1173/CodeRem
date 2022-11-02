package com.example.coderem.profiles

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.ProfileViewModel
import com.example.coderem.ProfileViewModelFactory
import com.example.coderem.database.database1.User
import com.example.coderem.database.database1.UserViewModel
import com.example.coderem.databinding.FragmentUserCodeforcesBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class UserCodeForces : Fragment() {

    lateinit var vm: UserViewModel
    lateinit var user: User
    lateinit var pvm: ProfileViewModel
    lateinit var viewModelFactory: ProfileViewModelFactory
    lateinit var cfLineEntry:ArrayList<Entry>
    lateinit var cfLineDataSet: LineDataSet
    lateinit var cfLineData: LineData
    lateinit var binding: FragmentUserCodeforcesBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUserCodeforcesBinding.inflate(inflater,container,false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]

        cfLineEntry=ArrayList<Entry>()

        vm.readcfdata.observe(viewLifecycleOwner, Observer { mUser ->
            if(mUser!=null) {
                binding.ucfId.text = mUser.profile
                user = mUser
                viewModelFactory = ProfileViewModelFactory(user.profile)
                pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
                pvm.getCfData(user.profile)
                pvm.cfdataResponse.observe(viewLifecycleOwner, Observer {
                    binding.cfRating.text="Rating : "+it.rating
                    binding.cfMaxRating.text="Max rating : "+it.max_rating
                    binding.cfRank.text="Rank : "+it.rank
                    binding.cfMaxRating.text="Max Rank : "+it.max_rank
                    var cnt=1f
                    for(i in it.contests.reversed()){
                        cfLineEntry.add(Entry(cnt,i.new_rating.toFloat()))
                        cnt+=2
                    }
                    cfLineDataSet = LineDataSet(cfLineEntry,"First")
                    cfLineData= LineData(cfLineDataSet)
                    cfLineDataSet.color= Color.WHITE
                    binding.cfLineChart.data=cfLineData
                    cfLineDataSet.valueTextColor= Color.BLACK
                    cfLineDataSet.valueTextSize=15f
                    cfLineData.setDrawValues(false)
                    binding.cfLineChart.animateX(2000)

                })
            }
        })
        binding.cflogout.setOnClickListener {
            vm.deleteUser(user)
            Toast.makeText(requireContext(),"GOOD",Toast.LENGTH_LONG).show()
        }
        return binding.root
    }

}