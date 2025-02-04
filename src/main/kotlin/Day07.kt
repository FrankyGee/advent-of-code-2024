import io.github.oshai.kotlinlogging.KotlinLogging

private val log = KotlinLogging.logger {}

class Day07 {

    enum class Operator(val apply: (Long, Long) -> Long, private val symbol: String) {
        PLUS({ a, b -> a + b }, "+"),
        MULTIPLY({ a, b -> a * b }, "*");

        override fun toString() = symbol
    }


    fun part1(input: List<String>): Long {
        val operators = listOf(Operator.PLUS, Operator.MULTIPLY)
        var totalOfValidValues = 0L

        for (line in input) {
            println("Processing line $line")

            val (target, numbers) = parseLine(line)

            if (isValid(target, operators, numbers)) {
                totalOfValidValues += target
            }
        }

        return totalOfValidValues
    }

    private fun parseLine(line: String): Pair<Long, List<Long>> {
        val target = line.substringBefore(':').toLong()
        val components = line.substringAfter(": ").split(' ').map(String::toLong)
        return Pair(target, components)
    }

    private fun isValid(
        target: Long,
        operators: List<Operator>,
        numbers: List<Long>
    ): Boolean =
        generateOperatorCombinations(operators, numbers.size - 1)
            .map { operatorCombo ->
                numbers.drop(1)  // first number will be used to initialise the accumulator later
                    .zip(operatorCombo)  // zip each remaining number with the operator that will apply to it
                    .fold(numbers.first()) { acc, (number, operator) ->  // apply each operator/number pair in sequence
                        operator.apply(acc, number)
                    }
            }
            .any { it == target }

    /**
     * Ok yes, I used Claude 3.5 Sonnet to help me work out this function
     */
    private fun generateOperatorCombinations(operators: List<Operator>, slots: Int): Sequence<List<Operator>> =
        sequence {
            if (slots == 0) {
                yield(emptyList())
                return@sequence
            }

            generateOperatorCombinations(operators, slots - 1).forEach { combination ->
                operators.forEach { op ->
                    yield(combination + op)
                }
            }
        }


    fun part2(input: List<String>): Long {

        return 0
    }
}
