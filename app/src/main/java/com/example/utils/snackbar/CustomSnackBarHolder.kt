package com.example.utils.snackbar

import android.view.View
import android.widget.Button
import androidx.core.view.allViews
import androidx.lifecycle.LifecycleOwner
import com.example.utils.ResourcesUtils.getColor
import com.google.android.material.snackbar.Snackbar

/**
 * Держатель [Snackbar] с настраиваемой разметкой
 */
class CustomSnackBarHolder(
    lifecycleOwner: LifecycleOwner,
    view: View,
    settings: SnackBarSettings = SnackBarSettings()
) : SnackBarHolder(lifecycleOwner, view, settings) {

    override fun createSnackBar(
        view: View,
        message: String,
        actionText: String?,
        duration: Int,
        actionListener: SnackBarActionListener?,
        snackBarCallback: Snackbar.Callback,
        settings: SnackBarSettings
    ): Snackbar {
        val snackbar = Snackbar.make(view, message, duration)
            .addCallback(snackBarCallback)
            .setAction(actionText) {
                actionListener?.onActionClick()
                hideMessage()
            }

        customizeSnackBar(snackbar, settings)

        return snackbar
    }

    /**
     * Настраивает snackbar в соответствии с [SnackBarSettings]
     *
     * @param snackbar для отображения сообщений
     * @param settings для настраивания snackbar
     */
    private fun customizeSnackBar(snackbar: Snackbar, settings: SnackBarSettings) {
        val snackbarLayout: Snackbar.SnackbarLayout =
            snackbar.view as Snackbar.SnackbarLayout

        val button = snackbarLayout.allViews.find { it is Button }

        settings.buttonTextColor?.let {
            (button as Button).setTextColor(getColor(it))
        }

        settings.backgroundColor?.let {
            snackbarLayout.setBackgroundColor(getColor(it))
        }

        settings.letterSpacing?.let {
            (button as Button).letterSpacing = it
        }
    }
}