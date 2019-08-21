clear()
drawStage(ColorMaker.black)
val cb = canvasBounds

class Ball {
    val pic = Picture.rectangle(40, 40)
    pic.setFillColor(red)
    pic.setPosition(cb.x + 20, cb.y + 20)
    var vel = Vector2D(2, 10)
    val gravity = Vector2D(0, -0.1)

    def draw() {
        pic.draw()
    }

    def step() {
        vel = vel + gravity
        pic.translate(vel)
        if (pic.collidesWith(stageBorder)) {
            vel = bouncePicVectorOffStage(pic, vel)
        }
    }
}

val ball = new Ball()
ball.draw()

animate {
    ball.step()
}

activateCanvas()
