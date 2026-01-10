package com.jaydeep.bookhub.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.jaydeep.bookhub.fragment.AboutAppFragment
import com.jaydeep.bookhub.fragment.DashboardFragment
import com.jaydeep.bookhub.fragment.FavouriteFragment
import com.jaydeep.bookhub.fragment.ProfileFragment
import com.jaydeep.bookhub.R
import com.jaydeep.bookhub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    var previousMenuItem: MenuItem?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        toolbar=binding.toolbar
        drawerLayout=binding.main
        navigationView= binding.navigationView
        setUpToolbar()

        //open dashboard
        openDashboard()


        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            if(previousMenuItem !=null){
                previousMenuItem?.isChecked= false
            }

            it.isCheckable=true
            it.isChecked=true
            previousMenuItem= it

            when(it.itemId){
                R.id.dashboard ->{
                    openDashboard()
                    drawerLayout.closeDrawers()
                }

                R.id.profile ->{
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame, ProfileFragment()
                        ).commit()
                    supportActionBar?.title="Profile"

                    drawerLayout.closeDrawers()
                }
                R.id.favourites ->{
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame, FavouriteFragment()
                        ).commit()
                    supportActionBar?.title="Favourites"

                    drawerLayout.closeDrawers()
                }
                R.id.about_app ->{
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame, AboutAppFragment()
                        ).commit()
                    supportActionBar?.title="About App"
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }



    }

    fun setUpToolbar(){
        setSupportActionBar(toolbar)

        supportActionBar?.title="Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id== android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun openDashboard(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, DashboardFragment())
            .commit()
        supportActionBar?.title="Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)
    }

    override fun onBackPressed() {
        val frag= supportFragmentManager.findFragmentById(R.id.frame)

        when(frag){
            !is DashboardFragment -> openDashboard()
            else -> super.onBackPressed()
        }
    }
}