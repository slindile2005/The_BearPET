package com.example.the_bear

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    // Initial percentage values
    private var fedPercentage = 50
    private var happyPercentage = 50
    private var cleanPercentage = 50
    private var isPetDead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val petImageView: ImageView = findViewById(R.id.petImageView)
        val feedButton: Button = findViewById(R.id.feedButton)
        val cleanButton: Button = findViewById(R.id.cleanButton)
        val playButton: Button = findViewById(R.id.playButton)
        val deathImageView: ImageView = findViewById(R.id.deathImageView)


        petImageView.setImageResource(R.drawable.your_pet_image)

        feedButton.setOnClickListener {
            if (!isPetDead) {
                petImageView.setImageResource(R.drawable.feeding_image)


                if (fedPercentage < 100) fedPercentage += 5
                updateStatus()
            }
        }

        cleanButton.setOnClickListener {
            if (!isPetDead) {

                petImageView.setImageResource(R.drawable.pet_cleaning_image)


                if (cleanPercentage < 100) cleanPercentage += 5
                updateStatus()
            }
        }

        playButton.setOnClickListener {
            if (!isPetDead) {

                petImageView.setImageResource(R.drawable.pet_playing_image)


                if (happyPercentage < 100) happyPercentage += 5
                updateStatus()
            }
        }
    }

    private fun updateStatus() {

        findViewById<ProgressBar>(R.id.progressBarFed).progress = fedPercentage
        findViewById<ProgressBar>(R.id.progressBarHappy).progress = happyPercentage
        findViewById<ProgressBar>(R.id.progressBarClean).progress = cleanPercentage


        if (fedPercentage <= 50 && !isPetDead) {
            petDies()
        }
    }

    private fun petDies() {

        findViewById<ImageView>(R.id.deathImageView).apply {
            visibility = View.VISIBLE
            setImageResource(R.drawable.pet_dying_image)
        }
        isPetDead = true


        Handler().postDelayed({
            
            fedPercentage = 100
            happyPercentage = 100
            cleanPercentage = 100
            isPetDead = false

            findViewById<ImageView>(R.id.deathImageView).visibility = View.GONE
            findViewById<ProgressBar>(R.id.progressBarFed).progress = fedPercentage
            findViewById<ProgressBar>(R.id.progressBarHappy).progress = happyPercentage
            findViewById<ProgressBar>(R.id.progressBarClean).progress = cleanPercentage
        }, 1000)
    }
}