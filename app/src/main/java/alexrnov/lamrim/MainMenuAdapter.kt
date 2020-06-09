package alexrnov.lamrim

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainMenuAdapter : RecyclerView.Adapter<MainMenuAdapter.TextViewHolder>() {

  private var dataset = arrayOf("item1", "item2", "item3", "item4",
          "item5", "item6", "item7", "item8", "item9", "item10", "item11", "item132")


  // Provide a reference to the views for each data item. Complex data items may need
  // more than one view per item, and you provide access to all the views for a data
  // item in a view holder. Each data item is just a string in this case that is shown in a TextView.
  class TextViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {
    init {
      textView.setOnClickListener {
        textView.setBackgroundResource(R.drawable.item_press)
        Log.i("P", "adapterPosition = $adapterPosition")
      }

      textView.setOnFocusChangeListener { view, b ->
        if (b) {
          Log.i("P", "YES")
          view.setBackgroundResource(R.drawable.item_press)
        } else {
          Log.i("P", "NO")
          view.setBackgroundResource(R.drawable.item_default)
        }
      }
    }
  }

  // Create new views (invoked by the layout manager)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
    // create a new view
    val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_view, parent, false) as TextView
    // set the view's size, margins, paddings and layout parameters
    // ...
    return TextViewHolder(textView)
  }

  // replace the contents of a view (invoked by the layout manager)
  override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
    // - get element from your dataset at this position
    // - replace the contents of the view with that element
    holder.textView.text = dataset[position]
  }

  // return the size of your dataset (invoked by the layout manager)
  override fun getItemCount() = dataset.size

}