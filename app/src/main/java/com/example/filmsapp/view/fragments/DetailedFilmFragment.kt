package com.example.filmsapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.filmsapp.R
import com.example.filmsapp.databinding.FragmentDetailedFilmBinding

class DetailedFilmFragment : Fragment(R.layout.fragment_detailed_film) {

    private lateinit var binding: FragmentDetailedFilmBinding
    private val args by navArgs<DetailedFilmFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailedFilmBinding.inflate(inflater)

        binding.film = args.film

        return binding.root
    }
}