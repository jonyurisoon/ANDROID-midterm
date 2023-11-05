package com.soon.animal.midterm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.soon.animal.midterm.adapters.AnimalAdapter
import com.soon.animal.midterm.databinding.ActivityAnimalNamesBinding
import com.soon.animal.midterm.models.Animal

class AnimalNamesActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityAnimalNamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalNamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("BlockedAnimals", Context.MODE_PRIVATE)

        // Retrieve the list of blocked animal names from SharedPreferences
        val blockedAnimalsSet = sharedPreferences.getStringSet("blockedAnimals", HashSet()) ?: HashSet()
        val blockedAnimalNames = blockedAnimalsSet.toList()

        // Create a list of animals (you can populate this list as needed)
        var animals: List<Animal> = listOf(
            Animal("Fox", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
            Animal("Dog", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
            Animal("Cat", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
            Animal("Bear", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
            Animal("Antelope", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
            Animal("Elephant", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")

        )

        // Sort the animals list by name in alphabetical order and assign it back to animals
        animals = animals.sortedBy { it.name }

        // Filter out the blocked animals
        val filteredAnimals = animals.filter { it.name !in blockedAnimalNames }.toMutableList()

        val adapter = AnimalAdapter(filteredAnimals, this)
        binding.animalList.layoutManager = LinearLayoutManager(this)
        binding.animalList.adapter = adapter

        binding.manageBlockedAnimalsButton.setOnClickListener {
            // Launch the ManageBlockActivity
            val intent = Intent(this, ManageBlockActivity::class.java)
            startActivity(intent)
        }
    }
}
