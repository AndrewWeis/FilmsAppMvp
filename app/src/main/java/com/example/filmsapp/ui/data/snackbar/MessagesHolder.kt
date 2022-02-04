package com.example.filmsapp.ui.data.snackbar

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.filmsapp.R
import com.example.filmsapp.ui.utils.ResourcesUtils.getString

/**
 * Держатель сообщений разных типов
 */
class MessagesHolder(
    lifecycleOwner: LifecycleOwner,
    view: View
) {
    private var snackBarHolder =
        DefaultSnackBarHolder(lifecycleOwner, view)

    private var infoSnackBarHolder = DefaultSnackBarHolder(lifecycleOwner, view)

    fun showNetworkError(message: String) {
        snackBarHolder.showLongDurationMessage(message)
    }

    fun showUnhiddenNetworkError(message: String, listener: () -> Unit) {
        snackBarHolder.showIndefiniteDurationMessage(
            message,
            getString(R.string.button_repeat),
            object : SnackBarActionListener {
                override fun onActionClick() {
                    listener()
                }
            }
        )
    }

    fun showMessage(message: String) {
        snackBarHolder.showLongDurationMessage(message, getString(R.string.button_ok))
    }

    fun showInfoMessage(message: String) {
        infoSnackBarHolder.showShortDurationMessage(message, getString(R.string.button_ok))
    }
}