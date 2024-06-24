fun main() {

    data class Point(val x: Int, val y: Int)
    data class Numbers(val value: Int, val adjacentPoints: Set<Point>, val row: Int)
    data class Symbols(val symbol: Char, val point: Int, val row: Int)


    fun part1(input: List<String>): Int {
        var result = 0
        var numbers = mutableListOf<Int>()
        input.forEach { line ->
//            println(line)
            Regex("[0-9]+").findAll(line)
                .map(MatchResult::value)
                .toList()
                .map {
                    it.toInt()
                    numbers.add(it.toInt() )
                    println(it.length)
                    println(line.indexOf(it))
//                    if(input.indexOf(line)-1 > 0)
//                    if(input.indexOf(line)+1 < input.size)
//                        println(input[input.indexOf(line)+1])
                }
            Regex("""[\p{P}\p{S}&&[^.]]+""").findAll(line)
                .map(MatchResult::value)
                .toList()
                .map {
                    println(it)
                }
        }
        println(numbers)
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        input.forEach { line ->
        }
        return result
    }

    val testInput = readInput("test_input")
    testInput.forEach { line ->

    }

    val input = readInput("test_input")
    part1(input).println()
    part2(input).println()
}
