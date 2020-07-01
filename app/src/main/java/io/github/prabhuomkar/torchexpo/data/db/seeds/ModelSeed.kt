package io.github.prabhuomkar.torchexpo.data.db.seeds

import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.prabhuomkar.torchexpo.data.db.model.Model
import io.github.prabhuomkar.torchexpo.util.FileUtil

class ModelSeed {
    companion object {
        fun populateModels(db: SupportSQLiteDatabase) {
            val models = getModels()
            models.forEach { model ->
                db.execSQL(
                    "INSERT into models VALUES(%d, %d, '%s', '%s', '%s', '%s', '%s', '%s', %d)".format(
                        model.id,
                        model.taskId,
                        model.name,
                        model.description,
                        model.paperLink,
                        model.sourceLink,
                        model.downloadLink,
                        model.imageLink,
                        model.size
                    )
                )
            }
        }

        private fun getModels(): Array<Model> {
            val gson = Gson()
            val arrayModelType = object : TypeToken<Array<Model>>() {}.type
            return gson.fromJson(
                FileUtil.getStringFromAssetFile("res/raw/models.json"),
                arrayModelType
            )
        }
    }
}