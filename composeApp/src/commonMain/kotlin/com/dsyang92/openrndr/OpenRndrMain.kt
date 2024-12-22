import com.dsyang92.raytracerchallenge.Point
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import com.dsyang92.raytracerchallenge.Tuple
import com.dsyang92.raytracerchallenge.Vector
import com.dsyang92.raytracerchallenge.norm
import com.dsyang92.raytracerchallenge.plus
import com.dsyang92.raytracerchallenge.point
import com.dsyang92.raytracerchallenge.vector
import com.dsyang92.raytracerchallenge.times
import org.openrndr.math.Vector2
import org.openrndr.shape.Circle
import org.openrndr.shape.LineSegment


data class Projectile(
    val position: Point,
    val velocity: Vector,
)

data class Environment(
    val gravity: Vector,
    val wind: Vector,
)

fun tick(p: Projectile, e: Environment): Projectile {
    val newPos = p.position + p.velocity
    val newV = p.velocity + e.gravity + e.wind
    return Projectile(newPos, newV)
}
const val WIDTH = 800
const val HEIGHT= 600
fun main() = application {
    configure {
        width = WIDTH
        height = HEIGHT
    }
    program {

        val scale = 15.0
        val origin = Tuple.p(WIDTH/2.0, HEIGHT/2.0, 0.0)

        val projectile: MutableList<Projectile> = mutableListOf()
        val environment: Environment = Environment(
            gravity = Tuple.v(0.0, -0.1, 0.0) * scale,
            wind = Tuple.v(-0.0, 0.0, 0.0) * scale
        )

        keyboard.keyUp.listen {
            if (it.name == "t") {
                val p = projectile.lastOrNull()
                p?.let {
                    projectile.add(tick(it, environment))
                }
                println("Tick on $p")
                println("Next p = ${projectile.lastOrNull()}")
            } else if (it.name == "r") {
                projectile.clear()
                projectile.add(Projectile(
                    point(0, 1, 0),
                    vector(1, 1, 0) * scale
                ))

                println("Projectile created: $projectile")
            }
        }

        mouse.buttonUp.listen {
            projectile.clear()
            projectile.add(Projectile(
                point(0, 1, 0),
                vector(1, 1, 0) * scale
            ))
            println("Projectile created: $projectile")
        }

        extend {
            drawer.clear(ColorRGBa.PINK)

            //draw axes
            drawer.fill = ColorRGBa.BLACK
            drawer.lineSegments(listOf(
                LineSegment(WIDTH/2.0, 0.0, WIDTH/2.0, HEIGHT.toDouble()),
                LineSegment(0.0, HEIGHT/2.0, WIDTH.toDouble(), HEIGHT/2.0)
            ))
            drawer.circle(Circle(origin.x, origin.y, 3.0))
            drawer.fill = null

            // draw cannon
            projectile.forEachIndexed { i, p ->
                if (i < projectile.size - 1) {
                    drawer.fill = ColorRGBa.GRAY
                } else {
                    drawer.fill = ColorRGBa.CYAN
                }
                drawer.circle(Circle(translate(p, origin), 4.0))
            }

        }
    }
}

private fun translate(p: Projectile, origin: Point): Vector2 {
    return p.position.let { pos ->
        val newX = pos.x + origin.x
        val newY = (-pos.y) + origin.y
        Vector2(newX, newY)
    }
}

