package com.amrdeveloper.askme.views

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.amrdeveloper.askme.R
import com.amrdeveloper.askme.databinding.ActivityMainBinding
import com.amrdeveloper.askme.extensions.notNull
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var mActionBar: ActionBar? = null
    private lateinit var mMainActivity: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainActivity = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mActionBar = supportActionBar
        mMainActivity.mainNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelection)
    }

    private val onNavigationItemSelection =
        BottomNavigationView.OnNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.navigation_home -> {
                    mActionBar.notNull { it.title = "Home" }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    mActionBar.notNull { it.title = "Notifications" }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_people -> {
                    mActionBar.notNull { it.title = "People" }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    mActionBar.notNull { it.title = "Profile" }
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}
