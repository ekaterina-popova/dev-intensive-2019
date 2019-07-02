package ru.skillbranch.devintensive.models.enums

enum class TimeUnits(
    var millisecond: Long
) {
    SECOND(1000L),
    MINUTE(60 * SECOND.millisecond),
    HOUR(60 * MINUTE.millisecond),
    DAY(24 * HOUR.millisecond)
}
