package com.example.coderem.profiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.ProfileViewModel
import com.example.coderem.ProfileViewModelFactory
import com.example.coderem.R
import com.example.coderem.database.User
import com.example.coderem.database.UserViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class codeforces : Fragment() {

    private lateinit var vm: UserViewModel
    lateinit var cflayout: TextInputLayout
    lateinit var cftext: TextInputEditText
    lateinit var cfbtn: Button
    lateinit var pvm: ProfileViewModel
    lateinit var viewModelFactory: ProfileViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_codeforces, container, false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
        cflayout=v.findViewById(R.id.cflayout)
        cftext=v.findViewById(R.id.cftext)
        cfbtn=v.findViewById(R.id.cfbtn)
        cftext.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotEmpty()) {
                cflayout.error = null
            }
        }
        cfbtn.setOnClickListener {
            if(cftext.text.toString().trim().isEmpty()) {
                cflayout.error="ID cannot be empty"

            }
            else {
                val user = User(0, "CodeForces", cftext.text.toString())
                viewModelFactory = ProfileViewModelFactory(cftext.text.toString().trim())
                pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
                pvm.getCfStatus(cftext.text.toString().trim())
                pvm.myResponse.observe(viewLifecycleOwner, Observer {
                    if(it.status.toString()=="Success") {
                        Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                        vm.addUser(user)
                    }
                    else{
                        cflayout.error="Invalid ID"
                    }
                })
            }
        } 
        return v
    }


}