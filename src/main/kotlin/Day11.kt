class Day11 {

    fun parseInput(input: List<String>): List<Long> = input
        .joinToString(separator = " ")
        .split(" ")
        .map { it.toLong() }

    fun applyRules(working: CountingList): CountingList {
        return CountingList().apply {
            for ((stone, count) in working.map) {
                val numStr = stone.toString()
                if (stone == 0L) {
                    // 1. If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
                    add(1L, count)
                } else if (numStr.length % 2 == 0) {
                    // 2. If the stone is engraved with a number that has an even number of digits, it is replaced by two stones.
                    //    The left half of the digits are engraved on the new left stone, and the right half of the digits are engraved on the new right stone.
                    //    (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
                    try {
                        val mid = numStr.length / 2
                        val left = numStr.substring(0, mid).toLong()
                        val right = numStr.substring(mid).toLong()
                        add(left, count)
                        add(right, count)
                    } catch (e: NumberFormatException) {
                        println("Error parsing number: $stone, error: ${e.message}")
                        throw (e)
                    }
                } else {
                    // 3. If none of the other rules apply, the stone is replaced by a new stone;
                    //    the old stone's number multiplied by 2024 is engraved on the new stone.
                    add(stone * 2024L, count)
                }
            }
        }
    }

    fun part1(input: List<String>): Long {
        val counts = parseInput(input).groupingBy { it }.eachCount().mapValues { (_, value) -> value.toLong() }
        var working = CountingList(counts.toMutableMap())

        repeat(25) {
            working = applyRules(working)
        }
        return working.map.values.sum()
    }

    class CountingList(val map: MutableMap<Long, Long> = mutableMapOf()) {
        fun add(number: Long, count: Long) {
            map[number] = map.getOrElse(number) { 0 } + count
        }
    }

    fun part2(input: List<String>): Long {
        val counts = parseInput(input).groupingBy { it }.eachCount().mapValues { (_, value) -> value.toLong() }
        var working = CountingList(counts.toMutableMap())

        repeat(75) {
            working = applyRules(working)
        }

        return working.map.values.sumOf { it } }
}
