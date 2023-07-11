package hr.ivuksan.android.bikerepair.link

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hr.ivuksan.android.bikerepair.R
import hr.ivuksan.android.bikerepair.databinding.LinkActivityBinding
import hr.ivuksan.android.bikerepair.link.controller.LinkController
import hr.ivuksan.android.bikerepair.link.model.RepairsAdapter
import hr.ivuksan.android.bikerepair.link.view.ILinkView
import hr.ivuksan.android.bikerepair.model.Bicycle
import hr.ivuksan.android.bikerepair.model.Repair

class LinkActivity : AppCompatActivity(), ILinkView {

    lateinit var binding: LinkActivityBinding
    lateinit var linkController: LinkController
    lateinit var repairsAdapter: RepairsAdapter
    lateinit var bicycle: Bicycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LinkActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        bicycle = intent.extras!!.getParcelable("bicycle")!!

        linkController = LinkController(this, applicationContext, bicycle)
        binding.addRepairs.setOnClickListener{
            val checkedRepairs = repairsAdapter.getCheckedRepairs()
            for (repair in checkedRepairs) {
                linkController.addRepairToBicycle(repair)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        linkController.onLoad()
    }

    override fun showRepairs(repairs: MutableList<Repair>) {
        repairsAdapter = RepairsAdapter(repairs)
        binding.listView.adapter = repairsAdapter
    }

    override fun onLinkSuccess() {
        binding.listView.visibility = View.GONE
        binding.addRepairs.visibility = View.GONE
        val textView = TextView(this)
        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT,
        )
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        layoutParams.marginStart = 20
        layoutParams.marginEnd= 20
        textView.gravity = Gravity.CENTER
        textView.textSize = 25F
        textView.setTextColor(resources.getColor(R.color.gray700))
        textView.typeface = Typeface.create("nunito_sans_bold.ttf", Typeface.BOLD)
        textView.text = resources.getString(R.string.linkSuccess)
        textView.layoutParams = layoutParams
        binding.relativeLayout.addView(textView)
    }
}