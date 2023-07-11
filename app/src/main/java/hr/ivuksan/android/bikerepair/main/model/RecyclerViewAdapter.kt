package hr.ivuksan.android.bikerepair.main.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import hr.ivuksan.android.bikerepair.detail.DetailActivity
import hr.ivuksan.android.bikerepair.R
import hr.ivuksan.android.bikerepair.link.LinkActivity
import hr.ivuksan.android.bikerepair.model.Bicycle

class RecyclerViewAdapter constructor(): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var mBicycleList: MutableList<Bicycle>
    lateinit var context: Context

    constructor(mBicycleList: MutableList<Bicycle>, context: Context): this() {
        this.mBicycleList = mBicycleList
        this.context = context
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        init {
            textView = itemView as TextView
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_list_item, parent, false)
        return ViewHolder(v as TextView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = FirebaseAuth.getInstance().currentUser?.email?.substringBefore("@")
        holder.textView.text = mBicycleList[position].name
        holder.textView.setOnClickListener(){
            val adminIntent = Intent(context, LinkActivity::class.java)
            val userIntent = Intent(context, DetailActivity::class.java)
            if (user == "admin"){
                adminIntent.putExtra("bicycle", mBicycleList[position])
                context.startActivity(adminIntent)
            }else {
                userIntent.putExtra("bicycle", mBicycleList[position])
                context.startActivity(userIntent)
            }

        }
    }

    override fun getItemCount(): Int {
        return mBicycleList.size
    }
}