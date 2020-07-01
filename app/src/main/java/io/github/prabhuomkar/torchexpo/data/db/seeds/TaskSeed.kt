package io.github.prabhuomkar.torchexpo.data.db.seeds

import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.prabhuomkar.torchexpo.data.db.model.Task
import io.github.prabhuomkar.torchexpo.util.FileUtil

class TaskSeed {
    companion object {
        fun populateTasks(db: SupportSQLiteDatabase) {
            val tasks = getTasks()
            tasks.forEach { task ->
                db.execSQL(
                    "INSERT into tasks VALUES(%d, '%s', '%s', '%s', '%s')".format(
                        task.id,
                        task.name,
                        task.description,
                        task.imageLink,
                        task.researchArea
                    )
                )
            }
        }

        private fun getTasks(): Array<Task> {
            val gson = Gson()
            val arrayTaskType = object : TypeToken<Array<Task>>() {}.type
            return gson.fromJson(
                FileUtil.getStringFromAssetFile("res/raw/tasks.json"),
                arrayTaskType
            )
        }
    }
}