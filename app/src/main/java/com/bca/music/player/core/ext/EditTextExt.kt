package com.bca.music.player.core.ext

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.EditText
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.disposables.Disposable

fun EditText.showVisibleIcon(leftDrawable : Int, rightDrawable : Int) : Disposable {
    return  this.textChanges().subscribe {
        if (it.isNotEmpty()){
            this.setCompoundDrawablesWithIntrinsicBounds(leftDrawable,0,rightDrawable,0)
        }else{
            this.setCompoundDrawablesWithIntrinsicBounds(leftDrawable,0,0,0)
        }
    }
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.showHidePassword(actionClick : (Boolean) -> Unit)  {
    var hasShow = false
    this.setOnTouchListener { v, event ->
        var hasConsumed = false
        if (v is EditText) {
            if (event.x >= v.width - v.totalPaddingRight) {
                if (event.action == MotionEvent.ACTION_UP) {
                    if (!hasShow){
                        actionClick.invoke(true)
                        hasShow = true
                    }else{
                        actionClick.invoke(false)
                        hasShow = false
                    }
                }
                hasConsumed = true
            }
        }
        hasConsumed
    }
}