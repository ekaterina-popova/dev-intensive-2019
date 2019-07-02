package ru.skillbranch.devintensive.models.enums

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
