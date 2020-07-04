package alexrnov.lamrim

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

// A ViewModel object provides the data for a specific UI component, such as a fragment
// or activity, and contains data-handling business logic to communicate with the model.
// The ViewModel doesn't know about UI components, so it isn't affected by configuration
// changes, such as recreating an activity when rotating the device. The class that
// prepares the data for viewing in the UserProfileFragment and reacts to user interactions.
class TextViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

  val idItem: String = "id_item"

  // Create a LiveData with a String
  val textItem: MutableLiveData<String> by lazy {
    MutableLiveData<String>()
  }
}