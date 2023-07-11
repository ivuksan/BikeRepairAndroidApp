package hr.ivuksan.android.bikerepair.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import hr.ivuksan.android.bikerepair.R
import hr.ivuksan.android.bikerepair.databinding.DetailActivityBinding
import hr.ivuksan.android.bikerepair.detail.controller.DetailController
import hr.ivuksan.android.bikerepair.detail.view.IDetailView
import hr.ivuksan.android.bikerepair.model.Bicycle
import hr.ivuksan.android.bikerepair.model.Repair

class DetailActivity : AppCompatActivity(), IDetailView {

    lateinit var binding: DetailActivityBinding
    lateinit var bicycle: Bicycle
    lateinit var detailController: DetailController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        bicycle = intent.extras!!.getParcelable("bicycle")!!

        detailController = DetailController(this, applicationContext, bicycle)
    }

    override fun onStart() {
        super.onStart()
        detailController.onLoad()
    }

    @SuppressLint("DiscouragedApi")
    override fun showImage(imageName: String) {
        Picasso.get()
            .load(resources.getIdentifier(imageName, "drawable", packageName))
            .error(R.drawable.image_not_found)
            .into(binding.imgBicycle)
    }

    override fun showBicycleDetails(bicycle: Bicycle) {
        binding.owner.text = bicycle.owner
        binding.brand.text = bicycle.brand
        binding.name.text = bicycle.name
        binding.category.text = bicycle.category
    }

    override fun showBicycleRepairs(repairs: MutableList<Repair>) {
        if (repairs.isEmpty()){
            binding.repairList.visibility = View.GONE
            val textView = TextView(this)
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            textView.text = resources.getString(R.string.repairsDetails)
            textView.textSize = 16F
            layoutParams.gravity = Gravity.CENTER
            layoutParams.topMargin = 80
            textView.layoutParams = layoutParams
            binding.listLayout.addView(textView)
        }else {
            val adapter = ArrayAdapter(this, R.layout.detail_list_item, repairs)
            binding.repairList.adapter = adapter
        }
    }
}