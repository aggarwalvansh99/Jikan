package com.example.jikan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jikan.databinding.ItemAnimeBinding
import com.example.jikan.model.Anime
import com.squareup.picasso.Picasso

class AnimeAdapter(private val onClick: (Anime) -> Unit) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    private val animeList = mutableListOf<Anime>()

    fun submitList(list: List<Anime>) {
        animeList.clear()
        animeList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animeList[position])
    }

    override fun getItemCount() = animeList.size

    inner class AnimeViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(anime: Anime) {
            if(!anime.title.isEmpty()) {
                binding.title.text = anime.title
            }else{
                binding.title.text = "N/A"
            }
            if(anime.episodes!= null){
                binding.episodes.text = "Episodes: ${anime.episodes}"
            }else {
                binding.episodes.text = "N/A"
            }
            if(anime.score!= null){
                binding.rating.text = "Rating: ${anime.score}"
            }else{
                binding.rating.text = "N/A"
            }
            if(!anime.images.jpg.image_url.isEmpty()) {
                Picasso.get().load(anime.images.jpg.image_url).into(binding.poster)
            }
            binding.root.setOnClickListener { onClick(anime) }
        }
    }
}