package alexrnov.lamrim.fragments

import alexrnov.lamrim.R
import alexrnov.lamrim.architecture.DetailModel
import alexrnov.lamrim.architecture.TextStyleObserver
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import java.util.*

class DetailsFragment: Fragment() {

  private var currentItemID = ""
  private var textView: TextView? = null
  private val model: DetailModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    currentItemID = arguments?.getString("id")?:"1"

    // Create the observer which updates the UI.
    val textObserver: Observer<String> = Observer { text ->
      textView?.text = text ?: ""
    }

    val s = model.detailsFileName + currentItemID
    val pack = activity!!.packageName
    val resID = resources.getIdentifier(s, "raw", pack)

    model.getDetailsText().observe(this, textObserver)
    val input = resources.openRawResource(resID)
    model.loadText(input)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, saveInstanseState: Bundle?): View? {
    // A boolean indicating whether the inflated layout should be attached to the ViewGroup (the second parameter) during inflation.
    val rootView: View = inflater.inflate(R.layout.details_layout, container, false)
    textView = rootView.findViewById<View>(R.id.details_text) as TextView

    // add observer for TextView change style (color and size)
    val activity = this.activity
    if (activity != null) {
      val context = activity.applicationContext
      val observer = TextStyleObserver(context, lifecycle)
      observer.addView(textView!!)
      lifecycle.addObserver(observer)
    }
    return rootView
  }
}