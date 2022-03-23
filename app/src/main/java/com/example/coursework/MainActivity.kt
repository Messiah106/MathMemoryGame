package com.example.coursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val about = findViewById<Button>(R.id.button)
        about.setOnClickListener {
            val intent = Intent(this, AboutMe::class.java)              //about me activity opens when about button clicked

            startActivity(intent)

            val nGame = findViewById<Button>(R.id.button2)
            nGame.setOnClickListener {
                val intent2 = Intent(this, GameScreen::class.java)          //GameScreen activity opens when New game button clicked

                startActivity(intent2)
            }
        }
    }
}