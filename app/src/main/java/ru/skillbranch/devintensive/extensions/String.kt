/*******************************************************************************
 * Created by Yana Ostapenko, 2020
 ******************************************************************************/

package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
    val str: String = this
    return when{
        str.trim().length<=value -> str.trim()
        str.trim().length>value -> "${str.take(value).trim()}..."
        else -> ""
    }
}

fun String.stripHtml(): String{
    val str: String = this.trim()
    return str.substringAfter('>').substringBeforeLast('<').trim().replace("  " +
            "     ", " ").replace("   ", " ").replace("  ", " ")
}