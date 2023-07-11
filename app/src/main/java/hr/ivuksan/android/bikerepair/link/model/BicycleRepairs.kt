package hr.ivuksan.android.bikerepair.link.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import hr.ivuksan.android.bikerepair.model.Bicycle
import hr.ivuksan.android.bikerepair.model.Repair
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "bicycle_repairs",
    primaryKeys = ["bicycleId", "repairId"],
    foreignKeys = [
        ForeignKey(entity = Bicycle::class, parentColumns = ["bicycleId"], childColumns = ["bicycleId"]),
        ForeignKey(entity = Repair::class, parentColumns = ["repairId"], childColumns = ["repairId"])
    ]
)
@Parcelize
data class BicycleRepairs (
    @ColumnInfo val bicycleId: Int,
    @ColumnInfo val repairId: Int
): Parcelable