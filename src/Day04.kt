import kotlin.math.pow

fun main() {

    fun findWinningCards(line: String): List<Int> {
        val winningNumbers = line.substring(line.indexOf(":")+1, line.indexOf("|"))
            .split(Regex("\\D+"))
            .filter { it.isNotBlank() }
            .map { it.toInt() }

        val cardNumbers =  line.substring(line.indexOf("|")+1)
            .split(Regex("\\D+"))
            .filter { it.isNotBlank() }
            .map { it.toInt() }

        return cardNumbers.filter { winningNumbers.contains(it) }
    }

    fun part1(input: List<String>): Int {
       var result = 0

        input.forEach { line ->

            val final = findWinningCards(line)

            result +=  2.0.pow(final.size - 1).toInt()
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        var cardCopies = mutableMapOf<Int, Int>()

        for (line in 1..input.size) {
            cardCopies.put(line, 0)
        }
        input.forEach { line ->

            val cardNumber = line.substring(0, line.indexOf(":"))
                .replace(Regex("\\D+"), "")

            cardCopies[cardNumber.toInt()] = cardCopies[cardNumber.toInt()]!! + 1
            val final = findWinningCards(line)
            for(i in 1..final.size) {
                if(cardCopies[cardNumber.toInt()] != 1) {
                    cardCopies[cardNumber.toInt()+i] =
                        cardCopies[cardNumber.toInt()+i]!! + cardCopies[cardNumber.toInt()]!!
                }
                else {
                    cardCopies[cardNumber.toInt()+i] = cardCopies[cardNumber.toInt()+i]!! + 1
                }
            }
        }

        cardCopies.forEach { cardNumber ->
            result += cardNumber.value
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