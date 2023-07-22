package com.bca.music.player.core.helper.util


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.bca.music.player.R

class CustomSnackbarView(
    parent: ViewGroup,
    content: BaseSnackbarView
) : BaseTransientBottomBar<CustomSnackbarView>(parent, content, content) {
    init {
        getView().setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                android.R.color.transparent
            )
        )
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {

        fun make(
            viewGroup: ViewGroup,
            text: String,
            snackbarType: SnackbarType,
            showClose: Boolean = true,
            dismissCallback: () -> Unit
        ): CustomSnackbarView {
            val customView = LayoutInflater.from(viewGroup.context).inflate(
                R.layout.snackbar_default_layout,
                viewGroup,
                false
            ) as BaseSnackbarView
            val snackbarClose = customView.findViewById<ImageView>(R.id.snackbarClose)
            val snackbarIcon = customView.findViewById<ImageView>(R.id.snackbarIcon)
            val snackbarTxt = customView.findViewById<TextView>(R.id.snackbarTxt)
            customView.setPadding(0, 0, 0, 0)
            snackbarClose.isVisible = showClose
            snackbarTxt.text = text
            customView.apply {
                when (snackbarType) {
                    SnackbarType.INFO -> {
                        setBackgroundColor(
                            ContextCompat.getColor(
                                viewGroup.context,
                                R.color.blue
                            )
                        )
                        snackbarIcon.setImageDrawable(
                            ContextCompat.getDrawable(
                                viewGroup.context,
                                R.drawable.ic_info_white
                            )
                        )
                    }

                    SnackbarType.ERROR -> {
                        setBackgroundColor(
                            ContextCompat.getColor(
                                viewGroup.context,
                                R.color.red
                            )
                        )
                        snackbarIcon.setImageDrawable(
                            ContextCompat.getDrawable(
                                viewGroup.context,
                                R.drawable.ic_info_white
                            )
                        )
                    }

                    SnackbarType.SUCCESS -> {
                        setBackgroundColor(
                            ContextCompat.getColor(
                                viewGroup.context,
                                R.color.green
                            )
                        )
                        snackbarIcon.setImageDrawable(
                            ContextCompat.getDrawable(
                                viewGroup.context,
                                R.drawable.ic_checklist_circle_white
                            )
                        )
                    }
                }
            }

            return CustomSnackbarView(viewGroup, customView).apply {
                snackbarClose.setOnClickListener {
                    this.dismiss()
                }
            }.apply {
                addCallback(object : BaseCallback<CustomSnackbarView?>() {
                    override fun onDismissed(
                        transientBottomBar: CustomSnackbarView?,
                        event: Int
                    ) {
                        if (event == DISMISS_EVENT_MANUAL || event == DISMISS_EVENT_TIMEOUT) {
                            dismissCallback()
                        }
                    }
                })
            }
        }
    }

    enum class SnackbarType {
        INFO, ERROR, SUCCESS
    }
}
