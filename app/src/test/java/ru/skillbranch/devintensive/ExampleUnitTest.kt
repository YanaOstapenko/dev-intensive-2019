package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

//    @Test
//    fun test_instance() {
//        val user2 = User("2","John", "Doe")
//    }

    @Test
    fun test_factory(){
        val user1 = User.makeUser("Yana Ostapenko")
        val user2 = User.makeUser("")
        val user3 = User.makeUser("     ")
        val user4 = User.makeUser(" vasya")
        val user5 = user1.copy(id="5", lastName = "Altman", lastVisit = Date())
        print("$user1 \n$user5")
    }

    @Test
    fun test_decomposition(){
        val user = User.makeUser("Natasha Koroleva")
        fun getUserInfo() = user
        val(id, firstName, lastName) = getUserInfo()
        println("${user.component1()}, ${user.component2()}, ${user.component3()}")
    }

    @Test
    fun test_copy(){
        val user = User.makeUser("Надежда Кузнецова")
        var user2 = user.copy(lastVisit = Date())
        var user3 = user.copy(lastName = "Cena", lastVisit = Date().add(-10, TimeUnits.SECOND))
        var user4 = user.copy(lastName = "Cena", lastVisit = Date().add(2, TimeUnits.HOUR))

        println("""
            ${user .lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format()}
        """.trimIndent())

//        if(user == user2){
//            println("equals data and hash \n$(user.hashCode() $user \n$(user2.hashCode()} $user2")
//        }
//        else{
//            println("not equals data and hash \n$(user.hashCode() $user \n$(user2.hashCode()} $user2")
//        }
//
//        user2 = user
//        if(user === user2){
//            println("equals address ${System.identityHashCode(user)} ${System.identityHashCode(user2)}")
//        }
//        else{
//            println("not equals address ${System.identityHashCode(user)} ${System.identityHashCode(user2)}")
//        }
    }
    @Test
    fun test_lastVisit(){
        val user = User.makeUser("Надежда Кузнецова")
        val user2 = user.copy("2", lastVisit = Date(119, 11, 8, 11, 50))

        println(user2.toUserView().status)
    }

    @Test
    fun test_transliteration(){
        val user = User.makeUser("небовглазах моревнутри")
        println(user.toUserView().fullName)
        println(user.toUserView().initials)
    }

    @Test
    fun test_data_maping(){
        val user = User.makeUser("Яна Остапенко")
        val newUser = user.copy(lastVisit = Date(120, 3, 8, 11, 50))
        println(newUser)
        val userView = newUser.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory(){
        val user = User.makeUser("Яна Остапенко")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "какой-то текст", type = "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "ссылка на какую-то картинку", type = "image")

        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())

//        when(imgMessage){
//            is TextMessage -> println("это текстовое сообщение")
//            is ImageMessage -> println("это картинка")
//        }
//        when(txtMessage){
//            is TextMessage -> println("это текстовое сообщение")
//            is ImageMessage -> println("это картинка")
//        }
//        when(imgMessage){
//            is BaseMessage -> println("это")
//            is TextMessage -> println("это текстовое сообщение")
//            is ImageMessage -> println("это картинка")
//        }
    }
}
