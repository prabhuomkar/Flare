package io.github.prabhuomkar.torchexpo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.prabhuomkar.torchexpo.DATABASE_NAME
import io.github.prabhuomkar.torchexpo.data.database.dao.ModelsDao
import io.github.prabhuomkar.torchexpo.data.database.dao.TasksDao
import io.github.prabhuomkar.torchexpo.data.models.Model
import io.github.prabhuomkar.torchexpo.data.models.Task

@Database(entities = [Model::class, Task::class], version = 1, exportSchema = false)
abstract class TorchDatabase : RoomDatabase() {

    abstract fun modelsDao(): ModelsDao
    abstract fun tasksDao(): TasksDao

    companion object Builder {
        private var INSTANCE: TorchDatabase? = null

        fun getInstance(context: Context): TorchDatabase {
            if (INSTANCE == null) {
                synchronized(TorchDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TorchDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}
