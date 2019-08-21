clear()
setSpeed(fast)
setPenColor(yellow)
def shape() {
    val p = position
    forward(100)
    right(36)
    repeat(9) {
        forward(100)
        var p1 = position
        var h = heading
        moveTo(p)
        setPosition(p1)
        setHeading(h)
        right(36)
    }
}
def block() {
    shape()
    forward(100)
    right(36)
}

repeat(10) {
    block()
}

