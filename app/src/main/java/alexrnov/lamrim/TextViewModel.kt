package alexrnov.lamrim

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

// A ViewModel object provides the data for a specific UI component, such as a fragment
// or activity, and contains data-handling business logic to communicate with the model.
// The ViewModel doesn't know about UI components, so it isn't affected by configuration
// changes, such as recreating an activity when rotating the device. The class that
// prepares the data for viewing in the UserProfileFragment and reacts to user interactions.
class TextViewModel(private val state: SavedStateHandle) : ViewModel() {

  private val savedState = state

  val idItem: String = "id_item"

  // Create a LiveData with a String
  val textItem: MutableLiveData<String> by lazy {
    MutableLiveData<String>().also {
      loadText()
    }
  }

  fun setCurrentItem(currentItem: String) {
    savedState.set(SELECT_ITEM, currentItem)
  }

  fun getCurrentItem(): String {
    // when app start for a first time, return first item ("0")
    return savedState[SELECT_ITEM] ?: "0"
  }

  private fun loadText() {
    // Do an asynchronous operation
  }

  companion object {
    private const val SELECT_ITEM = "selectItem"
  }
}