package hr.ivuksan.android.bikerepair.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BicycleDao {

    @Query("SELECT * FROM Bicycle WHERE owner LIKE :user")
    fun loadBicyclesByOwner(user: String?): MutableList<Bicycle>

    @Query("SELECT * FROM Bicycle ORDER BY bicycleId DESC")
    fun loadBicycles(): MutableList<Bicycle>

    @Insert
    fun insertOne(bicycle: Bicycle)

    @Delete
    fun delete(bicycle: Bicycle)
}