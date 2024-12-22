import com.dsyang92.openrndr.chapter1
import org.openrndr.application

const val WIDTH = 800
const val HEIGHT= 600
fun main() = application {
    configure {
        width = WIDTH
        height = HEIGHT
    }
    program {
        chapter1(program)
    }
}

