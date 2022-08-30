package co.kr.searchvoca.shared.android

inline fun <reified T : Enum<T>> Enum<T>.next(values: Array<T> = enumValues()) =
    values[(ordinal + 1) % values.size]