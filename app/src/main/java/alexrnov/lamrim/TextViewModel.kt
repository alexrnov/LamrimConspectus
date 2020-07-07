package alexrnov.lamrim

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

// A ViewModel object provides the data for a specific UI component, such as a fragment
// or activity, and contains data-handling business logic to communicate with the model.
// The ViewModel doesn't know about UI components, so it isn't affected by configuration
// changes, such as recreating an activity when rotating the device. The class that
// prepares the data for viewing in the UserProfileFragment and reacts to user interactions.
// state: SaveStateHandle allow store state of view
class TextViewModel(state: SavedStateHandle) : ViewModel() {

  private val savedState = state

  @NonNull
  private val repo = MyRepository.getInstance()

  private lateinit var myLiveData: LiveData<String>

  init {
    myLiveData = repo.myLiveData
  }

  fun getMyLiveData(): LiveData<String> {
    return myLiveData
  }
  /*
  // Create a LiveData with a String
  val text = MutableLiveData<String>().apply {
    // Coroutine that will be canceled when the ViewModel is cleared automatically to avoid consuming resources.
    viewModelScope.launch {
      val job = async(CoroutineName("load text")) {
        loadTextFromFile() // long operation - load big text from file
      }
      postValue(job.await())
    }
  }

  private fun loadTextFromFile(): String {
    return "loadingText"
  }
  */
  /*
  val text2: LiveData<String> = liveData {
    val data = loadText2()
    emit(data)
  }

  private suspend fun loadText2(): String {
    return withContext(IO) {
      return@withContext "text"
    }
  }
  */
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