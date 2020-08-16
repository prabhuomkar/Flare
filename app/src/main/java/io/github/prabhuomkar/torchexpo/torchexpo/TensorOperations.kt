package io.github.prabhuomkar.torchexpo.torchexpo

import io.github.prabhuomkar.torchexpo.torchexpo.tokenization.FullTokenizer

class TensorOperations {
    companion object {
        fun argmax(data: FloatArray, dim: Int, height: Int, width: Int): IntArray {
            val result = IntArray(height * width)
            for (i in 0 until height * width) {
                var maxDim = 0
                var maxVal = data[i]
                for (j in 1 until dim) {
                    if (data[(i + height * width * j)] > maxVal) {
                        maxVal = data[(i + height * width * j)]
                        maxDim = j
                    }
                }
                result[i] = maxDim
            }
            return result
        }

        fun featureConvertForSentimentAnalysis(
            inputDictionary: Map<String, Int>,
            text: String
        ): IntArray {
            val tokenizer = FullTokenizer(inputDictionary, true)
            val maxSeqLen = 256
            var tokens: MutableList<String> = tokenizer.tokenize(text)
            tokens.add(0, "[CLS]")
            if (tokens.size > maxSeqLen - 1) {
                tokens = tokens.subList(0, maxSeqLen - 1)
            }
            tokens.add("[SEP]")
            val inputIds: MutableList<Int> = tokenizer.convertTokensToIds(tokens)
            while (inputIds.size < maxSeqLen) {
                inputIds.add(0)
            }
            return inputIds.toIntArray()
        }
    }
}