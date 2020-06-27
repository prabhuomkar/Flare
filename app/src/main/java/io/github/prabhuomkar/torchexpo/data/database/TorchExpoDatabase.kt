package io.github.prabhuomkar.torchexpo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import io.github.prabhuomkar.torchexpo.DATABASE_NAME
import io.github.prabhuomkar.torchexpo.data.database.dao.ModelDao
import io.github.prabhuomkar.torchexpo.data.database.dao.TaskDao
import io.github.prabhuomkar.torchexpo.data.model.Model
import io.github.prabhuomkar.torchexpo.data.model.Task

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
            ).addCallback(CALLBACK).build()

        private val CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateTasks(db)
                populateModels(db)
            }
        }

        private fun populateTasks(db: SupportSQLiteDatabase) {
            db.execSQL("INSERT into tasks VALUES(1, 'Image Classification', 'Recognize images and find their associated class', 'https://paperswithcode.com/media/thumbnails/task/task-0000000951-52325f45_O0tAMly.jpg', 'Vision')")
            db.execSQL("INSERT into tasks VALUES(2, 'Image Segmentation', 'Cluster parts of an image together which belong to the same object class', 'https://paperswithcode.com/media/thumbnails/task/task-0000000885-5d13a681_ZOc2kft.jpg', 'Vision')")
            db.execSQL("INSERT into tasks VALUES(3, 'Image Generation', 'Task of generating new images from an existing dataset', 'https://paperswithcode.com/media/thumbnails/task/task-0000000001-8c5e89b0_b2Odeg7.jpg', 'Generative')")
        }

        private fun populateModels(db: SupportSQLiteDatabase) {
            db.execSQL("INSERT into models VALUES(1, 1, 'ResNet-18', 'Next generation ResNets, more efficient and accurate', 'https://arxiv.org/abs/1512.03385', 'https://pytorch.org/hub/pytorch_vision_resnext/', 'https://rebrand.ly/torchexpo-resnet-18', 0, 'https://pytorch.org/assets/images/resnet.png', 47)")
            db.execSQL("INSERT into models VALUES(2, 1, 'GoogleNet', 'Deep Convolutional Neural Network architecture codenamed \"Inception\"', 'https://arxiv.org/abs/1512.03385', 'https://pytorch.org/hub/pytorch_vision_googlenet/', 'https://rebrand.ly/torchexpo-googlenet', 0, 'https://pytorch.org/assets/images/googlenet2.png', 27)")
            db.execSQL("INSERT into models VALUES(3, 1, 'AlexNet', 'Deep Convolutional Neural Network won 2012 ImageNet', 'https://arxiv.org/abs/1512.03385', 'https://pytorch.org/hub/pytorch_vision_alexnet/', 'https://rebrand.ly/torchexpo-alexnet', 0, 'https://pytorch.org/assets/images/alexnet2.png', 244)")
            db.execSQL("INSERT into models VALUES(4, 2, 'DeeplabV3-ResNet101', 'DeepLabV3 model with a ResNet-101 backbone', 'https://arxiv.org/abs/1706.05587', 'https://pytorch.org/hub/pytorch_vision_deeplabv3_resnet101/', 'https://rebrand.ly/torchexpo-deeplabv3-resnet101', 0, 'https://pytorch.org/assets/images/deeplab2.png', 245)")
            db.execSQL("INSERT into models VALUES(5, 2, 'FCN-ResNet101', 'Fully-Convolutional Network model with a ResNet-101 backbone', 'https://arxiv.org/abs/1411.4038', 'https://pytorch.org/hub/pytorch_vision_fcn_resnet101/', 'https://rebrand.ly/torchexpo-fcn-resnet101', 0, 'https://pytorch.org/assets/images/fcn2.png', 218)")
        }
    }
}
