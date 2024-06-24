fun main() {

    data class Numbers(var value: Int, val location: List<Int>, val row: Int)
    data class Symbols(val value: String, val point: Int, val row: Int)

    val numbers = mutableListOf<Numbers>()
    val symbols = mutableListOf<Symbols>()

    fun traverseInput(input: List<String>) {
        input.forEach { line ->
            val row = input.indexOf(line)
            Regex("[0-9]+").findAll(line)
                .forEach { matchResult ->
                    val matchValue = matchResult.value
                    val matchIndex = matchResult.range.first
                    numbers.add(
                        Numbers(matchValue.toInt(),
                            ((matchIndex - 1)..(matchIndex + matchValue.length)).toList(),
                            row
                        )
                    )
                }
            Regex("""[\p{P}\p{S}&&[^.]]+""").findAll(line)
                .forEach { matchResult ->
                    val matchValue = matchResult.value
                    val matchIndex = matchResult.range.first
                    symbols.add(
                        Symbols(matchValue, matchIndex, row)
                    )
                }
        }
    }

    fun part1(input: List<String>): Int {
        var result = 0
        traverseInput(input)

        numbers.forEach { number ->
            val sym = symbols.filter { symbol -> symbol.point in number.location &&
                    symbol.row in (number.row - 1)..(number.row + 1)
            }
            if(sym.isNotEmpty()) {
                result += number.value
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        traverseInput(input)

        symbols.filter { it.value == "*" }
            .toSet()
            .forEach {
            symbol ->
            val nums = numbers.filter { number -> symbol.point in number.location &&
                    symbol.row in (number.row - 1)..(number.row + 1)
            }.toSet()
            if (nums.size == 2) {

                result += nums.elementAt(0).value * nums.elementAt(1).value
            }

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
