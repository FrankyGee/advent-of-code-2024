class Day01 {
    fun splitIntoColumns(input: List<String>): MutableList<MutableList<Int>> {
        return input
            .map { line -> line.split(whitespaceRegex) }
            .fold(mutableListOf<MutableList<Int>>()) { acc, row ->
                if (acc.isEmpty()) {
                    for (column in row) {
                        acc.add(mutableListOf(column.toInt()))
                    }
                } else {
                    for ((index, column) in row.withIndex()) {
                        acc[index].add(column.toInt())
                    }
                }
                return@fold acc
            }
    }

    // Solution from Kotlin video https://www.youtube.com/watch?v=r7nMRJ57QA0
    // Approx 3x faster than mine
    fun simpleSplit(input: List<String>): Pair<List<Int>, List<Int>> {
        return input.map { line ->
            val left = line.substringBefore(" ").toInt()
            val right = line.substringAfterLast(" ").toInt()
            left to right
        }.unzip()
    }

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

    // Solution from Kotlin video https://www.youtube.com/watch?v=r7nMRJ57QA0
    fun altPart2(input: List<String>): Int {
        val (left, right) = splitIntoColumns(input)

        val frequencies = right.toList().groupingBy { it }.eachCount()
        return left.fold(0) { acc, num ->
            acc + num * frequencies.getOrDefault(num, 0)
        }
    }
}
