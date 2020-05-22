/*******************************************************************************
 * Created by Yana Ostapenko, 2020
 ******************************************************************************/

package ru.skillbranch.devintensive.models

import java.util.regex.Pattern


class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {
    fun askQuestion(): String = when (question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question

    }
    companion object Counter{
        private var count = 0
        fun currentCount() = count
        fun countPlus(){
            ++count
        }
        fun countNull(){
            count = 0
        }
    }



   /* val STRING_ANSWER_UPPER = Pattern.compile(
        """[A-Z][^A-Z]+"""
    )

    val STRING_ANSWER_LOW = Pattern.compile(
        "[a-zа-я]"
    )*/

    val STRING_ANSWER = Pattern.compile(
        "[a-zA-Zа-яА-Я]{1,256}"
    )
    val YEAR_ANSWER = Pattern.compile(
        "[0123456789]{0,1000}"
    )

    val NUMBER_ANSWER = Pattern.compile(
        "[0123456789]{7}"
    )

    /*private fun isValidStrUpper(str: String): Boolean {
        val pattern = STRING_ANSWER_UPPER
        return pattern.matcher(str).matches()
    }

    private fun isValidStrLow(str: String): Boolean {
        val pattern = STRING_ANSWER_LOW
        return pattern.matcher(str).matches()
    }*/

    private fun isValidStr(str: String): Boolean {
        val pattern = STRING_ANSWER
        return pattern.matcher(str).matches()
    }

    private fun isValidYear(year: String): Boolean{
        val pattern = YEAR_ANSWER
        return pattern.matcher(year).matches()
    }

    private fun isValidNumber(number: String): Boolean {
        val pattern = NUMBER_ANSWER
        return pattern.matcher(number).matches()
    }

    fun answer(answer: String) : Pair <String, Triple<Int, Int, Int>>{

        return if(question.answers.contains(answer)){
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        }
        else{
            countPlus()
            if(currentCount() > 3){
                countNull()
                question = Question.NAME
                status = Status.NORMAL
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }
            else{
            status = status.nextStatus()
            "Это неправильный ответ\n${question.question}" to status.color}
        }
    }

    fun listenAnswer(answer: String) : Pair<String, Triple<Int, Int, Int>>{
        if(question == Question.NAME){
                return if (answer[0].isUpperCase()){
                    answer(answer.toLowerCase())
                } else {
                    "Имя должно начинаться с заглавной буквы\n" +
                            "${question.question}"  to status.color
                }
        }
        if(question == Question.PROFESSION){
            return if (answer[0].isLowerCase()){
                answer(answer)
            } else {
                "Профессия должна начинаться со строчной буквы\n" +
                        "${question.question}"  to status.color
            }
        }
        else
            if(question == Question.MATERIAL){
                return if (isValidStr(answer)){
                    answer(answer.toLowerCase())
                } else {
                    "Материал не должен содержать цифр\n" +
                            "${question.question}"  to status.color
                }
            }
        else
            if(question == Question.BDAY){
               return if(isValidYear(answer)){
                   answer(answer)
               } else {
                   "Год моего рождения должен содержать только цифры\n" +
                           "${question.question}"  to status.color
               }
            }
        else
                if(question == Question.SERIAL){
                    return if(isValidNumber(answer)){
                        answer(answer)
                    } else {
                        "Серийный номер содержит только цифры, и их 7\n" +
                                "${question.question}"  to status.color
                    }
                }
            return "Отлично - ты справился\n${question.question}" to status.color
    }

    enum class Status(val color : Triple<Int, Int, Int>){
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status{
            return if(this.ordinal < values().lastIndex){
                values() [this.ordinal +1]
            } else{
                values() [0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>){
        NAME("Как меня зовут?", listOf("Бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }
}