package com.example.jikan.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jikan.MainActivity
import com.example.jikan.ViewModelFactory
import com.example.jikan.adapters.AnimeAdapter
import com.example.jikan.databinding.FragmentAnimeListBinding
import com.example.jikan.network.AnimeApiService
import com.example.jikan.network.RetrofitInstance
import com.example.jikan.repo.AnimeRepository
import com.example.jikan.viewmodels.AnimeListViewModel

class AnimeListFragment : Fragment() {

    private var _binding: FragmentAnimeListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AnimeListViewModel
    private lateinit var animeListAdapter: AnimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService = RetrofitInstance.getInstance().create(AnimeApiService::class.java)
        val repository = AnimeRepository(apiService)
        val factory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[AnimeListViewModel::class.java]
        animeListAdapter = AnimeAdapter { anime ->
            (activity as? MainActivity)?.navigateToAnimeDetails(anime.mal_id)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = animeListAdapter

        viewModel.fetchTopAnime().observe(viewLifecycleOwner){  response ->
            if (response != null) {
                animeListAdapter.submitList(response.body()!!.data)
            } else {
                Toast.makeText(requireContext(), "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}