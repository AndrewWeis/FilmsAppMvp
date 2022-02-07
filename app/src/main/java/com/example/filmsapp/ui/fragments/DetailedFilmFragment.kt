package com.example.filmsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.filmsapp.R
import com.example.filmsapp.databinding.DetailedFilmFragmentBinding
import com.example.filmsapp.ui.data.image_loader.ImageLoader
import com.example.filmsapp.ui.list.entities.Film
import com.example.filmsapp.ui.utils.ResourcesUtils
import com.example.filmsapp.ui.utils.firstCharToLowerCase

/**
 * Fragment с подробным описанием фильма
 */
class DetailedFilmFragment : Fragment(R.layout.detailed_film_fragment) {

    private var _binding: DetailedFilmFragmentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailedFilmFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        _binding = DetailedFilmFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setDataToViews(args.film)
        setUpToolBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setDataToViews(film: Film) {
        binding.titleText.text = film.localizedName
        binding.yearGenreText.text = formYearGenreText(film.genres, film.year)
        binding.rateText.text = formRatingText(film.rating)
        binding.descriptionText.text = film.description

        ImageLoader
            .load(film.imageUrl)
            .error(R.drawable.img_not_found)
            .placeholder(R.drawable.img_not_found)
            .centerCrop()
            .roundedCorners(ResourcesUtils.getPxByDp(4f))
            .into(binding.previewImage)
    }

    /**
     * Формирует текст для строки рейтинга
     */
    private fun formRatingText(rating: Float?): String {
        var str = ""
        str += rating?.toString()?.dropLast(2) ?: "-"
        return str
    }

    /**
     * Формирует текст для строки жанра с годом
     */
    private fun formYearGenreText(genres: List<String>?, year: String?): String {
        var str = ""
        genres?.forEach { str += "${it.firstCharToLowerCase()}, " }
        str += year + " " + getString(R.string.year)
        return str
    }

    private fun setUpToolBar() {
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}