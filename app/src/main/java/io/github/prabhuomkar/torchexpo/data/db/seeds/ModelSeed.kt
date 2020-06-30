package io.github.prabhuomkar.torchexpo.data.db.seeds

import androidx.sqlite.db.SupportSQLiteDatabase
import io.github.prabhuomkar.torchexpo.data.db.model.Model

class ModelSeed {
    companion object {
        fun populateModels(db: SupportSQLiteDatabase) {
            val models = getModels()
            // TODO: Read json data and run db.execSQL
        }

        private fun getModels(): List<Model> {
            return TODO()
        }
    }
}