import com.dsyang92.raytracerchallenge.Point
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import com.dsyang92.raytracerchallenge.Tuple
import com.dsyang92.raytracerchallenge.Vector
import com.dsyang92.raytracerchallenge.norm
import com.dsyang92.raytracerchallenge.plus
import com.dsyang92.raytracerchallenge.point
import com.dsyang92.raytracerchallenge.vector


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

fun main() = application {
    configure {
        width = 800
        height = 600
    }
    program {

        var projectile: Projectile? = null
        val environment: Environment = Environment(
            gravity = Tuple.v(0.0, -0.1, 0.0),
            wind = Tuple.v(-0.01, 0.0, 0.0)
        )

        keyboard.keyUp.listen {
            if (it.name == "t") {
                val p = projectile
                p?.let {
                    projectile = tick(it, environment)
                }
                println("Tick on $p")
            }
            println("Projectile: $projectile")
        }

        mouse.buttonUp.listen {
            projectile = Projectile(
                point(0, 1, 0),
                vector(1, 1, 0).norm()
            )
            println("Projectile created: $projectile")
        }

        extend {
            drawer.clear(ColorRGBa.PINK)
        }
    }
}
