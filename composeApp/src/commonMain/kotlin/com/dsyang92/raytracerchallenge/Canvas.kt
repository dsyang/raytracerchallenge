package com.dsyang92.raytracerchallenge

// X grows left to right, Y grows top to bottom
class Canvas(
    val width: Int, val height: Int, init: (Int) -> Color = { _ ->
        color(0.0, 0.0, 0.0)
    }
) {

    private val pixels = MutableList<Color>(width * height, init)

    operator fun get(x: Int, y: Int): Color {
        return pixels[y * width + x]
    }

    operator fun set(x: Int, y: Int, c: Color) {
        pixels[y * width + x] = c
    }

    fun toPPM(): String {
        val maxValue = 255
        val ppmMaxLineLength = 70
        val header = listOf(
            "P3",
            "$width $height",
            "$maxValue"
        )
        val data = ppmPixelData(maxValue, ppmMaxLineLength)
        return (header + data).joinToString("\n")
    }

    internal fun ppmPixelData(maxValue: Int, ppmMaxLineLength: Int? = null): List<String> {
        val pixels2D = pixels.chunked(width)
        return pixels2D.flatMap { row ->
            val colors = row.map { px ->
                px.toPPM(maxValue)
            }

            val colorString = colors.joinToString(" ")
            if (ppmMaxLineLength == null) {
                listOf(colorString)
            } else {
                val colorComponents = colorString.split(" ")
                val lines = mutableListOf<String>()
                val sb = StringBuilder()
                colorComponents.forEach { c ->
                    if (sb.length + 1 + c.length > ppmMaxLineLength) {
                        lines.add(sb.toString().trim())
                        sb.clear()
                        sb.append("$c ")
                    } else {
                        sb.append("$c ")
                    }
                }
                lines.add(sb.toString().trim())
                lines
            }
        }
    }
}