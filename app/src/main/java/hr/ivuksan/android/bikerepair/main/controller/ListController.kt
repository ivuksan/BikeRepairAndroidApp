package hr.ivuksan.android.bikerepair.main.controller

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import hr.ivuksan.android.bikerepair.model.BicycleDao
import hr.ivuksan.android.bikerepair.model.Database
import hr.ivuksan.android.bikerepair.main.view.IMainView
import hr.ivuksan.android.bikerepair.model.Bicycle

class ListController(var mainView: IMainView, var context: Context): IListController {

    lateinit var db : Database
    lateinit var bicycleDao: BicycleDao
    lateinit var bicycleList: MutableList<Bicycle>

    override fun onLoad() {
        db = Room.databaseBuilder(
            context,
            Database::class.java, "bikeRepairDb"
        )
            .allowMainThreadQueries()
            .build()
        bicycleDao = db.bicycleDao()

        val user = FirebaseAuth.getInstance().currentUser?.email?.substringBefore("@")

        if (user == "admin"){
            bicycleList = bicycleDao.loadBicycles()
            mainView.showBicycles(bicycleList)
        }else {
            bicycleList = bicycleDao.loadBicyclesByOwner(user?.lowercase())
            mainView.showBicycles(bicycleList)
        }

    }
}