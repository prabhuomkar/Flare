package io.github.prabhuomkar.torchexpo.torchexpo.tokenization

import com.google.common.base.Ascii
import com.google.common.collect.Iterables

class BasicTokenizer(private val doLowerCase: Boolean) {

    fun tokenize(text: String?): List<String> {
        val cleanedText = cleanText(text)
        val origTokens: List<String> = tokenizeWhitespace(cleanedText)
        val stringBuilder = java.lang.StringBuilder()
        var lowerToken: String = ""
        for (token in origTokens) {
            lowerToken = if (doLowerCase) Ascii.toLowerCase(token) else token
            val list: List<String> = runSplitOnPunctuation(lowerToken)
            for (subToken in list) {
                stringBuilder.append(subToken).append(" ")
            }
        }
        return tokenizeWhitespace(stringBuilder.toString())
    }

    private fun cleanText(text: String?): String {
        val stringBuilder = StringBuilder("")
        for (index in text!!.indices) {
            val char: Char = text[index]
            if (charIsInvalid(char) || charIsControl(char)) {
                continue
            }
            if (charIsWhitespace(char)) {
                stringBuilder.append(" ")
            } else {
                stringBuilder.append(char)
            }
        }
        return stringBuilder.toString()
    }

    companion object {
        fun tokenizeWhitespace(text: String) = text.split(" ")
    }

    private fun runSplitOnPunctuation(text: String?): List<String> {
        val tokens: MutableList<String> = ArrayList()
        var startNewWord = true
        for (i in text!!.indices) {
            val ch: Char = text[i]
            if (charIsPunctuation(ch)) {
                tokens.add(ch.toString())
                startNewWord = true
            } else {
                if (startNewWord) {
                    tokens.add("")
                    startNewWord = false
                }
                tokens[tokens.size - 1] = Iterables.getLast(tokens) + ch
            }
        }

        return tokens
    }

    private fun charIsInvalid(char: Char): Boolean = (char.toInt() == 0 || char.toInt() == 0xfffd)

    private fun charIsControl(char: Char): Boolean = when {
        Character.isWhitespace(char) -> false
        else -> {
            val type: Int = Character.getType(char)
            (type == Character.CONTROL.toInt() || type == Character.FORMAT.toInt())
        }
    }

    private fun charIsWhitespace(char: Char): Boolean = when {
        Character.isWhitespace(char) -> true
        else -> {
            val type: Int = Character.getType(char)
            (type == Character.SPACE_SEPARATOR.toInt() ||
                    type == Character.LINE_SEPARATOR.toInt() ||
                    type == Character.PARAGRAPH_SEPARATOR.toInt())
        }
    }

    private fun charIsPunctuation(char: Char): Boolean {
        val type: Int = Character.getType(char)
        return (type == Character.CONNECTOR_PUNCTUATION.toInt() ||
                type == Character.DASH_PUNCTUATION.toInt() ||
                type == Character.START_PUNCTUATION.toInt() ||
                type == Character.END_PUNCTUATION.toInt() ||
                type == Character.INITIAL_QUOTE_PUNCTUATION.toInt() ||
                type == Character.FINAL_QUOTE_PUNCTUATION.toInt() ||
                type == Character.OTHER_PUNCTUATION.toInt())
    }
}