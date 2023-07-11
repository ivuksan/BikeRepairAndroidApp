package hr.ivuksan.android.bikerepair.insert.controller

import android.content.Context
import androidx.room.Room
import hr.ivuksan.android.bikerepair.model.Bicycle
import hr.ivuksan.android.bikerepair.model.BicycleDao
import hr.ivuksan.android.bikerepair.model.Database
import hr.ivuksan.android.bikerepair.insert.view.IAddView

class AddController(var insertView: IAddView, var context: Context): IAddController {

    lateinit var db : Database
    lateinit var bicycleDao: BicycleDao

    override fun onNewBicycle(owner: String, brand: String, name: String, category: String) {
        db = Room.databaseBuilder(
            context,
            Database::class.java, "bikeRepairDb"
        )
            .allowMainThreadQueries()
            .build()
        bicycleDao = db.bicycleDao()

        val bicycle = Bicycle(null, owner, brand, name, category)
        val bicycleCode: Int = bicycle.isValid()
        if (bicycleCode == 0) {
            insertView.onInsertError("Please enter owner name")
        } else if (bicycleCode == 1) {
            insertView.onInsertError("Please enter brand name")
        } else if (bicycleCode == 2) {
            insertView.onInsertError("Please enter bicycle name")
        } else if (bicycleCode == 3) {
            insertView.onInsertError("Please enter the category")
        } else {
            bicycleDao.insertOne(bicycle)
            insertView.onInsertSuccess("Added successfully")
        }
    }
}