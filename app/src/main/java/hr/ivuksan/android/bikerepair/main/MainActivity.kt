package hr.ivuksan.android.bikerepair.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import hr.ivuksan.android.bikerepair.databinding.MainActivityBinding
import hr.ivuksan.android.bikerepair.insert.InsertActivity
import hr.ivuksan.android.bikerepair.main.controller.ListController
import hr.ivuksan.android.bikerepair.main.model.RecyclerViewAdapter
import hr.ivuksan.android.bikerepair.main.view.IMainView
import hr.ivuksan.android.bikerepair.model.*

class MainActivity : AppCompatActivity(), IMainView {

    lateinit var binding: MainActivityBinding
    lateinit var listController: ListController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val db = Database.getInstance(this)
        DatabaseInitializer.initializeIfEmpty(db, applicationContext)

        val user = FirebaseAuth.getInstance().currentUser?.email?.substringBefore("@")
        if (user != "admin"){
            binding.actionBar.newBicycle.visibility = View.INVISIBLE
        }

        listController = ListController(this, applicationContext)
    }

    override fun showBicycles(bicycles: MutableList<Bicycle>) {
        val adapter: RecyclerView.Adapter<*>
        val layoutManager: RecyclerView.LayoutManager

        binding.bicycleList.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        binding.bicycleList.layoutManager = layoutManager

        adapter = RecyclerViewAdapter(bicycles, this)
        binding.bicycleList.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        listController.onLoad()
    }

    fun newBicycle(view: View){
        val intent = Intent(applicationContext, InsertActivity::class.java)
        startActivity(intent)
    }
}