package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.models.enums.Vocabulary

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
