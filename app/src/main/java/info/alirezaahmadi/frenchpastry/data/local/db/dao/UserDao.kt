package info.alirezaahmadi.frenchpastry.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import info.alirezaahmadi.frenchpastry.data.local.db.entitiesModel.UserEntity

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: UserEntity)

}