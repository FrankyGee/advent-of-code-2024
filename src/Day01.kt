import java.util.regex.Pattern

const val DAY = "01"
const val TEST_EXPECTED = 11

fun main(args: Array<String>) {
//    val isTest = "-t" in args

    fun split(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

        for (line in input) {
            val (leftItem, rightItem) = line.split("   ")
            left.add(leftItem.toInt())
            right.add(rightItem.toInt())
        }
        return Pair(left, right)
    }

    fun part1(input: List<String>): Int {
        val (left, right) = split(input)

        left.sort()
        right.sort()

        var sum = 0
        for (i in 0..<left.size) {
            sum += kotlin.math.abs(left[i] - right[i])
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        val (left, right) = split(input)

        var sum = 0
        for (item in left) {
            sum += item * right.count { i -> i == item }
        }

        return sum
    }

    // Read a test input from the `src/DayXX_test.txt` file:
    val testInput = readInput("Day${DAY}_test")  // Test input
    check(part1(testInput) == TEST_EXPECTED)

    // Read the input from the `src/DayXX.txt` file.
    val input = readInput("Day${DAY}")  // Actual input
    part1(input).println()
    part2(input).println()
}
