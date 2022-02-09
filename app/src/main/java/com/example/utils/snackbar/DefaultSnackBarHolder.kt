import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import com.example.filmsapp.R
import com.example.utils.ResourcesUtils.getString
import com.example.utils.snackbar.SnackBarActionListener
import com.example.utils.snackbar.SnackBarHolder
import com.google.android.material.snackbar.Snackbar

/**
 * Держатель [Snackbar] с разметкой по умолчанию
 */
class DefaultSnackBarHolder(
    lifecycleOwner: LifecycleOwner,
    view: View,
    customView: View?
) : SnackBarHolder(lifecycleOwner, view, customView) {

    override fun createSnackBar(
        view: View,
        customView: View?,
        message: String,
        actionText: String?,
        duration: Int,
        actionListener: SnackBarActionListener?,
        snackBarCallback: Snackbar.Callback
    ): Snackbar {

        return if (customView == null) {
            setUpDefaultSnackBar(view, message, actionText, duration, actionListener, snackBarCallback)
        } else {
            setUpCustomSnackBar(view, customView, message, duration, actionListener, snackBarCallback)
        }
    }

    private fun setUpDefaultSnackBar(
        view: View,
        message: String,
        actionText: String?,
        duration: Int,
        actionListener: SnackBarActionListener?,
        snackBarCallback: Snackbar.Callback
    ): Snackbar {
        return Snackbar.make(view, message, duration)
            .addCallback(snackBarCallback)
            .setAction(actionText) {
                actionListener?.onActionClick()
                hideMessage()
            }
    }

    private fun setUpCustomSnackBar(
        view: View,
        customView: View,
        message: String,
        duration: Int,
        actionListener: SnackBarActionListener?,
        snackBarCallback: Snackbar.Callback
    ): Snackbar {
        val snackbar = Snackbar.make(view, message, duration)
            .addCallback(snackBarCallback)

        val snackbarLayout: Snackbar.SnackbarLayout = snackbar.view as Snackbar.SnackbarLayout
        snackbarLayout.setPadding(0, 0, 0, 0)
        snackbarLayout.addView(customView)

        val snackbarButton = customView.findViewById<Button>(R.id.snackbar_action_button)
        snackbarButton.text = getString(R.string.button_repeat)
        snackbarButton.setOnClickListener {
            actionListener?.onActionClick()
            snackbarLayout.removeAllViews()
            hideMessage()
        }

        val snackbarMessage = customView.findViewById<TextView>(R.id.snackbar_message_text)
        snackbarMessage.text = message

        return snackbar
    }
}