package alexrnov.lamrim

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class Class5 {
  class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    init {
      item.setOnClickListener { Log.d("RecyclerView", "onClick：$adapterPosition") }

    }
  }
}