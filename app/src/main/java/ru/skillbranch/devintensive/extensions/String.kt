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

fun String.stripHtml(): String {
    var str: String = this.trim()
    var strTemp: String
    var count = 0
    for (char in str){
        if(char == '<') {
            count++
        }
    }
    for (i in 1..count){
        strTemp = str.substringBefore('<')
        str = "$strTemp ${str.substringAfter(">")}"
    }
    var strEnd = ""
        for (i in str.indices){
        if (((i == 0 || i == str.length-1) && str[i] == ' ') || ((str[i] == ' ') && (str[i-1] == ' ')))
            else {
            strEnd = "$strEnd${str[i]}"
        }
    }
    return strEnd
}