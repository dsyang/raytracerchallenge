package com.dsyang92.openrndr

import com.dsyang92.raytracerchallenge.Point
import com.dsyang92.raytracerchallenge.Tuple
import com.dsyang92.raytracerchallenge.Vector
import com.dsyang92.raytracerchallenge.plus
import com.dsyang92.raytracerchallenge.point
import com.dsyang92.raytracerchallenge.times
import com.dsyang92.raytracerchallenge.vector
import org.openrndr.Program
import org.openrndr.color.ColorRGBa
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

fun chapter1(program: Program) = program.run {

    val scale = 15.0
    val origin = Tuple.p(width/2.0, height/2.0, 0.0)

    val projectile: MutableList<Projectile> = mutableListOf()
    val environment: Environment = Environment(
        gravity = Tuple.v(0.0, -0.1, 0.0) * scale,
        wind = Tuple.v(-0.0, 0.0, 0.0) * scale
    )

    fun translateProjectile(p: Projectile, origin: Point): Vector2 {
        return p.position.let { pos ->
            val newX = pos.x + origin.x
            val newY = (-pos.y) + origin.y
            Vector2(newX, newY)
        }
    }
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
            LineSegment(width/2.0, 0.0, width/2.0, height.toDouble()),
            LineSegment(0.0, height/2.0, width.toDouble(), height/2.0)
        ))
        drawer.circle(Circle(origin.x, origin.y, 3.0))
        drawer.fill = null

        // draw cannon
        val cannonLocation = Tuple.p(width * 0.25, height * 0.5 - 50, 0.0)
        projectile.forEachIndexed { i, p ->
            if (i < projectile.size - 1) {
                drawer.fill = ColorRGBa.GRAY
            } else {
                drawer.fill = ColorRGBa.CYAN
            }
            drawer.circle(Circle(translateProjectile(p, cannonLocation), 4.0))
        }

    }
}