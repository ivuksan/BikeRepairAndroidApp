package hr.ivuksan.android.bikerepair.insert

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.ivuksan.android.bikerepair.model.Bicycle
import hr.ivuksan.android.bikerepair.databinding.InsertActivityBinding
import hr.ivuksan.android.bikerepair.insert.controller.AddController
import hr.ivuksan.android.bikerepair.insert.view.IAddView

class InsertActivity : AppCompatActivity(), IAddView {

    lateinit var binding: InsertActivityBinding
    lateinit var addController: AddController
    lateinit var bicycle: Bicycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InsertActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        addController = AddController(this, applicationContext)
        binding.addBicycle.setOnClickListener {
            addController.onNewBicycle(
                binding.owner.text.toString().lowercase(),
                binding.brand.text.toString().lowercase(),
                binding.name.text.toString(),
                binding.category.text.toString()
            )
            binding.owner.setText("")
            binding.brand.setText("")
            binding.name.setText("")
            binding.category.setText("")
        }
    }

    override fun onInsertSuccess(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onInsertError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}