package com.soon.animal.midterm

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.soon.animal.midterm.adapters.BlockedAnimalAdapter
import com.soon.animal.midterm.databinding.ActivityManageBlockBinding
import com.soon.animal.midterm.models.BlockedAnimal

class ManageBlockActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityManageBlockBinding
    private lateinit var adapter: BlockedAnimalAdapter
    private val blockedAnimals = mutableListOf<BlockedAnimal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageBlockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("BlockedAnimals", Context.MODE_PRIVATE)

        adapter = BlockedAnimalAdapter(
            blockedAnimals,
            onItemClick = {

            }
        ) { blockedAnimalName ->
            // Handle the "Unblock" button click here
            unblockAnimal(blockedAnimalName)
        }

        binding.blockedAnimalsList.layoutManager = LinearLayoutManager(this)
        binding.blockedAnimalsList.adapter = adapter

        loadBlockedAnimals()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadBlockedAnimals() {
        val blockedAnimalsSet = sharedPreferences.getStringSet("blockedAnimals", HashSet()) ?: HashSet()
        val blockedAnimalNames = blockedAnimalsSet.toList()

        // Create a list of blocked animals using the names
        val sortedBlockedAnimals = blockedAnimalNames
            .map { BlockedAnimal(it, "Default Description") }
            .sortedBy { it.name }

        // Clear the current list and add the sorted blocked animals
        blockedAnimals.clear()
        blockedAnimals.addAll(sortedBlockedAnimals)

        // Refresh the RecyclerView
        adapter.notifyDataSetChanged()
    }

    private fun unblockAnimal(blockedAnimalName: String) {
        // Remove the animal from SharedPreferences
        val updatedBlockedAnimals = blockedAnimals.filter { it.name != blockedAnimalName }
        val editor = sharedPreferences.edit()
        editor.putStringSet("blockedAnimals", updatedBlockedAnimals.map { it.name }.toSet())
        editor.apply()

        // Refresh the RecyclerView
        loadBlockedAnimals()
    }
}