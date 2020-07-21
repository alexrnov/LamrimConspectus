package alexrnov.lamrim

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar

/** Получить размеры экрана с навигационной панелью */
/* solution offer by EC84B4: https://stackoverflow.com/questions/26674378/android-get-screen-size-including-the-size-of-status-bar-and-software-navigation */
/*
fun getScreenSizeWithNavBar(activity: AppCompatActivity) {
  val x: Int
  val y: Int
  val context = activity.applicationContext
  val orientation = context.resources.configuration.orientation
  val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
  val display = wm.defaultDisplay
  val screenSize = Point()
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
    display.getRealSize(screenSize)
    x = screenSize.x
    y = screenSize.y
  } else {
    display.getSize(screenSize)
    x = screenSize.x
    y = screenSize.y
  }
  val width = getWidth(x, y, orientation)
  val height = getHeight(x, y, orientation)


  val displayMetrics = activity.resources.displayMetrics
  // логическая плотность дисплея. Это мастштабирующий фактор,
  // независящий от плотности пикселей (added in API Level 1)
  val density: Float = displayMetrics.density
  val dpWidth: Float = width / density
  val dpHeight: Float = height / density
  println("density = $density, width = $width, height = $height, dpWidth = $dpWidth, dpHeight = $dpHeight")
}

private fun getWidth(x: Int, y: Int, orientation: Int): Int {
  return if (orientation == Configuration.ORIENTATION_PORTRAIT) x else y
}

private fun getHeight(x: Int, y: Int, orientation: Int): Int {
  return if (orientation == Configuration.ORIENTATION_PORTRAIT) y else x
}
 */

/**
 * View snackbar
 * [view] - root view;
 * [message] - message text.
 */
fun showSnackbar(view: View, message: CharSequence) {
  val snackbar = Snackbar.make(view, message, 2000)
  snackbar.setAction("OK") { snackbar.dismiss() } // when you click on the button, the snackbar just hides
  snackbar.setActionTextColor(Color.parseColor("#ffffff")) // button color
  val snackbarView = snackbar.view
  snackbarView.setBackgroundColor(Color.parseColor("#656565")) // background color
  snackbarView.setPadding(0, 0, 0, 0)
  // set the width of the snackbar to the screen - this is necessary, since on the tablet the snackbar by default occupies only part of the screen
  val params = snackbarView.layoutParams as CoordinatorLayout.LayoutParams
  //val params = snackbarView.layoutParams as FrameLayout.LayoutParams
  // another option is to expand the snackbar
  //snackbarView.getLayoutParams().width = AppBarLayout.LayoutParams.MATCH_PARENT;
  params.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
  params.width = FrameLayout.LayoutParams.MATCH_PARENT
  snackbarView.layoutParams = params

  // invoke for android support library
  //val textView = snackbarView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
  // invoke for library androidx
  val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
  textView.setTextColor(Color.parseColor("#ffffff")) // message color
  snackbar.show()
}







