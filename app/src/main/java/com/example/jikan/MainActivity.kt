package com.example.jikan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jikan.databinding.ActivityMainBinding
import com.example.jikan.view.AnimeDetailsFragment
import com.example.jikan.view.AnimeListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load AnimeListFragment on app start
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AnimeListFragment())
                .commit()
        }
    }

    fun navigateToAnimeDetails(animeId: Int) {
        val detailsFragment = AnimeDetailsFragment.newInstance(animeId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailsFragment)
            .addToBackStack(null) // Add transaction to back stack
            .commit()
    }
}