package hr.ivuksan.android.bikerepair.link.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BicycleRepairsDao {
    @Insert
    fun insertBicycleRepairs(bicycleRepairs: BicycleRepairs)

    @Query("SELECT * FROM bicycle_repairs WHERE bicycleId = :bicycleId")
    fun getRepairsForBicycle(bicycleId: Int): List<BicycleRepairs>
}