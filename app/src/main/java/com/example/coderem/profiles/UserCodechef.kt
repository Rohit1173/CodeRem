package com.example.coderem.profiles

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.MyCustomFormatter
import com.example.coderem.ProfileViewModel
import com.example.coderem.ProfileViewModelFactory
import com.example.coderem.database.database1.User
import com.example.coderem.database.database1.UserViewModel
import com.example.coderem.databinding.FragmentUserCodechefBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.*


class UserCodeChef : Fragment() {

    lateinit var vm: UserViewModel
    lateinit var user: User
    lateinit var pvm: ProfileViewModel
    lateinit var viewModelFactory: ProfileViewModelFactory
    lateinit var ccLineEntry:ArrayList<Entry>
    lateinit var ccLineDataSet: LineDataSet
    lateinit var ccLineData: LineData
    lateinit var binding: FragmentUserCodechefBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentUserCodechefBinding.inflate(inflater,container,false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
        ccLineEntry=ArrayList<Entry>()


        vm.readccdata.observe(viewLifecycleOwner, Observer { mUser ->
            if(mUser!=null) {
                binding.uccId.text = mUser.profile
                user = mUser
                viewModelFactory = ProfileViewModelFactory(user.profile)
                pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
                pvm.getCcData(user.profile)
                pvm.ccdataResponse.observe(viewLifecycleOwner, Observer {

                        binding.ccrating.text = "Rating : " + it.rating
                        binding.ccstars.text = "Stars : " + it.stars
                        binding.ccHighestRating.text = "Highest rating : " + it.highest_rating
                        binding.ccGlobalRank.text = "Global rank : " + it.global_rank
                        binding.ccCountryRank.text = "Country rank : " + it.country_rank

                    binding.ccLineChart.xAxis.valueFormatter= MyCustomFormatter()
                    var cnt=1f
                    for(i in it.contest_ratings){
                        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                        val e1 = format.parse(i.end_date)
                        ccLineEntry.add(Entry(e1!!.time.toFloat(),i.rating.toFloat()))
                        cnt+=2
                    }


                    ccLineDataSet = LineDataSet(ccLineEntry,"First")
                    ccLineData= LineData(ccLineDataSet)
                    ccLineDataSet.color= Color.WHITE
                    binding.ccLineChart.data=ccLineData
                    ccLineDataSet.valueTextColor= Color.BLACK
                    ccLineDataSet.valueTextSize=15f
                    ccLineData.setDrawValues(false)
                    binding.ccLineChart.animateX(2000)



                })
            }
        })



        binding.cclogout.setOnClickListener {
            vm.deleteUser(user)
            Toast.makeText(requireContext(),"GOOD", Toast.LENGTH_LONG).show()
        }
        return binding.root
    }


}