package ro.dragossusi.model

import okio.Path

val Path.extension: String
  get() = segments.last().substringAfterLast('.', "")
