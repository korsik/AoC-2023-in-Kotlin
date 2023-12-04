fun main() {
    val candidates = listOf(
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "zero"
    )

    val stringNumbers = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
        "zero" to 0
    )

    fun convertToNumber(entry: String): Int {
//        entry.println()
        return if (entry.length == 1) {
            entry.toInt()
        } else {
            stringNumbers[entry] ?: -1
        }
    }

    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach { line ->
            val first = line.first { it.isDigit() }
            val last = line.last { it.isDigit() }
            val num = "$first$last"

            result += Integer.parseInt(num)

        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        input.forEach { line ->
            var firstIndex = line.length
            var lastIndex = 0
            var firstEntry = ""
            var lastEntry = ""
            val er = candidates.filter { line.contains(it) }

            er.forEach {
                val index = line.indexOf(it)
                val lIndex = line.lastIndexOf(it)

                if (index <= firstIndex) {
                    firstIndex = index
                    firstEntry = it
                }
                if (lIndex >= lastIndex) {
                    lastIndex = lIndex
                    lastEntry = it
                }
            }
            result += convertToNumber(firstEntry) * 10 + convertToNumber(lastEntry)
        }
        return result
    }

//    val testInput = readInput("test_input")


    val input = readInput("input")
    part1(input).println()
    part2(input).println()
}
