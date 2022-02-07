package com.example.filmsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.filmsapp.R
import com.example.filmsapp.databinding.DetailedFilmFragmentBinding
import com.example.filmsapp.ui.data.image_loader.ImageLoader
import com.example.filmsapp.ui.fragments.base.BaseWithAppBarNavigationFragment
import com.example.filmsapp.ui.list.entities.Film
import com.example.filmsapp.ui.utils.ResourcesUtils
import com.example.filmsapp.ui.utils.firstCharToLowerCase
import com.google.android.material.textview.MaterialTextView
import com.sequenia.app_bar_provider.AppBarSettings

/**
 * Fragment с подробным описанием фильма
 */
class DetailedFilmFragment :
    BaseWithAppBarNavigationFragment(),
    AppBarSettings {

    private var _binding: DetailedFilmFragmentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailedFilmFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailedFilmFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        appBarProvider?.setAppBarSettings(this)

        val toolbarTitle =
            appBarProvider?.setCustomToolbarView(R.layout.toolbar_title_view) as MaterialTextView

        toolbarTitle.text = args.film.name
    }

    override fun isBackButtonVisible(): Boolean {
        return true
    }
}