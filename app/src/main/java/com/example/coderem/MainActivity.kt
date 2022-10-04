package com.example.coderem

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var  drawer: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawer=findViewById(R.id.drawer)
        val nav: NavigationView =findViewById(R.id.nav)
        val view: View = nav.getHeaderView(0)
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav.setCheckedItem(R.id.myHome)
        //nav.checkedItem!!.isChecked = false

        nav.setNavigationItemSelectedListener {

             it.isChecked=true
            when(it.itemId){

                R.id.myHome->{
                   replace(home())
                }
                R.id.cc ->{
                   replace(codechef())
                }
                R.id.cf ->{
                    replace(codeforces())
                }
                R.id.lc->{
                    replace(leetcode())
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