package alexrnov.lamrim.architecture

import androidx.annotation.NonNull
import androidx.lifecycle.*
import kotlinx.coroutines.*
import java.io.InputStream

// A ViewModel object provides the data for a specific UI component, such as a fragment
// or activity, and contains data-handling business logic to communicate with the model.
// The ViewModel doesn't know about UI components, so it isn't affected by configuration
// changes, such as recreating an activity when rotating the device. The class that
// prepares the data for viewing in the UserProfileFragment and reacts to user interactions.
// state: SaveStateHandle allow store state of view
class PreviewModel(state: SavedStateHandle) : ViewModel() {

  val previewFileName = "preview_text"
  private val savedState = state

  @NonNull
  private val repository = Repository.getInstance()

  private var previewText: LiveData<String>

  init {
    previewText = repository.previewText
  }

  fun loadText(input: InputStream) {
    // Coroutine that will be canceled when the ViewModel is cleared automatically to avoid consuming resources.
    viewModelScope.launch {
      withContext(Dispatchers.Default) {
        repository.loadPreviewTextPromFile(input)
      }
    }
  }

  fun getPreviewText(): LiveData<String> {
    return previewText
  }

  fun setCurrentItem(currentItem: String) {
    savedState.set(SELECT_ITEM, currentItem)
  }

  fun getCurrentItem(): String {
    // when app start for a first time, return first item ("0")
    return savedState[SELECT_ITEM] ?: "0"
  }

  companion object {
    private const val SELECT_ITEM = "selectItem"
  }
}