package hr.ivuksan.android.bikerepair.detail.controller

import android.content.Context
import androidx.room.Room
import hr.ivuksan.android.bikerepair.detail.view.IDetailView
import hr.ivuksan.android.bikerepair.link.model.BicycleRepairsDao
import hr.ivuksan.android.bikerepair.model.Bicycle
import hr.ivuksan.android.bikerepair.model.Database
import hr.ivuksan.android.bikerepair.model.Repair
import hr.ivuksan.android.bikerepair.model.RepairDao

class DetailController(
    var detailView: IDetailView,
    var context: Context,
    var bicycle: Bicycle
): IDetailController {

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
        bicycleRepairsDao = db.bicycleRepairsDao()
        repairDao = db.repairDao()

        val linkList = bicycle.bicycleId?.let { bicycleRepairsDao.getRepairsForBicycle(it) }
        val repairList = mutableListOf<Repair>()
        linkList?.let{
            for (br in linkList){
                repairList.add(repairDao.getRepairById(br.repairId))
                println(br.repairId)
            }
        }

        detailView.showImage(bicycle.brand)
        detailView.showBicycleDetails(bicycle)
        detailView.showBicycleRepairs(repairList)
    }
}