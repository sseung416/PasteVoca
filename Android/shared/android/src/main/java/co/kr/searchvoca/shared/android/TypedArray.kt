package co.kr.searchvoca.shared.android

import android.content.res.TypedArray
import androidx.core.content.res.use

/**
 * use 함수에서 리시버를 제공하는 형태
 *
 * @see TypedArray.use
 * */
inline fun TypedArray.useWith(block: TypedArray.() -> Unit): TypedArray {
    this.use(block)
    return this
}