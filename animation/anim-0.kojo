clear()
drawStage(ColorMaker.black)
val cb = canvasBounds

val pic = Picture {
    setFillColor(red)
    repeat(4) {
        forward(40)
        right(90)
    }
}
pic.setPosition(cb.x + 20, cb.y + 20)
var vel = Vector2D(2, 10)

draw(pic)

animate {
    pic.translate(vel)
    if (pic.collidesWith(stageBorder)) {
        vel = bouncePicVectorOffStage(pic, vel)
    }
}

