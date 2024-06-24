fun main() {

    data class Numbers(var value: Int, val location: List<Int>, val row: Int)
    data class Symbols(val symbol: String, val point: Int, val row: Int)


    fun part1(input: List<String>): Int {
        var result = 0
        val numbers = mutableListOf<Numbers>()
        val symbols = mutableListOf<Symbols>()
        input.forEach { line ->
            val row = input.indexOf(line)
            Regex("[0-9]+").findAll(line)
                .map(MatchResult::value)
                .toList()
                .map {
                    numbers.add(
                        Numbers(it.toInt(),
                            ((line.indexOf(it) - 1)..(line.indexOf(it) + it.length)).toList(),
                            row
                            )
                    )
                }
            Regex("""[\p{P}\p{S}&&[^.]]+""").findAll(line)
                .map(MatchResult::value)
                .toList()
                .map {
                    symbols.add(
                        Symbols(it, line.indexOf(it), row)
                    )
                }
        }
        println(numbers.size)
        // Check for adjacent
        var num = mutableListOf<Int>()
        var sum = 0
//        symbols.sortBy { it.row }
        symbols.forEach { symbol ->
            var check = false
            numbers.forEachIndexed { index, number ->
                println("The Line location Symbol ${symbol.point} -> Number ${number.location}")
                println("The row position Symbol ${symbol.row} -> Number ${number.row}")
                if(symbol.row == 2) {
                    println("Yes")
                }
                    if ((symbol.point in number.location) and
                        (symbol.row in (number.row - 1)..(number.row + 1))
                    ) {
                        sum += 1
                        num.add(index)
//                        println(number.value)
                        result += number.value
//                        number.value = 0
                        check = true
//                        return@symb
                    }
            }
            if(check) {
//                println("Checking ${num.size} numbers")
                num.forEach {
                    numbers.removeAt(it)
                }
                num.clear()
            }
        }

        println("The numbers list size is ${numbers.size}")
        println(sum)
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

    // 1197 numbers and 730 symbols

    // 143 numbers should be left out

    //548033, 291365,

    // 531932 with 1054

//    233823
//    392612

    val input = readInput("input")

    part1(input).println()
//    Day03(input).solvePart1()
    part2(input).println()
}
