package com.example.coderem.profiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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
        lcbtn.setOnClickListener {
            val user = User(0,"LeetCode",lctext.text.toString())
            vm.addUser(user)
            Toast.makeText(requireContext(),"SUCCESS!!!", Toast.LENGTH_LONG).show()
        }
        return v
    }
}