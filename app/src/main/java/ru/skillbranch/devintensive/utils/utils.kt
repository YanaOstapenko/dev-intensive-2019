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

    private fun toInitial(str: String?): String? {
        return str?.replace(Regex("[^a-zA-Z]"), "")?.take(1)?.capitalize()
    }
    fun toInitials(firstName: String?, lastName: String?): String? {
//        val list = listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H',
//            'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M')

        val fname = toInitial(firstName?.let { transliteration(it) })
        val lname = toInitial(lastName?.let { transliteration(it) })

        return when (Pair(fname.isNullOrEmpty(), lname.isNullOrEmpty())) {
            Pair(true, true) -> null
            Pair(true, false) -> lname
            Pair(false, true) -> fname
            else -> fname+lname
        }

//        return if (firstName?.trim().isNullOrEmpty() && lastName?.trim().isNullOrEmpty()) "null"
//        else if(firstName?.trim().isNullOrEmpty() && !lastName?.trim().isNullOrEmpty()) "${lastName?.trim()?.capitalize()?.let { transliteration(it) }?.get(0)}"
//        else if(firstName?.trim().isNullOrEmpty() && !list.contains(lastName?.trim()?.capitalize()?.let { transliteration(it) }?.get(0))) "null"
//
//        else if(!firstName?.trim().isNullOrEmpty() && lastName?.trim().isNullOrEmpty()) "${firstName?.trim()?.capitalize()?.let { transliteration(it) }?.get(0)}"
//        else if(!list.contains(firstName?.trim()?.capitalize()?.let { transliteration(it) }?.get(0)) && lastName?.trim().isNullOrEmpty()) "null"
//
//        else if(!firstName?.trim().isNullOrEmpty() && !list.contains(lastName?.trim()?.capitalize()?.let { transliteration(it) }?.get(0))) "${firstName?.trim()?.capitalize()?.let { transliteration(it) }?.get(0)}"
//
//        else if(!list.contains(firstName?.trim()?.capitalize()?.let { transliteration(it) }?.get(0)) && !lastName?.trim().isNullOrEmpty()) "${lastName?.trim()?.capitalize()?.let { transliteration(it) }?.get(0)}"
//
//        else if(!list.contains(firstName?.trim()?.capitalize()?.let { transliteration(it) }?.get(0)) &&  !list.contains(lastName?.trim()?.capitalize()?.let { transliteration(it) }?.get(0))) "null"
//
//            else "${firstName?.trim()?.capitalize()?.let { transliteration(it) }?.get(0)} ${lastName?.trim()?.capitalize()?.let { transliteration(it) }?.get(0)}"


    }
}