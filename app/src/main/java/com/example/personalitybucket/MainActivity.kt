package com.example.personalitybucket

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.myDrawerLayout)
        toggle = ActionBarDrawerToggle(this, drawerLayout,  R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView = findViewById<NavigationView>(R.id.navView)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_me -> Toast.makeText(applicationContext, "Already on page \"My Personality\"", Toast.LENGTH_SHORT).show()
                R.id.nav_my_traits -> this.startActivity(Intent(this, Traits::class.java))
                R.id.nav_my_accomplishments -> Toast.makeText(applicationContext, "Clicked Accomplishments", Toast.LENGTH_SHORT).show()
                R.id.nav_my_improvements_corner -> Toast.makeText(applicationContext, "Clicked Improvements Corner", Toast.LENGTH_SHORT).show()
                R.id.nav_information -> Toast.makeText(applicationContext, "Clicked Information", Toast.LENGTH_SHORT).show()
                R.id.nav_bucket -> Toast.makeText(applicationContext, "Clicked Bucket", Toast.LENGTH_SHORT).show()
            }
            true
        }

        val saveBtn = findViewById<Button>(R.id.saveBtn)

        saveBtn.setOnClickListener {
            saveData()
        }

        loadData()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveData(){
        var personName = findViewById<TextView>(R.id.PersonName).text.toString()
        var personSurname = findViewById<TextView>(R.id.PersonSurname).text.toString()
        var personDateOfBirth = findViewById<TextView>(R.id.PersonDoB).text.toString()
        var personHeight = findViewById<TextView>(R.id.PersonHeight).text.toString()
        var personWeight = findViewById<TextView>(R.id.PersonWeight).text.toString()
        var personStory = findViewById<TextView>(R.id.PersonStory).text.toString()

        val sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("PERSON_NAME", personName)
            putString("PERSON_SURNAME", personSurname)
            putString("PERSON_DOB", personDateOfBirth)
            putString("PERSON_HEIGHT", personHeight)
            putString("PERSON_WEIGHT", personWeight)
            putString("PERSON_STORY", personStory)
        }.apply()
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
        loadData()
    }

    private fun loadData(){
        val sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        var personName = sharedPreferences.getString("PERSON_NAME", null)
        var personSurname = sharedPreferences.getString("PERSON_SURNAME", null)
        var personDateOfBirth = sharedPreferences.getString("PERSON_DOB", null)
        var personHeight = sharedPreferences.getString("PERSON_HEIGHT", null)
        var personWeight = sharedPreferences.getString("PERSON_WEIGHT", null)
        var personStory = sharedPreferences.getString("PERSON_STORY", null)

        if (personName == "" || personSurname == ""){
            findViewById<TextView>(R.id.myNameSurname).text = "[Name, Surname]"
        }
        else if (personName == null || personSurname == null){
            findViewById<TextView>(R.id.myNameSurname).text = "[Name, Surname]"
        }
        else {
            findViewById<TextView>(R.id.myNameSurname).text = "$personName $personSurname"
        }

        findViewById<TextView>(R.id.PersonName).text = personName
        findViewById<TextView>(R.id.PersonSurname).text = personSurname
        findViewById<TextView>(R.id.PersonDoB).text = personDateOfBirth
        findViewById<TextView>(R.id.PersonHeight).text = personHeight
        findViewById<TextView>(R.id.PersonWeight).text = personWeight
        findViewById<TextView>(R.id.PersonStory).text = personStory
    }

}