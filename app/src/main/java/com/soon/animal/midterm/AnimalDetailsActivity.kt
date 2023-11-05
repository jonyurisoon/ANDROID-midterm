package com.soon.animal.midterm

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soon.animal.midterm.databinding.ActivityAnimalDetailsBinding

class AnimalDetailsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityAnimalDetailsBinding // Use your view binding class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetailsBinding.inflate(layoutInflater) // Inflate the view using view binding
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")

        binding.nameTextView.text = name // Use view binding to access the views
        binding.descriptionTextView.text = description

        sharedPreferences = getSharedPreferences("BlockedAnimals", Context.MODE_PRIVATE)

        binding.blockAnimalButton.setOnClickListener {
            if (name != null) {
                saveBlockedAnimalName(name)
            }
            finish() // Close the activity
        }
    }

    private fun saveBlockedAnimalName(name: String) {
        val blockedAnimalsSet = sharedPreferences.getStringSet("blockedAnimals", HashSet())?.toMutableSet()
        blockedAnimalsSet?.add(name)
        sharedPreferences.edit().putStringSet("blockedAnimals", blockedAnimalsSet).apply()
    }
}