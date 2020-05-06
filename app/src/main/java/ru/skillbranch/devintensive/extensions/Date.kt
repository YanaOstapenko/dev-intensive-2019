package ru.skillbranch.devintensive.extensions

import android.icu.util.DateInterval
import androidx.core.util.rangeTo
import androidx.core.util.toClosedRange
import ru.skillbranch.devintensive.models.User
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

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

fun Date.setTimeLastVisit(dateText:String): Int {
    val k: Int = 4
    return k
}

private fun pluralForm(n: Int, form1: String, form2: String, form5: String): String{
    val n1 = n % 100
    val n2 = n % 10
    if (n1 > 10 && n1 < 20) return form5
    if (n2 > 1 && n2 < 5) return form2
    if (n2 == 1) return form1
    return form5
}

fun Date.humanizeDiff(): String {
    val longDiff = System.currentTimeMillis() - this.time
    val time1 =
        if(longDiff<1000) "только что"
        else if (longDiff<45000) "несколько секунд назад"
        else if (longDiff<75000) "минуту назад"
        else if (longDiff<(45*60*1000)) "${longDiff / (1000*60)} ${pluralForm((longDiff/1000/60).toInt(),"минута", "минуты", "минут")} назад"
        else if (longDiff<(75*60*1000)) "час назад"
        else if (longDiff<(22.toLong()*60*60*1000)) "${longDiff / (1000*60*60)} ${pluralForm((longDiff/1000/60/60).toInt(),"час", "часа", "часов")} назад"
        else if (longDiff<(26.toLong()*60*60*1000)) "день назад"
        else if (longDiff<(360.toLong()*24*60*60*1000)) "${longDiff / (1000*60*60*24)} ${pluralForm((longDiff/1000/60/60/24).toInt(),"день", "дня", "дней")} назад"
        else "более года назад"
    return time1
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}