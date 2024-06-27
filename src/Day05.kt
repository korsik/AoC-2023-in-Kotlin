import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {

    data class GardenerInfo(
        val seeds: Long, var location: Long, var changed: Boolean, val range: Long = 0
    )

    data class RangeMap(val srcFrom: Long, val dstFrom: Long, val len: Long) {
        operator fun contains(value: Long) = value in srcFrom..<(srcFrom + len)

        fun map(value: Long): Long = value - srcFrom + dstFrom
    }

    data class Category(var name: String, var ranges: List<RangeMap>)

    fun parseRanges(input: List<String>): List<Category> {
        val categories = input.subList(2, input.size).fold(mutableListOf(mutableListOf<String>())) { list, line ->
            if (line.isBlank() || !line.contains(Regex("\\d+"))) {
                list.add(mutableListOf(line.split(" ").first()))
            } else {
                list.last().add(line)
            }
            list
        }.filter { it.isNotEmpty() }.map { line ->
            val category = Category("", mutableListOf())
            val lines = line.map { numbers ->
                lateinit var range: RangeMap
                if (!numbers.contains(Regex("\\d+"))) {
                    category.name = numbers
                } else {
                    val (dst, src, len) = numbers.split(" ").map { it.trim().toLong() }
                    range = RangeMap(src, dst, len)
                    range
                }
            }.filter { it is RangeMap }
            category.ranges = lines as MutableList<RangeMap>
            category
        }.filter { it.ranges.isNotEmpty() }

        return categories
    }

    class Almanac(val seeds: List<Pair<Long, Long>>) {
        var categories = mutableListOf<Category>()

        fun seedToLocation(seed: Long): Long {
            var translated = seed
            for (cat in categories) {
                translated = cat.ranges.firstOrNull { translated in it }?.map(translated) ?: translated
            }
            return translated
        }
    }

    fun findSeedLocations(gardenerInfo: List<GardenerInfo>, input: List<String>): List<GardenerInfo> {
        input.subList(2, input.size).forEach { line ->

            if (line.isNotBlank() && !line.contains(Regex("\\d+"))) {
                gardenerInfo.forEach {
                    it.changed = false
                }
            }

            if (line.isNotBlank() && line.contains(Regex("\\d+"))) {
                val coords = line.split(Regex("\\D+")).filter { it.isNotBlank() }.map { it.toLong() }
                gardenerInfo.forEach { info ->
                    if (info.location in coords[1]..(coords[1] + coords[2]) && !info.changed) {
                        info.location += (coords[0] - coords[1])
                        info.changed = true
                    }
                }

            }
        }
        return gardenerInfo
    }

    fun part1(input: List<String>): Int {
        val result: Int

        val gardenerInfo = mutableListOf<GardenerInfo>()
        input[0].split(Regex("\\D+")).filter { it.isNotBlank() }.map {
            it.toBigInteger()
            gardenerInfo.add(GardenerInfo(it.toLong(), it.toLong(), false))
        }


        result = findSeedLocations(gardenerInfo, input).minByOrNull { it.location }?.location?.toInt() ?: 0
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        val seedPairs = input[0].split(Regex("\\D+")).filter { it.isNotBlank() }.map {
            it.toLong()
        }
            .chunked(2)
            .map { (start, len) ->
                Pair(start, len)
            }

        val almanacData = Almanac(seedPairs)
        almanacData.categories = parseRanges(input) as MutableList<Category>

        println(almanacData.categories)
        Day05().parseAlmanac(input).categories.println()

        val min2 = runBlocking(Dispatchers.Default) {
            almanacData.seeds
                .map { (start, len) ->
                    async {
                        (start..<(start + len)).minOf { almanacData.seedToLocation(it) }
//                            .also { println("  Min of seeds $start [len: $len]: $it") }
                    }
                }
                .minOf { it.await() }
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