/*******************************************************************************
 * Created by Yana Ostapenko, 2020
 ******************************************************************************/

package ru.skillbranch.devintensive.extensions

/*
import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.math.roundToInt

*/

/*
fun Activity.hideKeyboard(view: View){
        val inputMethodManager= getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

}

fun Activity.getRootView(): View {
        return findViewById<View>(android.R.id.content)
}
fun Context.convertDpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                this.resources.displayMetrics
        )
}
fun Activity.isKeyboardOpen(): Boolean {
        val visibleBounds = Rect()
        this.getRootView().getWindowVisibleDisplayFrame(visibleBounds)
        val heightDiff = getRootView().height - visibleBounds.height()
        val marginOfError = this.convertDpToPx(50F).roundToInt()
        return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed(): Boolean {
        return !this.isKeyboardOpen()
}*/
