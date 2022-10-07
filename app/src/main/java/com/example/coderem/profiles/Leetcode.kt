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

class leetcode : Fragment() {

    private lateinit var vm: UserViewModel
    lateinit var lclayout: TextInputLayout
    lateinit var lctext: TextInputEditText
    lateinit var lcbtn: Button
    lateinit var pvm: ProfileViewModel
    lateinit var viewModelFactory: ProfileViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_leetcode, container, false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
        lclayout=v.findViewById(R.id.lclayout)
        lctext=v.findViewById(R.id.lctext)
        lcbtn=v.findViewById(R.id.lcbtn)
        lctext.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotEmpty()) {
                lclayout.error = null
            }
        }
        lcbtn.setOnClickListener {
            if(lctext.text.toString().trim().isEmpty()) {
                lclayout.error="ID cannot be empty"

            }
            else {

                viewModelFactory = ProfileViewModelFactory(lctext.text.toString().trim())
                pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
                pvm.getLcStatus(lctext.text.toString().trim())
                pvm.lcResponse.observe(viewLifecycleOwner, Observer {

                    if(it!=null) {
                        if (it.status.toString() == "Success") {
                            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG)
                                .show()
                            val user = User(0, "LeetCode", lctext.text.toString())
                            vm.addUser(user)
                        } else {
                            lclayout.error = "Invalid ID"
                        }
                    }
                    else{
                        Toast.makeText(requireContext(), "Try Again", Toast.LENGTH_LONG)
                            .show()
                    }
                })
            }
        }
        return v
    }
}