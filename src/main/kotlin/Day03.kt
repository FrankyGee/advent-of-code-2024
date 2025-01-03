class Day03 {

    fun part1(input: List<String>): Int {
        return input.flatMap { row -> Regex("""mul\(\d+,\d+\)""").findAll(row) }
            .map { it.value }
            .also { println(it) }
            .map { Regex("""\d+""").findAll(it) }
            .sumOf { it.fold(1) { acc: Int, matchResult: MatchResult -> acc * matchResult.value.toInt() } }
    }

    fun part2(input: List<String>): Int {
        var total = 0
        var isDo = true

        for (line in input) {
            val indexesOfDo = Regex("""do\(\)""").findAll(line)
                .map { match -> match.range.first }
                .toList()
                .toIntArray()

            val indexesOfDont = Regex("""don't\(\)""").findAll(line)
                .map { match -> match.range.first }
                .toList()
                .toIntArray()

            val rangesOfDo = mutableListOf<IntRange>()
            var lastIndex = 0
            for (i in 0..line.length) {
                if (isDo) {
                    if (i in indexesOfDont) {
                        rangesOfDo.add(IntRange(lastIndex, i - 1))
                        isDo = false
                        lastIndex = i
                    }
                } else {
                    if (i in indexesOfDo) {
                        isDo = true
                        lastIndex = i
                    }
                }
            }
            if (isDo) {
                rangesOfDo.add(IntRange(lastIndex, line.length - 1))
            }

            total += Regex("""mul\((\d+),(\d+)\)""").findAll(line)
                .filter { rangesOfDo.any { intRange -> it.range.first in intRange } }
                .fold(0) { acc: Int, matchResult: MatchResult ->
                    val left = matchResult.groups[1]?.value?.toInt() ?: 0
                    val right = matchResult.groups[2]?.value?.toInt() ?: 0
                    acc + (left * right)
                }
        }
        return total
    }
}
