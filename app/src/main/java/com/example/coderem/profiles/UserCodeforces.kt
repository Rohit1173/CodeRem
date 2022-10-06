package com.example.coderem.profiles

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
import com.example.coderem.R
import com.example.coderem.database.User
import com.example.coderem.database.UserViewModel


class UserCodeForces : Fragment() {

    lateinit var vm: UserViewModel
    lateinit var user: User
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_user_codeforces, container, false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
        val ucfId: TextView =v.findViewById(R.id.ucf_id)
        val cflogout: Button =v.findViewById(R.id.cflogout)
        vm.readcfdata.observe(viewLifecycleOwner, Observer {
            if(it!=null) {
                ucfId.text = it.profile
                user = it
            }
        })
        cflogout.setOnClickListener {
            vm.deleteUser(user)
            Toast.makeText(requireContext(),"GOOD",Toast.LENGTH_LONG).show()
        }
        return v
    }

}