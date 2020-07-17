package alexrnov.lamrim.activities

import alexrnov.lamrim.R
import alexrnov.lamrim.fragments.PreviewFragment
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.OnLongClickListener
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for main menu
 * [currentSelectId] - id item witch is current select. Pass to the
 * constructor to remember select state retain after rotation screen.
 * [dualPaneMode] - mode when view menu and short description.
 * [parentActivity] - activity include fragment.
 */
class MainMenuAdapter(var currentSelectId: String,
          private val dualPaneMode: Boolean,
          private val parentActivity: MainActivity) :
        RecyclerView.Adapter<MainMenuAdapter.TextViewHolder>() {

  private var dataset: Array<String> = parentActivity.resources.getStringArray(R.array.item_names)
  private val selectedItem: ArrayList<Int> = ArrayList()

  init {

    if (dualPaneMode) {
      // select the item, witch saved in onSaveInstanceState() method
      selectedItem.add(Integer.valueOf(currentSelectId))

      val arguments = Bundle()
      arguments.putString("id", currentSelectId)
      val fragment = PreviewFragment()
      fragment.arguments = arguments

      // to manage the fragments in activity, need to use FragmentManager.
      parentActivity.supportFragmentManager.beginTransaction()
              .replace(R.id.fragment_preview, fragment)
              .commit() // call commit() for the changes to take effect.
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
            .inflate(R.layout.item_menu_text_view, parent, false) as TextView
    // here may set the view's size, margins, paddings and layout parameters
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

    holder.textView.tag = position
    holder.textView.isClickable = true

    if (dualPaneMode) { // dual pane mode

      holder.textView.setOnClickListener { view ->
        // solution Dustin Charles how-to-properly-highlight-selected-item-on-recyclerview
        view.setBackgroundResource(R.drawable.item_check)

        if (selectedItem.isEmpty()) {
          selectedItem.add(position)
        } else {
          //val oldSelected = selectedItem[0]
          selectedItem.clear()
          selectedItem.add(position)
          // we do not notify that an item has been selected because that work is
          // done here. We instead send notifications for items to be deselected
          //notifyItemChanged(oldSelected) // work slowly
          notifyDataSetChanged()
        }

        val arguments = Bundle()
        currentSelectId = view.tag.toString()
        arguments.putString("id", currentSelectId)
        val fragment = PreviewFragment()
        fragment.arguments = arguments

        // to manage the fragments in activity, need to use FragmentManager.
        val manager: FragmentManager = parentActivity.supportFragmentManager
        // to make fragment transactions in activity (such as add, remove, or replace a fragment)
        val transaction: FragmentTransaction = manager.beginTransaction()
        // replace an existing fragment that was added to a container
        transaction.replace(R.id.fragment_preview, fragment)
        transaction.commit() // call commit() for the changes to take effect.
      }

    } else { // one pane mode
      holder.textView.setOnClickListener { view ->
        view.setBackgroundResource(R.drawable.item_check)

        if (selectedItem.isEmpty()) {
          selectedItem.add(position)
        } else {
          //val oldSelected = selectedItem[0]
          selectedItem.clear()
          selectedItem.add(position)
          // we do not notify that an item has been selected because that work is
          // done here. We instead send notifications for items to be deselected
          //notifyItemChanged(oldSelected) // work slowly
          notifyDataSetChanged()
        }

        val context = view.context
        val intent = Intent(context, DetailsActivity::class.java)
        currentSelectId = view.tag.toString()

        intent.putExtra("id", currentSelectId)
        context.startActivity(intent)
      }
    }
  }

  // return the size of your dataset (invoked by the layout manager)
  override fun getItemCount() = dataset.size

}