package com.mindovercnc.repository

import ro.dragossusi.model.CncStateMessage
import kotlinx.coroutines.flow.StateFlow
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/** Repository for messages. */
interface CncMessagesRepository {
    suspend fun pushMessage(uiMessage: CncStateMessage)

    suspend fun popMessage(uiMessage: CncStateMessage)

    @OptIn(ExperimentalTime::class)
    val messagesFlow: StateFlow<Map<CncStateMessage, Instant>>
}

suspend fun CncMessagesRepository.handleMessage(
    isNeeded: Boolean,
    uiMessage: CncStateMessage,
) {
    if (isNeeded) {
        pushMessage(uiMessage)
    } else {
        popMessage(uiMessage)
    }
}
