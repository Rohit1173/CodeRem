package com.example.coderem

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.database.UserViewModel
import com.example.coderem.profiles.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var  drawer: DrawerLayout
    lateinit var vm:UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
        drawer=findViewById(R.id.drawer)
        val nav: NavigationView =findViewById(R.id.nav)
        val view: View = nav.getHeaderView(0)
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav.setCheckedItem(R.id.myHome)

        nav.setNavigationItemSelectedListener {

             it.isChecked=true
            when(it.itemId){

                R.id.myHome->{
                   replace(Home())
                }
                R.id.cc ->{
                    vm.readccdata.observe(this, Observer { user->
                        if(user==null){
                            replace(codechef())
                        }
                        else{
                            replace(UserCodeChef())
                        }
                        })

                }
                R.id.cf ->{
                    vm.readcfdata.observe(this, Observer { user->
                        if(user==null){
                            replace(codeforces())
                        }
                        else{
                            replace(UserCodeForces())
                        }
                    })

                }
                R.id.lc->{
                    vm.readlcdata.observe(this, Observer { user->
                        if(user==null){
                            replace(leetcode())
                        }
                        else{
                            replace(UserLeetCode())
                        }
                    })

                }
            }

            true
        }

    }
    private fun replace(fragment:Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frag,fragment)
        fragmentTransaction.commit()
        drawer.closeDrawers()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}