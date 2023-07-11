package hr.ivuksan.android.bikerepair.link.controller

import android.content.Context
import androidx.room.Room
import hr.ivuksan.android.bikerepair.link.model.BicycleRepairs
import hr.ivuksan.android.bikerepair.link.model.BicycleRepairsDao
import hr.ivuksan.android.bikerepair.link.view.ILinkView
import hr.ivuksan.android.bikerepair.model.Bicycle
import hr.ivuksan.android.bikerepair.model.Database
import hr.ivuksan.android.bikerepair.model.Repair
import hr.ivuksan.android.bikerepair.model.RepairDao

class LinkController(
    var linkView: ILinkView,
    var context: Context,
    var bicycle: Bicycle
) : ILinkController {

    lateinit var db : Database
    lateinit var repairDao: RepairDao
    lateinit var bicycleRepairsDao: BicycleRepairsDao

    override fun onLoad() {
        db = Room.databaseBuilder(
            context,
            Database::class.java, "bikeRepairDb"
        )
            .allowMainThreadQueries()
            .build()
        repairDao = db.repairDao()

        val repairList = repairDao.loadRepairs()
        linkView.showRepairs(repairList)
    }

    override fun addRepairToBicycle(repair: Repair) {
        db = Room.databaseBuilder(
            context,
            Database::class.java, "bikeRepairDb"
        )
            .allowMainThreadQueries()
            .build()
        bicycleRepairsDao = db.bicycleRepairsDao()
        val bicycleRepairs = bicycle.bicycleId?.let { repair.repairId?.let { it1 ->
            BicycleRepairs(it,
                it1
            )
        } }
        if (bicycleRepairs != null) {
            bicycleRepairsDao.insertBicycleRepairs(bicycleRepairs)
            linkView.onLinkSuccess()
        }
    }
}