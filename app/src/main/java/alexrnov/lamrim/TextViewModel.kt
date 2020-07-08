package alexrnov.lamrim

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.*
import kotlinx.coroutines.*

// A ViewModel object provides the data for a specific UI component, such as a fragment
// or activity, and contains data-handling business logic to communicate with the model.
// The ViewModel doesn't know about UI components, so it isn't affected by configuration
// changes, such as recreating an activity when rotating the device. The class that
// prepares the data for viewing in the UserProfileFragment and reacts to user interactions.
// state: SaveStateHandle allow store state of view
class TextViewModel(state: SavedStateHandle) : ViewModel() {

  private val savedState = state

  @NonNull
  private val repo = FileRepository.getInstance()

  private lateinit var myLiveData: LiveData<String>

  private var newLiveData: LiveData<String>

  init {
    Log.i("P", "init view model")
    myLiveData = repo.myLiveData

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

  fun f() {
    viewModelScope.launch {
      sf()
    }
  }

  private suspend fun sf() = withContext(Dispatchers.Default) {
    delay(10000)
    repo.performJob()
    Log.i("P", "sf() complete")
  }

  fun getMyLiveData(): LiveData<String> {
    return myLiveData
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