package io.github.prabhuomkar.torchexpo.torchexpo

import io.github.prabhuomkar.torchexpo.torchexpo.tokenization.FullTokenizer
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

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

        data class FeatureQA(
            val inputIds: IntArray,
            val inputMask: IntArray,
            val segmentIds: IntArray,
            val origTokens: List<String>,
            val tokenToOrigMap: Map<Int, Int>
        )

        fun featureConvertForQuestionAnswering(
            inputDictionary: Map<String, Int>, query: String,
            context: String
        ): FeatureQA {
            val tokenizer = FullTokenizer(inputDictionary, true)
            val maxSeqLen = 384
            val maxQueryLen = 64
            val queryTokens: MutableList<String> = tokenizer.tokenize(query)
            if (queryTokens.size > maxQueryLen) {
                queryTokens.subList(0, maxQueryLen)
            }
            val origTokens: List<String> =
                context.trim().split("\\s+")
            val tokenToOrigIndex: MutableList<Int> = ArrayList()
            var allDocTokens: MutableList<String> = ArrayList()
            for (i in origTokens.indices) {
                val token = origTokens[i]
                val subTokens: List<String> = tokenizer.tokenize(token)
                for (subToken in subTokens) {
                    tokenToOrigIndex.add(i)
                    allDocTokens.add(subToken)
                }
            }
            val maxContextLen = maxSeqLen - queryTokens.size - 3
            if (allDocTokens.size > maxContextLen) {
                allDocTokens = allDocTokens.subList(0, maxContextLen)
            }
            val tokens: MutableList<String> = ArrayList()
            val segmentIds: MutableList<Int> = ArrayList()
            val tokenToOrigMap: MutableMap<Int, Int> = HashMap()
            tokens.add("[CLS]")
            segmentIds.add(0)
            for (queryToken in queryTokens) {
                tokens.add(queryToken)
                segmentIds.add(0)
            }
            tokens.add("[SEP]")
            segmentIds.add(0)
            for (i in allDocTokens.indices) {
                val docToken = allDocTokens[i]
                tokens.add(docToken)
                segmentIds.add(1)
                tokenToOrigMap[tokens.size] = tokenToOrigIndex[i]
            }
            tokens.add("[SEP]")
            segmentIds.add(1)
            val inputIds = tokenizer.convertTokensToIds(tokens)
            val inputMask: MutableList<Int> =
                ArrayList(Collections.nCopies(inputIds.size, 1))
            while (inputIds.size < maxSeqLen) {
                inputIds.add(0)
                inputMask.add(0)
                segmentIds.add(0)
            }
            return FeatureQA(
                inputIds.toIntArray(), inputMask.toIntArray(), segmentIds.toIntArray(),
                origTokens, tokenToOrigMap
            )
        }
    }
}