package alexrnov.lamrim

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView

class MainMenuAdapter(private val dualPane: Boolean, private val parentActivity: MainActivity) :
        RecyclerView.Adapter<MainMenuAdapter.TextViewHolder>() {

  private var dataset = arrayOf("Предисловие", "item2", "item3", "item4",
          "item5", "item6", "item7", "item8", "item9", "item10", "item11", "item13")

  private val selectedItem: ArrayList<Int> = ArrayList()

  init {
    if (dualPane) { // by default, in dual pane mode, select the first item
      selectedItem.add(0)
      // to manage the fragments in activity, need to use FragmentManager.
      parentActivity.supportFragmentManager.beginTransaction()
              .replace(R.id.fragment_container, ContentFragment())
              .commit() // call commit() for the changes to take effect.
    }
  }

  private val onePanelListener = { view: View ->
    view.setBackgroundResource(R.drawable.item_check)
    val context = view.context
    val intent = Intent(context, ContentActivity::class.java)
    context.startActivity(intent)
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

    if (!selectedItem.contains(position)) { // view not selected
      holder.itemView.setBackgroundResource(R.drawable.item_default)
    } else { // view is selected
      holder.itemView.setBackgroundResource(R.drawable.item_check)
    }
    // - get element from your dataset at this position - replace the contents of the view with that element
    holder.textView.text = dataset[position]

    holder.textView.isClickable = true

    if (dualPane) { // dual pane mode

      holder.textView.setOnClickListener { view ->
        // solution Dustin Charles how-to-properly-highlight-selected-item-on-recyclerview
        view.setBackgroundResource(R.drawable.item_check)

        if (selectedItem.isEmpty()) {
          selectedItem.add(position)
        } else {
          val oldSelected = selectedItem[0]
          selectedItem.clear()
          selectedItem.add(position)
          // we do not notify that an item has been selected because that work is
          // done here. We instead send notifications for items to be deselected
          notifyItemChanged(oldSelected)
        }

        // to manage the fragments in activity, need to use FragmentManager.
        val manager: FragmentManager = parentActivity.supportFragmentManager
        // to make fragment transactions in activity (such as add, remove, or replace a fragment)
        val transaction: FragmentTransaction = manager.beginTransaction()
        // replace an existing fragment that was added to a container
        transaction.replace(R.id.fragment_container, ContentFragment())
        transaction.commit() // call commit() for the changes to take effect.
      }
    } else { // one pane mode
      holder.textView.setOnClickListener(onePanelListener)
    }
  }

  // return the size of your dataset (invoked by the layout manager)
  override fun getItemCount() = dataset.size
}