package editor

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import ro.dragossusi.editor.IntColor

fun IntColor.toColor() = Color(value)

fun IntColor.toSpanStyle() = SpanStyle(toColor())