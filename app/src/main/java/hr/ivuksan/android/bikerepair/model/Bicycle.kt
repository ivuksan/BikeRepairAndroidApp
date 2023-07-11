package hr.ivuksan.android.bikerepair.model

import android.os.Parcelable
import android.text.TextUtils
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Bicycle(
    @PrimaryKey(autoGenerate = true) val bicycleId: Int?,
    @ColumnInfo val owner: String,
    @ColumnInfo val brand: String,
    @ColumnInfo val name: String,
    @ColumnInfo val category: String
): Parcelable {
    fun isValid(): Int {
        return if (TextUtils.isEmpty(owner)) 0
        else if (TextUtils.isEmpty(brand)) 1
        else if (TextUtils.isEmpty(name)) 2
        else if (TextUtils.isEmpty(category)) 3
        else -1
    }
}