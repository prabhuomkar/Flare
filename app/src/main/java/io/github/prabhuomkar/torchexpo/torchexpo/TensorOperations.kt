package io.github.prabhuomkar.torchexpo.torchexpo

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
    }
}