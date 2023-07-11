package hr.ivuksan.android.bikerepair.model

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import hr.ivuksan.android.bikerepair.link.model.BicycleRepairs
import hr.ivuksan.android.bikerepair.link.model.BicycleRepairsDao

@Database(entities = [Bicycle::class, Repair::class, BicycleRepairs::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun bicycleDao(): BicycleDao
    abstract fun repairDao(): RepairDao
    abstract fun bicycleRepairsDao(): BicycleRepairsDao
    companion object {
        @Volatile
        private var instance: hr.ivuksan.android.bikerepair.model.Database? = null

        fun getInstance(context: Context): hr.ivuksan.android.bikerepair.model.Database {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    hr.ivuksan.android.bikerepair.model.Database::class.java, "bikeRepairDb"
                ).build().also { instance = it }
            }
        }
    }
}