package io.github.prabhuomkar.torchexpo.torchexpo.tokenization

class WordPieceTokenizer(vocab: Map<String, Int>) {

    private var dictionary: Map<String, Int> = vocab
    private val UNKNOWN_TOKEN = "[UNK]"
    private val MAX_INPUTCHARS_PER_WORD = 200

    fun tokenize(text: String?): List<String> {
        val outputTokens: MutableList<String> = ArrayList()
        for (token in BasicTokenizer.tokenizeWhitespace(text!!)) {
            if (token.length > MAX_INPUTCHARS_PER_WORD) {
                outputTokens.add(UNKNOWN_TOKEN)
                continue
            }
            var isBad = false
            var start = 0
            val subTokens: MutableList<String> = ArrayList()
            while (start < token.length) {
                var curSubStr = ""
                var end = token.length
                while (start < end) {
                    val subStr =
                        if (start == 0) token.substring(start, end) else "##" + token.substring(
                            start,
                            end
                        )
                    if (dictionary.containsKey(subStr)) {
                        curSubStr = subStr
                        break
                    }
                    end--
                }
                if ("" == curSubStr) {
                    isBad = true
                    break
                }
                subTokens.add(curSubStr)
                start = end
            }
            if (isBad) {
                outputTokens.add(UNKNOWN_TOKEN)
            } else {
                outputTokens.addAll(subTokens)
            }
        }
        return outputTokens
    }
}