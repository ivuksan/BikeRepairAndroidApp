package hr.ivuksan.android.bikerepair.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Repair(
    @PrimaryKey(autoGenerate = true) val repairId: Int?,
    @ColumnInfo val name: String,
    @ColumnInfo val price: Int,
    @ColumnInfo val duration: Int,
){
    override fun toString(): String {
        return name
    }
}