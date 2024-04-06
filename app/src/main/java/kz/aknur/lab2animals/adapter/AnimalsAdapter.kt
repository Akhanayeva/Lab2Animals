package kz.aknur.lab2animals.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.aknur.lab2animals.databinding.AnimalItemBinding
import kz.aknur.lab2animals.model.Animal

class AnimalsAdapter : ListAdapter<Animal, AnimalsAdapter.AnimalViewHolder>(AnimalsDiff) {


    object AnimalsDiff : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AnimalItemBinding.inflate(layoutInflater, parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val dogBreed = getItem(position)
        holder.bind(dogBreed)
    }

    inner class AnimalViewHolder(private val binding: AnimalItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(animal: Animal) {
            with(binding) {
                tvScientificName.text = animal.name

                tvLocations.text = animal.locations?.joinToString(", ")

                tvPopulationSize.text = "Population: ${animal.characteristics?.estimated_population_size}"
                tvBiggestThreat.text = "Threat: ${animal.characteristics?.biggest_threat}"
                tvTopSpeed.text = "Speed: ${animal.characteristics?.top_speed}"
                tvLifespan.text = "Lifespan: ${animal.characteristics?.lifespan}"
            }
        }
    }
}