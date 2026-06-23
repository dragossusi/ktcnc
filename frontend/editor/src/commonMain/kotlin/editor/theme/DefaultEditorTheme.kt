package editor.theme

import ro.dragossusi.editor.EditorTheme
import ro.dragossusi.editor.EditorThemeVariant
import ro.dragossusi.editor.IntColor
import ro.dragossusi.editor.LineNumberTheme


object DefaultEditorTheme {
    private val light =
        EditorThemeVariant(
            comment = IntColor(0xFF808080),
            variable = IntColor(0xFF696969),
            value = IntColor(0xFF6897BB),
            text = IntColor(0xFFA9B7C6),
            keyword = IntColor(0xFFCC7832),
            punctuation = IntColor(0xFFA1C17E),
            background = IntColor(0xFFFFFFFF),
            lineNumber =
            LineNumberTheme(
                text = IntColor(0xFFCECECE),
                background = IntColor(0xFFFFFFFF),
                divider = IntColor(0xFFFFFFFF)
            ),
            gcode = IntColor(0XFFAAAAFF)
        )
    private val dark =
        EditorThemeVariant(
            comment = IntColor(0xFF808080),
            variable = IntColor(0xFFCCCCCC),
            value = IntColor(0xFF6897BB),
            text = IntColor(0xFFA9B7C6),
            keyword = IntColor(0xFFCC7832),
            punctuation = IntColor(0xFFA1C17E),
            background = IntColor(0xFF2B2B2B),
            lineNumber =
            LineNumberTheme(
                text = IntColor(0xFFCECECE),
                background = IntColor(0xFF2B2B2B),
                divider = IntColor(0xFF2B2B2B),
            ),
            gcode = IntColor(0XFF6666FF)
        )

    val theme = EditorTheme(light, dark)
}