package com.mindovercnc.model

import kotlin.time.Instant
import ro.dragossusi.proto.linuxcnc.status.SystemMessage

@OptIn(kotlin.time.ExperimentalTime::class)
data class MessageBundle(
  val emcMessages: List<SystemMessage>,
  val uiMessages: Map<CncStateMessage, Instant>
)
