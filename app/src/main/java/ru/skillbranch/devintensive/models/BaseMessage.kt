/*******************************************************************************
 * Created by Yana Ostapenko, 2020
 ******************************************************************************/

package ru.skillbranch.devintensive.models

import android.service.voice.AlwaysOnHotwordDetector
import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object AbstractFactory{
        var lastID = -1
        fun makeMessage(from:User?, chat:Chat, date: Date = Date(), type: String = "test", payload: Any?): BaseMessage{
            lastID++
            return when(type) {
                "image" -> ImageMessage("$lastID", from, chat, date= date, image = payload as String)
                    else -> TextMessage("$lastID", from, chat, date= date, text = payload as String)
            }

        }
    }
}