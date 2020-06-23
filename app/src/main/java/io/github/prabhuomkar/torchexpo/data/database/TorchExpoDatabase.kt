package io.github.prabhuomkar.torchexpo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.prabhuomkar.torchexpo.DATABASE_NAME
import io.github.prabhuomkar.torchexpo.data.database.dao.ModelDao
import io.github.prabhuomkar.torchexpo.data.database.dao.TaskDao
import io.github.prabhuomkar.torchexpo.data.models.Model
import io.github.prabhuomkar.torchexpo.data.models.Task

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
                DATABASE_NAME
            ).build()
    }
}