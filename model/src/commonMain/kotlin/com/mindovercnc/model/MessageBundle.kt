package com.mindovercnc.model

import kotlin.time.Instant
import ro.dragossusi.proto.linuxcnc.status.SystemMessage

data class MessageBundle(
  val emcMessages: List<SystemMessage>,
  val uiMessages: Map<CncStateMessage, Instant>
)
