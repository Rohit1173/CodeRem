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


class UserCodeForces : Fragment() {

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
        val v= inflater.inflate(R.layout.fragment_user_codeforces, container, false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
        val ucfId: TextView =v.findViewById(R.id.ucf_id)
        val cflogout: Button =v.findViewById(R.id.cflogout)
        val cfRating:TextView=v.findViewById(R.id.cf_rating)
        val cf_maxRating:TextView=v.findViewById(R.id.cf_maxRating)
        val cf_rank:TextView=v.findViewById(R.id.cf_rank)
        val cf_maxRank:TextView=v.findViewById(R.id.cf_maxRank)
        vm.readcfdata.observe(viewLifecycleOwner, Observer { mUser ->
            if(mUser!=null) {
                ucfId.text = mUser.profile
                user = mUser
                viewModelFactory = ProfileViewModelFactory(user.profile)
                pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
                pvm.getCfData(user.profile.toString())
                pvm.cfdataResponse.observe(viewLifecycleOwner, Observer {
                    cfRating.text="Rating : "+it.rating
                    cf_maxRating.text="Max rating : "+it.max_rating
                    cf_rank.text="Rank : "+it.rank
                    cf_maxRank.text="Max Rank : "+it.max_rank

                })
            }
        })
        cflogout.setOnClickListener {
            vm.deleteUser(user)
            Toast.makeText(requireContext(),"GOOD",Toast.LENGTH_LONG).show()
        }
        return v
    }

}