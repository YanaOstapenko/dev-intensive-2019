package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND):Date{
    var time = this.time

    time +=when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun TimeUnits.plural(value: Int): String{
    return when (this) {
        TimeUnits.SECOND -> "$value ${pluralForm(value.toInt(), "секунду", "секунды", "секунд")}"
        TimeUnits.MINUTE -> "$value ${pluralForm(value.toInt(), "минуту", "минуты", "минут")}"
        TimeUnits.HOUR -> "$value ${pluralForm(value.toInt(), "час", "часа", "часов")}"
        TimeUnits.DAY -> "$value ${pluralForm(value.toInt(), "день", "дня", "дней")}"
    }
}

private fun pluralForm(n: Int, form1: String, form2: String, form5: String): String{
    val n1 = n % 100
    val n2 = n % 10
    if (n1 in 11..19) return form5
    if (n2 in 2..4) return form2
    if (n2 == 1) return form1
    return form5
}

fun Date.humanizeDiff(): String {
    val longDiff = System.currentTimeMillis() - this.time
    return when {
        longDiff <= -(360.toLong()*24*60*60*1000) -> "более чем через год"
        longDiff in -(360.toLong()*24*60*60*1000) until -(26.toLong()*60*60*1000)+1 -> "через ${-longDiff / (1000*60*60*24)} ${pluralForm((-longDiff/1000/60/60/24).toInt(),"день", "дня", "дней")}"
        longDiff in -(26.toLong()*60*60*1000) until -(22.toLong()*60*60*1000)+1 -> "через день"
        longDiff in -(22.toLong()*60*60*1000) until -(75*60*1000)+1 -> "через ${-longDiff / (1000*60*60)} ${pluralForm((-longDiff/1000/60/60).toInt(),"час", "часа", "часов")}"
        longDiff in -(75*60*1000) until -(45*60*1000)+1 -> "через час"
        longDiff in -(45*60*1000) until -75001 -> "через ${-longDiff / (1000*60)} ${pluralForm((-longDiff/1000/60).toInt(),"минуту", "минуты", "минут")}"
        longDiff in -75000..-45001 -> "через минуту"
        longDiff in -45000..0 -> "через несколько секунд"
        longDiff in 0..1000 -> "только что"
        longDiff in 1001..45000 -> "несколько секунд назад"
        longDiff in 45001..75000 -> "минуту назад"
        longDiff in 75001 until (45*60*1000) -> "${longDiff / (1000*60)} ${pluralForm((longDiff/1000/60).toInt(),"минута", "минуты", "минут")} назад"
        longDiff in (45*60*1000)+1 until (75*60*1000) -> "час назад"
        longDiff in (75*60*1000)+1 until (22.toLong()*60*60*1000) -> "${longDiff / (1000*60*60)} ${pluralForm((longDiff/1000/60/60).toInt(),"час", "часа", "часов")} назад"
        longDiff in (22.toLong()*60*60*1000)+1 until (26.toLong()*60*60*1000) -> "день назад"
        longDiff in (26.toLong()*60*60*1000)+1 until (360.toLong()*24*60*60*1000) -> "${longDiff / (1000*60*60*24)} ${pluralForm((longDiff/1000/60/60/24).toInt(),"день", "дня", "дней")} назад"
        longDiff >= (360.toLong()*24*60*60*1000) -> "более года назад"
        else -> "хз"
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}