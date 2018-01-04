package lb.util.ext


fun String.pluralize(): String
{
    val word = this.trimEnd()
    if (word.isEmpty()) error /*throw IllegalArgumentException*/("Impossible to pluralize an empty or blank string")

    val irr = irregilarPlurals[word]
    if (irr != null) return irr

    val ending = word.lastChar.toLowerCase()
    return when (ending) {
        's'  -> word + "es"
        'y'  -> word.changeSuffix(1, "ies")
        else -> word + "s"
    }
}

internal val irregilarPlurals =
        mapOf( "index"  to "indices",
               "vertex" to "vertices",
               "schema" to "schemata",
               "month"  to "months",
               "this"   to "these",
               "that"   to "those",
               "it"     to "they"
             )


val String.lastChar: Char
    get() {
        val n = this.length
        if (n == 0) return '\u0000'
        return this[n-1]
    }


fun String.changeSuffix(oldSuffixLength: Int, newSuffix: String): String =
        this.substring(0, this.length - oldSuffixLength) + newSuffix
