package alexrnov.lamrim

import android.util.Log
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

  private val savedState = state

  @NonNull
  private val repo = FileRepository.getInstance()

  private var newLiveData: LiveData<String>

  init {
    newLiveData = repo.newLiveData
  }

  // Create a LiveData with a String
  val text = MutableLiveData<String>().apply {
    // Coroutine that will be canceled when the ViewModel is cleared automatically to avoid consuming resources.
    viewModelScope.launch {
      val job = async(CoroutineName("load text")) {
        //Log.i("P", "job start")
        delay(10000)
        //Log.i("P", "job success")
        "text"// long operation - load big text from file
      }
      postValue(job.await())
    }
  }

  fun f(input: InputStream) {
    // Coroutine that will be canceled when the ViewModel is cleared automatically to avoid consuming resources.
    viewModelScope.launch {
      sf(input)
    }
  }

  private suspend fun sf(input: InputStream) = withContext(Dispatchers.Default) {
    delay(10000)
    repo.performJob(input)
    Log.i("P", "suspend fun complete")
  }

  fun getNewLiveData(): LiveData<String> {
    return newLiveData
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