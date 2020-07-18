package alexrnov.lamrim

import alexrnov.lamrim.TestUtils.isFontSize
import android.content.Context
import android.content.Intent
import android.view.Surface
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val LAUNCH_TIMEOUT = 1000L
private const val BASIC_SAMPLE_PACKAGE = "alexrnov.lamrim"

@RunWith(AndroidJUnit4::class)
// the annotation verifies that the API is at least version 18, as required by the Automator framework
@SdkSuppress(minSdkVersion = 18)
class SettingsTest {
  private lateinit var device: UiDevice

  @Before
  fun startMainActivityFromHomeScreen() {
    // Initialize UiDevice instance
    device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    device.pressHome() // Start from the home screen

    // wait run
    val launcherPackage: String = device.launcherPackageName
    ViewMatchers.assertThat(launcherPackage, CoreMatchers.notNullValue())
    device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT)

    // Launch the app
    val context = ApplicationProvider.getApplicationContext<Context>()
    val intent = context.packageManager
            .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE)?.apply {
              // Clear out any previous instances
              addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }

    context.startActivity(intent)
    device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT ) // Wait

  }

  @Test
  fun changeSizeFontLandscape() {
    InstrumentationRegistry.getInstrumentation().uiAutomation
            .setRotation(Surface.ROTATION_90) // landscape orientation

    // Get the button object. If you want to access a specific UI component in your
    // application, use the UiSelector class. This class represents a request for
    // specific items in the currently displayed user interface.
    val settingsButton: UiObject = device.findObject(UiSelector()
            .resourceId("$BASIC_SAMPLE_PACKAGE:id/action_settings"))
    settingsButton.click()

    val launcherPackage: String = device.launcherPackageName
    device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT)

    onView(withText(R.string.font_size)).perform(click())

    device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT)

    onView(withText(R.string.very_big_size)).perform(click())

    device.pressBack() // back to main activity
    device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT)

    onView(withId(R.id.preview_text)).check(matches(isFontSize(13.0f)))

  }

  fun changeSizeFontPortrait() {
    InstrumentationRegistry.getInstrumentation().uiAutomation
            .setRotation(Surface.ROTATION_0) // portrait orientation
  }


}