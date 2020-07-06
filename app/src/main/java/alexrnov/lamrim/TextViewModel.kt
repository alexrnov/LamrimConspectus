package alexrnov.lamrim

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

// A ViewModel object provides the data for a specific UI component, such as a fragment
// or activity, and contains data-handling business logic to communicate with the model.
// The ViewModel doesn't know about UI components, so it isn't affected by configuration
// changes, such as recreating an activity when rotating the device. The class that
// prepares the data for viewing in the UserProfileFragment and reacts to user interactions.
// state: SaveStateHandle allow store state of view
class TextViewModel(private val state: SavedStateHandle) : ViewModel() {

  private val savedState = state

  // Create a LiveData with a String

  val text: MutableLiveData<String> by lazy {
    MutableLiveData<String>().also {
      loadText()
    }
  }

  private fun loadText() = viewModelScope.launch {
    val v1 = async(CoroutineName("v1coroutine")) {
      "text"
    }
    text.postValue(v1.await())
    // Coroutine that will be canceled when the ViewModel is cleared automatically to avoid consuming resources.
  }


  /*
  val text1 = MutableLiveData<String>()
          .apply {
    setValue("55555555")
    //loadText1()
  }

   */

  val text2: LiveData<String> = liveData {
    val data = loadText2()
    emit(data)
  }

  private suspend fun loadText2(): String {
    return withContext(IO) {
      return@withContext "text"
    }
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