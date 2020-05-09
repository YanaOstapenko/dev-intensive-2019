package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{
        val parts: List<String>? = fullName?.trim()?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        if(firstName.isNullOrEmpty()) firstName = null
        if(lastName.isNullOrEmpty()) lastName = null
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val result = payload.replace(Regex("[абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ ]")) {
            when (it.value) {
                "а" -> "a"
                "б" -> "b"
                "в" -> "v"
                "г" -> "g"
                "д" -> "d"
                "е" -> "e"
                "ё" -> "e"
                "ж" -> "zh"
                "з" -> "z"
                "и" -> "i"
                "й" -> "i"
                "к" -> "k"
                "л" -> "l"
                "м" -> "m"
                "н" -> "n"
                "о" -> "o"
                "п" -> "p"
                "р" -> "r"
                "с" -> "s"
                "т" -> "t"
                "у" -> "u"
                "ф" -> "f"
                "х" -> "h"
                "ц" -> "c"
                "ч" -> "ch"
                "ш" -> "sh"
                "щ" -> "sh"
                "ъ" -> ""
                "ы" -> "i"
                "ь" -> ""
                "э" -> "e"
                "ю" -> "yu"
                "я" -> "ya"
                "А" -> "A"
                "Б" -> "B"
                "В" -> "V"
                "Г" -> "G"
                "Д" -> "D"
                "Е" -> "E"
                "Ё" -> "E"
                "Ж" -> "Zh"
                "З" -> "Z"
                "И" -> "I"
                "Й" -> "I"
                "К" -> "K"
                "Л" -> "L"
                "М" -> "M"
                "Н" -> "N"
                "О" -> "O"
                "П" -> "P"
                "Р" -> "R"
                "С" -> "S"
                "Т" -> "T"
                "У" -> "U"
                "Ф" -> "F"
                "Х" -> "H"
                "Ц" -> "C"
                "Ч" -> "Ch"
                "Ш" -> "Sh"
                "Щ" -> "Sh"
                "Ъ" -> ""
                "Ы" -> "I"
                "Ь" -> ""
                "Э" -> "E"
                "Ю" -> "Yu"
                "Я" -> "Ya"
                " " -> divider
                else -> it.value
            }
        }
        return result
    }



    fun toInitials(firstName: String?, lastName: String?): String? {

        val fname = firstName?.replace(Regex("[^a-zA-Zа-яА-Я]"), "")?.take(1)?.capitalize()
        val lname = lastName?.replace(Regex("[^a-zA-Zа-яА-Я]"), "")?.take(1)?.capitalize()

        return when (Pair(fname.isNullOrEmpty(), lname.isNullOrEmpty())) {
            Pair(true, true) -> null
            Pair(true, false) -> lname
            Pair(false, true) -> fname
            else -> fname+lname
        }
    }
}