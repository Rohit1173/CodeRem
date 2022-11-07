package com.example.coderem

import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.database.database1.UserViewModel
import com.example.coderem.databinding.ActivityMainBinding
import com.example.coderem.profiles.*

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    lateinit var vm:UserViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportActionBar!!.title=""

        vm = ViewModelProvider(this)[UserViewModel::class.java]
        val view: View = binding.nav.getHeaderView(0)
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.open, R.string.close)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        binding.toolbar.setNavigationIcon(R.drawable.ic_icon_menu)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.nav.setCheckedItem(R.id.myHome)
        binding.nav.itemIconTintList=null

        binding.nav.setNavigationItemSelectedListener {

            it.isChecked=true
            when(it.itemId){

                R.id.myHome->{
                    binding.tooltext.text="CodeRem"
                    binding.tooltext.typeface= ResourcesCompat.getFont(this, R.font.satisfy_regular)
                    binding.tooltext.textSize= 32F
                    replace(Home())
                }
                R.id.cc ->{
                    binding.tooltext.text="CodeChef"
                    binding.tooltext.typeface= ResourcesCompat.getFont(this, R.font.nunito_extrabold)
                    binding.tooltext.textSize= 22F
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
                    binding.tooltext.text="CodeForces"
                    binding.tooltext.typeface= ResourcesCompat.getFont(this, R.font.nunito_extrabold)
                    binding.tooltext.textSize= 22F
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
                    binding.tooltext.text="LeetCode"
                    binding.tooltext.typeface= ResourcesCompat.getFont(this, R.font.nunito_extrabold)
                    binding.tooltext.textSize= 22F
                    vm.readlcdata.observe(this, Observer { user->
                        if(user==null){
                            replace(leetcode())
                        }
                        else{
                            replace(UserLeetCode())
                        }
                    })

                }
                R.id.settings->{
                    binding.tooltext.text="Settings"
                    binding.tooltext.typeface= ResourcesCompat.getFont(this, R.font.nunito_extrabold)
                    binding.tooltext.textSize= 22F
                    replace(settings())
                }
                R.id.faq->{
                    binding.tooltext.text="FAQ"
                    binding.tooltext.typeface= ResourcesCompat.getFont(this, R.font.nunito_extrabold)
                    binding.tooltext.textSize= 22F
                    replace(faq())
                }
                R.id.support->{
                    binding.tooltext.text="Support"
                    binding.tooltext.typeface=  ResourcesCompat.getFont(this, R.font.nunito_extrabold )
                    binding.tooltext.textSize= 22F
                    replace(support())
                }

            }

            true
        }

    }
    private fun replace(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frag,fragment)
        fragmentTransaction.commit()
        binding.drawer.closeDrawers()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    var time = 0L
    override fun onBackPressed() {
        if (time + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(
                this, "Press once again to exit",
                Toast.LENGTH_SHORT
            ).show()
            time = System.currentTimeMillis()
        }
    }
}