package com.example.jikan.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jikan.ViewModelFactory
import com.example.jikan.databinding.FragmentAnimeDetailsBinding
import com.example.jikan.network.AnimeApiService
import com.example.jikan.network.RetrofitInstance
import com.example.jikan.repo.AnimeRepository
import com.example.jikan.viewmodels.AnimeDetailsViewModel

import com.squareup.picasso.Picasso


class AnimeDetailsFragment : Fragment() {

    private var _binding: FragmentAnimeDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AnimeDetailsViewModel

    private var animeId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animeId = arguments?.getInt(ARG_ANIME_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val apiService = RetrofitInstance.getInstance().create(AnimeApiService::class.java)
        val repository = AnimeRepository(apiService)
        val factory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[AnimeDetailsViewModel::class.java]

        animeId?.let { id ->
            viewModel.fetchAnimeDetails(id)
            viewModel.animeDetails.observe(viewLifecycleOwner) { anime ->
                anime?.let {
                    binding.title.text = it.data.title
                    binding.synopsis.text = it.data.synopsis ?: "Synopsis not available"
                    if(it.data.cast!= null && !it.data.cast.isEmpty()) {
                        binding.mainCast.text =
                            "Main Cast: ${it.data.cast[0]?.character?.name ?: "N/A"}"
                    }else{
                        binding.mainCast.text = "Main Cast: N/A"
                    }
                    binding.genres.text = it.data.genres.joinToString(", ") { genre -> genre.name }
                    binding.episodes.text = "Episodes: ${it.data.episodes.toString()?: "N/A"}"
                    binding.rating.text = "Rating: ${it.data.score.toString() ?: "N/A"}"

                    val webView: WebView = binding.trailer
                    webView.settings.javaScriptEnabled = true

                    it.data.trailer?.url?.let { trailerUrl ->
                        binding.trailer.visibility = View.VISIBLE
                        webView.loadUrl(it.data.trailer?.embed_url.toString())
                    } ?: run {
                        binding.trailer.visibility = View.GONE
                        Picasso.get().load(it.data.images.jpg.image_url).into(binding.poster)
                    }
                } ?: run {
                    Toast.makeText(requireContext(), "Failed to load anime details", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_ANIME_ID = "anime_id"

        fun newInstance(animeId: Int): AnimeDetailsFragment {
            return AnimeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ANIME_ID, animeId)
                }
            }
        }
    }
}