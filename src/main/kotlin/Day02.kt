typealias Report = List<Int>

fun Report.isSafe(): Boolean {
    val sorted = this.toMutableSet<Int>().sorted()

    // Report must be strictly ascending or descending
    if (sorted != this && sorted.asReversed() != this) return false

    // Check that every two adjacent numbers are the correct distance apart
    return this.windowed(2, 1).all { (left, right) -> kotlin.math.abs(left - right) in 1..3 }
}

fun Report.isSafeWhenDamp(): Boolean {
    var result = false
    (0..this.size).forEach { i ->
        result = result || this.filterIndexed { index, _ -> index != i }.isSafe()
    }
    return result
}

fun main() {
    val day = "02"
    val part1ExpectedTestResult = 2
    val part2ExpectedTestResult = 4
    val part1Answer = 516
    val part2Answer = 561

//    val isTest = "-t" in args


    fun part1(input: List<String>): Int {
        var countOfSafeReports = 0
        for (row in input) {
            if (row.split(whitespaceRegex).map { it.toInt() }.isSafe()) {
                countOfSafeReports++
            }
        }

        return countOfSafeReports
    }

    fun part2(input: List<String>): Int {
        var countOfSafeReports = 0
        for (row in input) {
            if (row.split(whitespaceRegex).map { it.toInt() }.isSafeWhenDamp()) {
                countOfSafeReports++
            }
        }

        return countOfSafeReports
    }

    // Read a test input from the `src/main/resources/DayXX_test.txt` file:
    val testInput = readInput("main/resources/Day${day}_test")  // Test input
    check(part1(testInput) == part1ExpectedTestResult)
    check(part2(testInput) == part2ExpectedTestResult)

    // Read the input from the `src/main/resources/DayXX.txt` file.
    val input = readInput("main/resources/Day${day}")  // Actual input
    part1(input).println()
    part2(input).println()

    check(value = part1(input) == part1Answer)
    check(part2(input) == part2Answer)
}
