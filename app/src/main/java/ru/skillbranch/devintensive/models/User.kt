package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String?,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?,
    val rating: Int = 0,
    val respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    init {
        println(
            "It's Alive!!!\n" +
                    "${if (lastName === "Doe") "His name is $firstName $lastName" 
                    else "And his name is $firstName $lastName!!!"}\n"
        )
    }

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    class Builder {
        var id: String? = null
            private set
        var firstName: String? = null
            private set
        var lastName: String? = null
            private set
        var avatar: String? = null
            private set
        var rating: Int = 0
            private set
        var respect: Int = 0
            private set
        var lastVisit: Date? = null
            private set
        var isOnline: Boolean = false
            private set

        fun id (value: String) = apply { this.id = value }
        fun firstName (value: String) = apply { this.firstName = value }
        fun lastName (value: String) = apply { this.lastName = value }
        fun avatar (value: String) = apply { this.avatar = value }
        fun rating (value: Int) = apply { this.rating = value }
        fun respect (value: Int) = apply { this.respect = value }
        fun lastVisit (value: Date) = apply { this.lastVisit = value }
        fun isOnline (value: Boolean) = apply { this.isOnline = value }
        fun build () = User(this.id, this.firstName, this.lastName, this.avatar, this.rating, this.respect, this.lastVisit, this.isOnline)
    }
}