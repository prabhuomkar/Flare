package io.github.prabhuomkar.torchexpo.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.prabhuomkar.torchexpo.data.database.dao.ModelsDao
import io.github.prabhuomkar.torchexpo.data.database.dao.TasksDao

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
                "torch_expo"
            ).build()
    }
}
