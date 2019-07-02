package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.enums.Intervals
import ru.skillbranch.devintensive.enums.TimeUnits
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * TimeUnits.SECOND.millisecond
        TimeUnits.MINUTE -> value * TimeUnits.MINUTE.millisecond
        TimeUnits.HOUR -> value * TimeUnits.HOUR.millisecond
        TimeUnits.DAY -> value * TimeUnits.DAY.millisecond
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = abs(this.time - date.time)
    for (i in 0 until Intervals.values().size) {
        if (Intervals.values()[i].leftBorder < diff && diff <= Intervals.values()[i].rightBorder) {
            if (diff == Intervals.JUST.leftBorder) {
                return "только что"
            } else {
                when (Intervals.values()[i].ordinal) {
                    0 -> return "только что"
                    1 -> return "несколько секунд назад"
                    2 -> return "минуту назад"
                    3 -> return createMessageAboutMinutes(diff)
                    4 -> return "час назад"
                    5 -> return createMessageAboutHours(diff)
                    6 -> return "день назад"
                    7 -> return createMessageAboutDays(diff)
                }
            }
        } else if (Intervals.MORE_THAN_A_YEAR_AGO.leftBorder < diff) {
            return "более года назад"
        }
        return ""
    }
    return ""
}

fun createMessageAboutMinutes(diff: Long): String {
    val minutes = diff / TimeUnits.MINUTE.millisecond
    val lastDigit = minutes % 10
    var message = "$minutes "
    message += if (minutes in 11..14) {
        "минут"
    } else {
        when (lastDigit) {
            1L -> "минуту"
            2L, 3L, 4L -> "минуты"
            else -> "минут"
        }
    }
    return "$message назад"
}

fun createMessageAboutHours(diff: Long): String {
    val hours = diff / TimeUnits.HOUR.millisecond
    val lastDigit = hours % 10
    var message = "$hours "
    message += if (hours in 11..14) {
        "часов"
    } else {
        when (lastDigit) {
            1L -> "час"
            2L, 3L, 4L -> "часа"
            else -> "часов"
        }
    }
    return "$message назад"
}

fun createMessageAboutDays(diff: Long): String {
    val days = diff / TimeUnits.DAY.millisecond
    val lastDigit = days % 10
    var message = "$days "
    message += if (days in 11..14) {
        "дней"
    } else {
        when (lastDigit) {
            1L -> "день"
            2L, 3L, 4L -> "дня"
            else -> "дней"
        }
    }
    return "$message назад"
}
