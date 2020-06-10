package alexrnov.lamrim

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainMenuAdapter(private val dualPane: Boolean,
                                      private val parentActivity: MainActivity) :
        RecyclerView.Adapter<MainMenuAdapter.TextViewHolder>() {

  private var dataset = arrayOf("Предисловие", "item2", "item3", "item4",
          "item5", "item6", "item7", "item8", "item9", "item10", "item11", "item13")


  private val onePanelListener = { view: View ->
    view.setBackgroundResource(R.drawable.item_check)
    val context = view.context
    val intent = Intent(context, ContentActivity::class.java)
    context.startActivity(intent)
  }

  private val dualPaneListener = { view: View, hasFocus: Boolean ->
    if (hasFocus) {
      val fragment = ContentFragment()

      parentActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit() // call commit() for the changes to take effect.

      view.setBackgroundResource(R.drawable.item_check)
    } else {
      view.setBackgroundResource(R.drawable.item_default)
    }
  }

  // Provide a reference to the views for each data item. Complex data items may need
  // more than one view per item, and you provide access to all the views for a data
  // item in a view holder. Each data item is just a string in this case that is shown in a TextView.
  class TextViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

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

    holder.textView.isClickable = true
    if (dualPane) {
      holder.textView.requestFocus()
      holder.textView.isFocusable = true
      holder.textView.isFocusableInTouchMode = true
      holder.textView.setOnFocusChangeListener(dualPaneListener)
    } else {
      holder.textView.setOnClickListener(onePanelListener)
    }
  }

  // return the size of your dataset (invoked by the layout manager)
  override fun getItemCount() = dataset.size

}