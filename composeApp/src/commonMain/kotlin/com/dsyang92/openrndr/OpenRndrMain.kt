import com.dsyang92.openrndr.chapter1
import com.dsyang92.openrndr.chapter1Config
import com.dsyang92.openrndr.chapter2
import com.dsyang92.openrndr.chapter2Config
import org.openrndr.application

const val WIDTH = 800
const val HEIGHT= 600
private const val CHAPTER = 1
fun main() = application {
    configure {
        when(CHAPTER) {
            1 -> chapter1Config(this)
            2 -> chapter2Config(this)
            else -> {
                width = WIDTH
                height = HEIGHT
            }
        }
    }
    program {
        when(CHAPTER) {
            1 -> chapter1(this)
            2 -> chapter2(this)
        }
    }
}

