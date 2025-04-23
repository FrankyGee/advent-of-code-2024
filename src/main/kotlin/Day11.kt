import kotlin.system.exitProcess

class Day11 {

    fun applyRules(number: Long): List<Long> {
        // 1. If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
        if (number == 0L) {
            return listOf(1L)
        }

        // 2. If the stone is engraved with a number that has an even number of digits, it is replaced by two stones.
        //    The left half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new right stone.
        //    (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
        if (number.toString().length % 2 == 0) {
            number.toString().let {
                try {
                    val mid = it.length / 2
                    val left = it.substring(0, mid).toLong()
                    val right = it.substring(mid).toLong()
                    return listOf(left, right)
                } catch (e: NumberFormatException) {
                    // Handle the case where the number is too large to be parsed
                    // or any other parsing error
                    println("Error parsing number: $number, error: ${e.message}")
                    exitProcess(1)
                }
            }
        }

        // 3. If none of the other rules apply, the stone is replaced by a new stone;
        //    the old stone's number multiplied by 2024 is engraved on the new stone.
        return listOf(number * 2024)
    }

    fun part1(input: List<String>): Int {
        return parseInput(input).sumOf { processDepthFirst(it, 75) }
//        var working = parseInput(input)
//
//        repeat(25) {
//            working = working.flatMap { applyRules(it) }
//        }
//        return working.size
    }

    private fun parseInput(input: List<String>): List<Long> = input
        .joinToString(separator = " ")
        .split(" ")
        .map { it.toLong() }


    fun processDepthFirst(item: Long, maxDepth: Int, currentDepth: Int = 0): Int {
        // Base case: stop recursion if max depth reached
        if (currentDepth >= maxDepth) return 1

        // Get results from processing the current item and process them
        return applyRules(item)
            .sumOf { result ->
                processDepthFirst(result, maxDepth, currentDepth + 1)
            }
    }


    fun part2(input: List<String>): Int {
        return parseInput(input).sumOf { processDepthFirst(it, 75) }
    }
}
