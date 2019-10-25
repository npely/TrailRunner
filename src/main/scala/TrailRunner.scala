object TrailRunner {
    val game = "TrailRunner"
    val version = 1.0
    def setPlayerName(playername:String): Player = {
        Player.apply(playername)
    }
}
