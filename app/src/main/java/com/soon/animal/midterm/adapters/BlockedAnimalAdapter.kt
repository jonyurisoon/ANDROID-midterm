package com.soon.animal.midterm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soon.animal.midterm.databinding.ItemBlockedAnimalBinding
import com.soon.animal.midterm.models.BlockedAnimal

class BlockedAnimalAdapter(
    private val blockedAnimals: List<BlockedAnimal>,
    private val onItemClick: (String) -> Unit,
    private val onUnblockClick: (String) -> Unit
) : RecyclerView.Adapter<BlockedAnimalAdapter.BlockedAnimalViewHolder>() {

    inner class BlockedAnimalViewHolder(private val binding: ItemBlockedAnimalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(blockedAnimal: BlockedAnimal) {
            binding.blockedAnimalName.text = blockedAnimal.name

            binding.root.setOnClickListener {
                onItemClick(blockedAnimal.name)
            }

            binding.unblockButton.setOnClickListener {
                onUnblockClick(blockedAnimal.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockedAnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBlockedAnimalBinding.inflate(inflater, parent, false)
        return BlockedAnimalViewHolder(binding)
    }

    override fun getItemCount() = blockedAnimals.size

    override fun onBindViewHolder(holder: BlockedAnimalViewHolder, position: Int) {
        val blockedAnimal = blockedAnimals[position]
        holder.bind(blockedAnimal)
    }
}
