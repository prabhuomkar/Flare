package io.github.prabhuomkar.torchexpo.torchexpo.tokenization

class FullTokenizer(inputDictionary: Map<String, Int>, doLowerCase: Boolean) {

    private val basicTokenizer: BasicTokenizer = BasicTokenizer(doLowerCase)
    private val wordPieceTokenizer: WordPieceTokenizer = WordPieceTokenizer(inputDictionary)
    private val dictionary: Map<String, Int> = inputDictionary

    fun tokenize(text: String): MutableList<String> {
        val splitTokens: MutableList<String> = ArrayList()
        for (token in basicTokenizer.tokenize(text)) splitTokens.addAll(
            wordPieceTokenizer.tokenize(
                token
            )
        )
        return splitTokens.toMutableList()
    }

    fun convertTokensToIds(tokens: List<String>): MutableList<Int> {
        val outputIds: MutableList<Int> = ArrayList()
        for (token in tokens) outputIds.add(dictionary.get(token)!!)
        return outputIds
    }
}