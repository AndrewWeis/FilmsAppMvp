package com.example.filmsapp.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Модель фильма
 */
@Parcelize
data class Film(
    var id: Long?,
    @SerializedName("localized_name")
    var localizedName: String?,
    var name: String?,
    var year: String?,
    var rating: Float?,
    @SerializedName("image_url")
    var imageUrl: String?,
    var description: String?,
    var genres: List<String>?
) : Parcelable