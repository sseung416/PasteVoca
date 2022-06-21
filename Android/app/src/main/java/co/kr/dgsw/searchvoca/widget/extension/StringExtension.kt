package co.kr.dgsw.searchvoca.widget.extension

fun String.removeSpecialSymbol(): String {
    val regex = Regex("[^A-Za-z0-9]")
    return regex.replace(this, "")
}

fun String.removeWhiteSpaces(): String = this.filter { !it.isWhitespace() }