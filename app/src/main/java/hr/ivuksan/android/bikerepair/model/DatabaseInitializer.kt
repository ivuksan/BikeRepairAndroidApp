package hr.ivuksan.android.bikerepair.model

import android.content.Context
import hr.ivuksan.android.bikerepair.R

object DatabaseInitializer {

    fun initializeIfEmpty(database: Database, context: Context) {
        Thread {
            val repairDao = database.repairDao()
            val repairList = repairDao.loadRepairs()
            if (repairList.isEmpty()) {
                val repairs = context.resources.getStringArray(R.array.repairs)
                repairDao.insertOne(Repair(null, repairs[0], 10, 2))
                repairDao.insertOne(Repair(null, repairs[1], 13, 3))
                repairDao.insertOne(Repair(null, repairs[2], 25, 5))
                repairDao.insertOne(Repair(null, repairs[3], 8, 1))
                repairDao.insertOne(Repair(null, repairs[4], 47, 2))
                repairDao.insertOne(Repair(null, repairs[5], 23, 6))
                repairDao.insertOne(Repair(null, repairs[6], 10, 7))
                repairDao.insertOne(Repair(null, repairs[7], 22, 3))
                repairDao.insertOne(Repair(null, repairs[8], 60, 2))
                repairDao.insertOne(Repair(null, repairs[9], 13, 5))
                repairDao.insertOne(Repair(null, repairs[10], 5, 5))
                repairDao.insertOne(Repair(null, repairs[11], 7, 1))
                repairDao.insertOne(Repair(null, repairs[12], 5, 2))
                repairDao.insertOne(Repair(null, repairs[13], 20, 7))
                repairDao.insertOne(Repair(null, repairs[14], 21, 11))
            }
        }.start()
    }
}