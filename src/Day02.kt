fun main() {

    fun part1(input: List<String>): Int {
        var gameSum = 0
        var gameNumber = 1
        input.forEach { line ->
            val games: String? = if (line.indexOf(":") == -1) null else line.substring(line.indexOf(":") + 1)
            var isValidGame = true
            val gameList = games?.split(";")
            gameList!!.forEach { it ->
                val gamePicks = it.split(",")
                gamePicks.forEach{ gem ->
                    val gems = gem.split(" ")
                    val gemNumber = gems[1].toInt()
                    val gemColor = gems[2]

                    if(gemColor.equals("red") && (gemNumber > 12)) {
                        isValidGame = false
                    }
                    if(gemColor.equals("green") && (gemNumber > 13)) {
                        isValidGame = false
                    }
                    if (gemColor.equals("blue") && (gemNumber > 14)) {
                        isValidGame = false
                    }
                }
            }
            if(isValidGame) gameSum += gameNumber
            ++gameNumber
        }
        return gameSum
    }

    fun part2(input: List<String>): Int {
        var result = 0

        input.forEach { line ->
            val games: String? = if (line.indexOf(":") == -1) null else line.substring(line.indexOf(":") + 1)
            var red = 0
            var green = 0
            var blue = 0
            val gameList = games?.split(";")
            gameList!!.forEach { it ->
                val gamePicks = it.split(",")
                gamePicks.forEach{ gem ->
                    val gems = gem.split(" ")
                    val gemNumber = gems[1].toInt()
                    val gemColor = gems[2]

                    if(gemColor.equals("red") && (gemNumber > red)) {
                        red = gemNumber
                    }
                    if(gemColor.equals("green") && (gemNumber > green)) {
                        green = gemNumber
                    }
                    if (gemColor.equals("blue") && (gemNumber > blue)) {
                        blue = gemNumber
                    }
                }
            }
            result += (red * green * blue)
        }
        return result
    }

    val testInput = readInput("test_input")
    testInput.forEach { line ->

    }

    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
