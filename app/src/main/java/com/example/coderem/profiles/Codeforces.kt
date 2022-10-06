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


class codeforces : Fragment() {

    private lateinit var vm: UserViewModel
    lateinit var cflayout: TextInputLayout
    lateinit var cftext: TextInputEditText
    lateinit var cfbtn: Button
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

        cfbtn.setOnClickListener {
            val user = User(0,"CodeForces",cftext.text.toString())
            vm.addUser(user)
            Toast.makeText(requireContext(),"SUCCESS!!!", Toast.LENGTH_LONG).show()
        } 
        return v
    }


}