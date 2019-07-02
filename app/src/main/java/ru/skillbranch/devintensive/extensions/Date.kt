package ru.skillbranch.devintensive.extensions

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

enum class TimeUnits(
    var millisecond: Long
) {
    SECOND(1000L),
    MINUTE(60 * TimeUnits.SECOND.millisecond),
    HOUR(60 * TimeUnits.MINUTE.millisecond),
    DAY(24 * HOUR.millisecond)
}

enum class Intervals(
    val leftBorder: Long,
    val rightBorder: Long
) {
    JUST(0, TimeUnits.SECOND.millisecond),
    FEW_SECONDS_AGO(TimeUnits.SECOND.millisecond, 45 * TimeUnits.SECOND.millisecond),
    MINUTE_AGO(45 * TimeUnits.SECOND.millisecond, 75 * TimeUnits.SECOND.millisecond),
    N_MINUTES_AGO(75 * TimeUnits.SECOND.millisecond, 45 * TimeUnits.MINUTE.millisecond),
    HOUR_AGO(45 * TimeUnits.MINUTE.millisecond, 75 * TimeUnits.MINUTE.millisecond),
    N_HOURS_AGO(45 * TimeUnits.MINUTE.millisecond, 22 * TimeUnits.HOUR.millisecond),
    DAY_AGO(22 * TimeUnits.HOUR.millisecond, 26 * TimeUnits.HOUR.millisecond),
    N_DAYS_AGO(22 * TimeUnits.HOUR.millisecond, 360 * TimeUnits.DAY.millisecond),
    MORE_THAN_A_YEAR_AGO(360 * TimeUnits.DAY.millisecond, 0),
}
