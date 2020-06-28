package alexrnov.lamrim

import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.os.Build
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

/** Получить размеры экрана с навигационной панелью */
/* solution offer by EC84B4: https://stackoverflow.com/questions/26674378/android-get-screen-size-including-the-size-of-status-bar-and-software-navigation */
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
  Log.i("i", "density = $density, width = $width, height = $height, " + "dpWidth = $dpWidth, dpHeight = $dpHeight")

}

private fun getWidth(x: Int, y: Int, orientation: Int): Int {
  return if (orientation == Configuration.ORIENTATION_PORTRAIT) x else y
}

private fun getHeight(x: Int, y: Int, orientation: Int): Int {
  return if (orientation == Configuration.ORIENTATION_PORTRAIT) y else x
}







