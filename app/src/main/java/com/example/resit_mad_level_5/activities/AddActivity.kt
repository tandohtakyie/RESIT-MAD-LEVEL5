package com.example.resit_mad_level_5.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.resit_mad_level_5.R
import com.example.resit_mad_level_5.model.Game
import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.activity_add_game.view.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        add_button.setOnClickListener{
            onAddGame()
        }
        initialViews()
    }

    private fun initialViews(){
        supportActionBar?.title = "Add Activity"
    }

    private fun onAddGame(){
        val months = arrayListOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        val totalDays = 1..31
        val totalMonths = 1..12
        val years = 1900..2025

        var title = etTitle.title.text.toString()
        var platform = etPlatform.platform.text.toString()
        var day = etDay.day.text.toString()
        var month = etMonth.month.text.toString()
        var year = etYear.year.text.toString()

        if (title.isNotBlank() && platform.isNotBlank() && day.isNotBlank() && month.isNotBlank() && year.isNotBlank()) {
           if (day.toInt() in totalDays && month.toInt() in totalMonths && year.toInt() in years){
               val game = Game(title, platform, day.toInt(), months[month.toInt() - 1], year.toInt())

               val resultIntent = Intent()
               resultIntent.putExtra(EXTRA_GAME, game)
               setResult(Activity.RESULT_OK, resultIntent)
               finish()
           } else {
               Toast.makeText(this,"Please fill in a valid date.", Toast.LENGTH_LONG).show()
           }
        }else if (!title.isNotBlank()){
            Toast.makeText(this,"Please fill in a title.", Toast.LENGTH_LONG).show()
        } else if (!platform.isNotBlank()){
            Toast.makeText(this,"Please fill in a platform.", Toast.LENGTH_LONG).show()
        } else if (!day.isNotBlank() || !month.isNotBlank() || !year.isNotBlank()){
            Toast.makeText(this,"Please fill in a date.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this,"Multiple fields are empty. Please fill them in.", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val EXTRA_GAME = "EXTRA_GAME"
    }


}
