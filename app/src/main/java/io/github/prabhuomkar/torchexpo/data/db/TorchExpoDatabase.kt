package io.github.prabhuomkar.torchexpo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.prabhuomkar.torchexpo.Constants
import io.github.prabhuomkar.torchexpo.data.db.dao.ModelDao
import io.github.prabhuomkar.torchexpo.data.db.dao.TaskDao
import io.github.prabhuomkar.torchexpo.data.db.model.Model
import io.github.prabhuomkar.torchexpo.data.db.model.Task

@Database(entities = [Model::class, Task::class], version = 1, exportSchema = false)
abstract class TorchExpoDatabase : RoomDatabase() {

    abstract fun modelDao(): ModelDao
    abstract fun taskDao(): TaskDao

    companion object Builder {
        private var INSTANCE: TorchExpoDatabase? = null

        fun getInstance(context: Context): TorchExpoDatabase {
            if (INSTANCE == null) {
                synchronized(TorchExpoDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TorchExpoDatabase::class.java,
                Constants.DATABASE_NAME
            ).build()
    }
}