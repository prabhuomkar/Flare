package io.github.prabhuomkar.torchexpo.util

import android.app.Activity
import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

class UIUtil {
    companion object {
        fun showSnackbar(context: Context, message: String, hasAction: Boolean = false) {
            val activity: Activity = context as Activity
            val snackbar = Snackbar.make(
                activity.findViewById(android.R.id.content),
                message, if (hasAction) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_SHORT
            )
            if (hasAction) {
                snackbar.setAction("OK", View.OnClickListener { snackbar.dismiss() })
                    .setActionTextColor(context.getColor(android.R.color.white))
            }
            snackbar.show()
        }
    }
}