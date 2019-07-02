package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val fullName1 = checkString(fullName)
        val parts: List<String>? = fullName1?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstName1 = checkString(firstName)
        val lastName1 = checkString(lastName)
        val firstInitial = firstName1?.getOrNull(0)
        val secondInitial = lastName1?.getOrNull(0)
        var result = ""
        if (firstInitial != null) {
            result += firstInitial.toUpperCase()
        }
        if (secondInitial != null) {
            result += secondInitial.toUpperCase()
        }
        return checkString(result)
    }

    fun transliteration(fullName: String, divider: String? = " "): String? {
        val divider1 = checkDivider(divider)
        var newFullName = ""
        for (i in fullName.indices) {
            newFullName += convertCharacter(fullName[i])
        }
        val parts = newFullName.split(" ")
        return parts[0] + divider1 + parts[1]
    }

    private fun convertCharacter(char: Char): String {
        val isUpperCase = char.isUpperCase()
        val char1 = char.toString().toLowerCase()
        for (i in 0 until Vocabulary.values().size) {
            if (char1 == Vocabulary.values()[i].russianChar) {
                return if (isUpperCase) {
                    Vocabulary.values()[i].englishChar.toUpperCase()
                } else {
                    Vocabulary.values()[i].englishChar
                }
            }
        }
        return char.toString()
    }

    private fun checkString(stringValue: String?): String? {
        return if (stringValue != null) {
            if (stringValue === "" || stringValue.trim().isEmpty()) null else stringValue
        } else {
            stringValue
        }
    }

    private fun checkDivider(divider: String?): String {
        return if (divider == null || divider === "") " " else divider
    }
}

enum class Vocabulary(
    val englishChar: String,
    val russianChar: String
) {
    CHAR_1("a", "а"),
    CHAR_2("b", "б"),
    CHAR_3("v", "в"),
    CHAR_4("g", "г"),
    CHAR_5("d", "д"),
    CHAR_6("e", "е"),
    CHAR_7("e", "ё"),
    CHAR_8("zh", "ж"),
    CHAR_9("z", "з"),
    CHAR_10("i", "и"),
    CHAR_11("i", "й"),
    CHAR_12("k", "к"),
    CHAR_13("l", "л"),
    CHAR_14("m", "м"),
    CHAR_15("n", "н"),
    CHAR_16("o", "о"),
    CHAR_17("p", "п"),
    CHAR_18("a", "р"),
    CHAR_19("s", "с"),
    CHAR_20("t", "т"),
    CHAR_21("u", "у"),
    CHAR_22("f", "ф"),
    CHAR_23("h", "х"),
    CHAR_24("c", "ц"),
    CHAR_25("ch", "ч"),
    CHAR_26("sh", "ш"),
    CHAR_27("sh", "щ"),
    CHAR_28("", "ъ"),
    CHAR_29("i", "ы"),
    CHAR_30("", "ь"),
    CHAR_31("e", "э"),
    CHAR_32("yu", "ю"),
    CHAR_33("ya", "я"),
}
