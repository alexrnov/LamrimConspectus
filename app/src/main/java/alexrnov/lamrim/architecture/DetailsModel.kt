package alexrnov.lamrim.architecture

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream

class DetailsModel : ViewModel() {

  @NonNull
  private val repository = Repository.getInstance()

  val detailsFileName = "details_text"

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
        repository.loadDetailsTextPromFile(input)
      }
    }
  }

}