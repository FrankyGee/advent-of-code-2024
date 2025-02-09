typealias Report = List<Int>

class Day02 {
    private fun Report.isSafe(): Boolean {
        val sorted = this.toMutableSet<Int>().sorted()

        // Report must be strictly ascending or descending
        if (sorted != this && sorted.asReversed() != this) return false

        // Check that every two adjacent numbers are the correct distance apart
        return this.windowed(2, 1).all { (left, right) -> kotlin.math.abs(left - right) in 1..3 }
    }

    private fun Report.isSafeWhenDamp(): Boolean {
        var result = false
        (0..this.size).forEach { i ->
            result = result || this.filterIndexed { index, _ -> index != i }.isSafe()
        }
        return result
    }

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
}
