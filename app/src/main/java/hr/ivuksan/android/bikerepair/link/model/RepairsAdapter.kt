package hr.ivuksan.android.bikerepair.link.model

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import hr.ivuksan.android.bikerepair.R
import hr.ivuksan.android.bikerepair.model.Repair

class RepairsAdapter(private val repairList: List<Repair>): BaseAdapter() {

    private val checkedRepairs = SparseBooleanArray()

    override fun getCount(): Int {
        return repairList.size
    }

    override fun getItem(position: Int): Any {
        return repairList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.list_layout, parent, false)

        val repair = repairList[position]

        val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
        checkBox.text = repair.name
        checkBox.isChecked = checkedRepairs.get(position, false)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            checkedRepairs.put(position, isChecked)
        }

        return view
    }

    fun getCheckedRepairs(): List<Repair> {
        val checkedRepairList = mutableListOf<Repair>()
        for (i in 0 until checkedRepairs.size()) {
            val position = checkedRepairs.keyAt(i)
            val isChecked = checkedRepairs.valueAt(i)
            if (isChecked) {
                checkedRepairList.add(repairList[position])
            }
        }
        return checkedRepairList
    }
}