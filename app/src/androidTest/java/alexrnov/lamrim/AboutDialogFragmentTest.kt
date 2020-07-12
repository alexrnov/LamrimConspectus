package alexrnov.lamrim

import alexrnov.lamrim.fragments.AboutDialogFragment
import androidx.fragment.app.testing.launchFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class AboutDialogFragmentTest {

  @Test
  fun showDialog() {

    val scenario = launchFragment<AboutDialogFragment>()
    scenario.onFragment { fragment ->
      assertThat(fragment.dialog).isNotNull()
      assertThat(fragment.requireDialog().isShowing).isTrue()
      fragment.dismiss()
      // is deprecated
      //fragment.requireFragmentManager().executePendingTransactions()
      fragment.parentFragmentManager.executePendingTransactions()
      assertThat(fragment.dialog).isNull()
    }

    /*
    with(launchFragment<AboutDialogFragment>()) {
      onFragment { fragment ->
        assertThat(fragment.dialog).isNotNull()
        assertThat(fragment.requireDialog().isShowing).isTrue()
        fragment.dismiss()
        // is deprecated
        //fragment.requireFragmentManager().executePendingTransactions()
        fragment.parentFragmentManager.executePendingTransactions()
        assertThat(fragment.dialog).isNull()
      }
    }
    */

  }
}