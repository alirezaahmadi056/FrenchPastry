package info.alirezaahmadi.frenchpastry.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import info.alirezaahmadi.frenchpastry.data.local.db.dao.UserDao
import info.alirezaahmadi.frenchpastry.data.local.db.entitiesModel.UserEntity

@Database(
    entities = [UserEntity::class],
    version = MainDatabase.DATABASE_VERSION
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDao

    companion object {

        private const val DATABASE_NAME = "main_database"
        const val DATABASE_VERSION = 1
        const val USERS_TABLE = "user_table"

        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {

            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

            return INSTANCE!!

        }

    }

}