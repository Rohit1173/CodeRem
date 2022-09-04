package com.example.coderem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var vm: ViewModel
    lateinit var re:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       re=findViewById(R.id.recycler)
        supportActionBar!!.hide()
        vm = ViewModelProvider.AndroidViewModelFactory(application)
            .create(ViewModel::class.java)

        vm.myevent.observe(this){
            re.adapter=DataAdapter(it)
            re.setHasFixedSize(true)
        }
    }
}