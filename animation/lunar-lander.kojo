cleari()
drawStage(ColorMaker.hsl(240, 0.20, 0.16))

class Lander {
    val body = fillColor(red) -> Picture.rectangle(40, 70)
    val thruster = fillColor(orange) -> Picture.rectangle(20, 35)
    thruster.setPosition(body.position.x + 10, body.position.y - 20)

    var gravity = Vector2D(0, -0.1)
    var velocity = Vector2D(0, 0)
    var thrust = Vector2D(0, 0)

    def draw() {
        body.draw()
        thruster.draw()
        thruster.invisible()
    }

    def step() {
        if (isKeyPressed(Kc.VK_UP)) {
            inThrust()
        }
        else {
            noThrust()
        }
        velocity += gravity
        velocity += thrust

        body.translate(velocity)
        thruster.setPosition(body.position.x + 10, body.position.y - 20)

        if (body.collidesWith(stageBorder)) {
            velocity = bouncePicVectorOffStage(body, velocity)
        }
    }

    def inThrust() {
        thrust = new Vector2D(0, 1)
        thruster.visible()
    }

    def noThrust() {
        thrust = new Vector2D(0, 0)
        thruster.invisible()
    }
}

class Moon {
    val pic = trans(370, -350) -> Picture {
        setPenColor(cm.lightBlue)
        setFillColor(cm.darkGray)
        left(45)
        left(90, 500)
    }

    def draw() {
        pic.draw()
    }

    def check(l: Lander) {
        if (l.body.collidesWith(pic)) {
            if (l.velocity.y.abs > 3) {
                drawCenteredMessage("You Lose", red, 39)
            }
            else {
                drawCenteredMessage("You Win", green, 30)
            }
            stopAnimation()
        }
    }

}

val l = new Lander()
l.draw()

val m = new Moon()
m.draw()

animate {
    l.step()
    m.check(l)
}
activateCanvas()