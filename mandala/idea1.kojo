cleari()
setSpeed(superFast)
setPenColor(cm.yellow)

def tenSided() {
    repeat(10) {
        forward(100)
        right(36)
    }
}

def hopAndLineToStart(n: Int) {
    savePosHe()
    val pos = position
    repeat(n) {
        hop(100)
        right(36)
    }
    moveTo(pos)
    restorePosHe()

}

def linesHome() {
    repeatFor(2 to 8) { n =>
        hopAndLineToStart(n)
    }
    hop(100)
    right(36)
}

tenSided()
repeat(8) {
    linesHome()
}

