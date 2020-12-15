package com.example.personalitybucket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Traits : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traits)

        val drawerLayout = findViewById<DrawerLayout>(R.id.myDrawerLayout)
        toggle = ActionBarDrawerToggle(this, drawerLayout,  R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView = findViewById<NavigationView>(R.id.navViewTraits)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_me -> this.startActivity(Intent(this, MainActivity::class.java))
                R.id.nav_my_traits -> Toast.makeText(applicationContext, "Already on page \"Traits\"", Toast.LENGTH_SHORT).show()
                R.id.nav_my_accomplishments -> Toast.makeText(applicationContext, "Clicked Accomplishments", Toast.LENGTH_SHORT).show()
                R.id.nav_my_improvements_corner -> Toast.makeText(applicationContext, "Clicked Improvements Corner", Toast.LENGTH_SHORT).show()
                R.id.nav_information -> Toast.makeText(applicationContext, "Clicked Information", Toast.LENGTH_SHORT).show()
                R.id.nav_bucket -> Toast.makeText(applicationContext, "Clicked Bucket", Toast.LENGTH_SHORT).show()
            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}