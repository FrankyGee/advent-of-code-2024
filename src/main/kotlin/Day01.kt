

fun main() {
    val day = "01"
    val part1ExpectedTestResult = 11
    val part2ExpectedTestResult = 0
    val part1Answer = 1660292
    val part2Answer = 22776016

//    val isTest = "-t" in args

    fun part1(input: List<String>): Int {
        val (left, right) = splitIntoColumns(input)

        left.sort()
        right.sort()

        var sum = 0
        for (i in 0..<left.size) {
            sum += kotlin.math.abs(left[i] - right[i])
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        val (left, right) = splitIntoColumns(input)

        var sum = 0
        for (item in left) {
            sum += item * right.count { i -> i == item }
        }

        return sum
    }

    // Read a test input from the `src/main/resources/DayXX_test.txt` file:
    val testInput = readInput("main/resources/Day${day}_test")  // Test input
    check(part1(testInput) == part1ExpectedTestResult)

    // Read the input from the `src/main/resources/DayXX.txt` file.
    val input = readInput("main/resources/Day${day}")  // Actual input
    val part1Actual = part1(input)
    val part2Actual = part2(input)
    part1Actual.println()
    part2Actual.println()
    check(part1Actual == part1Answer)
    check(part2Actual == part2Answer)
}
