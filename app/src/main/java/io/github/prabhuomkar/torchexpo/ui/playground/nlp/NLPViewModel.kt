package io.github.prabhuomkar.torchexpo.ui.playground.nlp

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.AndroidViewModel
import io.github.prabhuomkar.torchexpo.R
import io.github.prabhuomkar.torchexpo.torchexpo.TensorOperations
import io.github.prabhuomkar.torchexpo.ui.playground.common.IMDb
import io.github.prabhuomkar.torchexpo.util.FileUtil
import kotlinx.android.synthetic.main.sentiment_analysis_fragment.view.*
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.Tensor
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class NLPViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext
    private var module: Module? = null
    private var dictionary: MutableMap<String, Int> = HashMap()

    init {
        loadDictionary()
    }

    fun clearPlayground() {
        module?.destroy()
    }

    private fun loadDictionary() {
        val vocabFilePath = FileUtil.getAssetFilePath(context, "imdb_vocab.txt")
        if (!vocabFilePath.isNullOrEmpty()) {
            BufferedReader(FileReader(File(vocabFilePath))).use { reader ->
                var index = 0
                while (reader.ready()) {
                    val key: String = reader.readLine()
                    dictionary.put(key, index++)
                }
            }
        }
    }

    fun loadIMDbSample(view: View) {
        view.rootView.inputText.setText(IMDb.SAMPLES[(0..3).random()])
    }

    fun runSentimentAnalysis(view: View, modelName: String?) {
        if (!modelName.isNullOrEmpty()) {
            module = Module.load(FileUtil.getModelAssetFilePath(context, modelName))
            val inputText = view.rootView.inputText.text.toString()
            val features = TensorOperations.convert(dictionary, inputText)
            val curSeqLen = features.size
            val inputIds = LongArray(curSeqLen)
            for (j in 0 until curSeqLen) inputIds[j] = features.get(j).toLong()
            val shape = longArrayOf(1, curSeqLen.toLong())
            val inputTensor = Tensor.fromBlob(inputIds, shape)
            val output = module!!.forward(IValue.from(inputTensor))
            val outputTuple = output.toTuple()
            val outputTensor = outputTuple[0].toTensor()
            val result = outputTensor.dataAsFloatArray
            if (result.isNotEmpty()) {
                view.rootView.sentiment_positive_score.text =
                    context.getString(R.string.score_more_precision).format(result[0])
                view.rootView.sentiment_negative_score.text =
                    context.getString(R.string.score_more_precision).format(result[1])
            }
        }
    }
}