clear()
drawStage(ColorMaker.black)
val cb = canvasBounds

class Launcher {
    var pt1: Point = null
    var pt2: Point = null
    var inited = false
    var pic: Picture = null

    def init(x: Double, y: Double) {
        pt1 = Point(x, y)
        inited = true
    }

    def line(pt1: Point, pt2: Point) = {
        val x1 = pt1.x; val y1 = pt1.y; val x2 = pt2.x; val y2 = pt2.y
        val pic = Picture.line(x2 - x1, y2 - y1)
        pic.setPosition(x1, y1)
        pic
    }

    def drawUpdate(x: Double, y: Double) {
        if (pic != null) {
            pic.erase()
        }
        pt2 = Point(x, y)
        pic = line(pt1, pt2)
        draw(pic)
    }

    def release() = {
        pic.erase
        Vector2D(pt1.x - pt2.x, pt1.y - pt2.y) / 10
    }
}

class Ball {
    val pic1 = PicShape.image("/media/flappy-ball/ballwing1.png")
    val pic2 = PicShape.image("/media/flappy-ball/ballwing2.png")
    val pic = picBatch(
        pic1,
        pic2
    )
    pic.setPosition(cb.x + 50, cb.y + 50)
    var vel: Vector2D = null
    val gravity = Vector2D(0, -0.1)

    val launcher = new Launcher()

    pic.onMouseDrag { (x, y) =>
        if (!launcher.inited) {
            val b = pic.bounds
            launcher.init(b.x + b.width / 2, b.y + b.height / 2)
        }
        else {
            launcher.drawUpdate(x, y)
        }
    }

    pic.onMouseRelease { (x, y) =>
        vel = launcher.release()
    }

    def draw() {
        pic.draw()
    }

    def step() {
        if (vel != null) {
            vel = vel + gravity
            pic.translate(vel)
            if (pic.collidesWith(stageBorder)) {
                vel = bouncePicVectorOffStage(pic, vel)
            }
            pic.showNext(150)
        }
    }
}

val ball = new Ball()
ball.draw()

animate {
    ball.step()
}

activateCanvas()

