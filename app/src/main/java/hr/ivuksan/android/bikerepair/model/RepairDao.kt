package hr.ivuksan.android.bikerepair.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RepairDao {

    @Query("SELECT * FROM Repair ORDER BY repairId DESC")
    fun loadRepairs(): MutableList<Repair>

    @Query("SELECT * FROM Repair WHERE repairId = :repairId")
    fun getRepairById(repairId: Int?): Repair

    @Insert
    fun insertOne(repair: Repair)
}