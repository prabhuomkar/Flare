package io.github.prabhuomkar.torchexpo.data.db.seeds

import androidx.sqlite.db.SupportSQLiteDatabase
import io.github.prabhuomkar.torchexpo.data.db.model.Task

class TaskSeed {
    companion object {
        fun populateTasks(db: SupportSQLiteDatabase) {
            val tasks = getTasks()
            // TODO: Read json data and run db.execSQL
        }

        private fun getTasks(): List<Task> {
            return TODO()
        }
    }
}