package info.alirezaahmadi.frenchpastry.data.local.db.entitiesModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import info.alirezaahmadi.frenchpastry.data.local.db.MainDatabase

@Entity(tableName = MainDatabase.USERS_TABLE)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val fullName: String = "",
    @ColumnInfo val phone: String = "",
    @ColumnInfo val date: String = "",
    @ColumnInfo val email: String = "",
    @ColumnInfo val nationalCode: String = "",
    @ColumnInfo val sex: Int = 0
)