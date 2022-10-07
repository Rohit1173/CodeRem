package com.example.coderem.profiles

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.ProfileViewModel
import com.example.coderem.ProfileViewModelFactory
import com.example.coderem.R
import com.example.coderem.database.User
import com.example.coderem.database.UserViewModel


class UserCodeChef : Fragment() {

    lateinit var vm: UserViewModel
    lateinit var user: User
    lateinit var pvm: ProfileViewModel
    lateinit var viewModelFactory: ProfileViewModelFactory
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_user_codechef, container, false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
        val uccId:TextView=v.findViewById(R.id.ucc_id)
        val cclogout:Button=v.findViewById(R.id.cclogout)
        val ccrating:TextView=v.findViewById(R.id.ccrating)
        val ccstars:TextView=v.findViewById(R.id.ccstars)
        val cc_highestRating:TextView=v.findViewById(R.id.cc_highestRating)
        val cc_globalRank:TextView=v.findViewById(R.id.cc_globalRank)
        val cc_countryRank:TextView=v.findViewById(R.id.cc_countryRank)



        vm.readccdata.observe(viewLifecycleOwner, Observer { mUser ->
            if(mUser!=null) {
                uccId.text = mUser.profile
                user = mUser
                viewModelFactory = ProfileViewModelFactory(user.profile)
                pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
                pvm.getCcData(user.profile)
                pvm.ccdataResponse.observe(viewLifecycleOwner, Observer {
                    ccrating.text="Rating : "+it.rating
                    ccstars.text="Stars : "+it.stars
                    cc_highestRating.text="Highest rating : "+it.highest_rating
                    cc_globalRank.text="Global rank : "+it.global_rank
                    cc_countryRank.text="Country rank : "+it.country_rank

                })
            }
        })


        cclogout.setOnClickListener {
            vm.deleteUser(user)
            Toast.makeText(requireContext(),"GOOD", Toast.LENGTH_LONG).show()
        }
        return v
    }


}