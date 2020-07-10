package alexrnov.lamrim.architecture

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream

class DetailModel(state: SavedStateHandle) : ViewModel() {

  @NonNull
  private val repository = Repository.getInstance()

  private var detailsText: LiveData<String>

  init {
    detailsText = repository.detailsText
  }

  fun getDetailsText(): LiveData<String> {
    return detailsText
  }

  fun loadText(input: InputStream) {
    viewModelScope.launch {
      withContext(Dispatchers.Default) {
        delay(5000)
        repository.loadDetailsTextPromFile(input)
      }
    }
  }

}