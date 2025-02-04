import io.github.oshai.kotlinlogging.KotlinLogging

private val log = KotlinLogging.logger {}

class Day07 {

    enum class Operator(val apply: (Int, Int) -> Int, val symbol: String) {
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

    private fun parseLine(line: String): Pair<Int, List<Int>> {
        val target = line.substringBefore(':').toInt()
        val components = line.substringAfter(": ").split(' ').map(String::toInt)
        return Pair(target, components)
    }

    private fun isValid(
        target: Int,
        operators: List<Operator>,
        numbers: List<Int>
    ): Boolean =
        generateOperatorCombinations(operators, numbers.size - 1)
            .map { operatorCombo ->
                numbers.drop(1)  // first number will initialise the accumulator later
                    .zip(operatorCombo)  // zip each remaining number with the operator that will apply to it
                    .fold(numbers.first()) { acc, (number, operator) ->  // apply each operator/number pair in sequence
                        operator.apply(acc, number)
                    }
            }
            .any { it == target }

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


//    private fun isValid(testValue: String, components: List<Int>, operators: List<Operator>) {
//        for (component in components) {
//
//            for (operator in operators) {
//                // magic?
//            }
//
//        }
//    }


//    fun List<Int>.combinations(operators: List<Operator>): Sequence<Pair<Int, List<Pair<Operator, Int>>>> = sequence {
//        val numbers = this@combinations
//        if (numbers.isEmpty()) return@sequence
//
//        val operatorCombinations = generateOperatorCombinations(operators, numbers.size - 1)
//
//        for (ops in operatorCombinations) {
//            val operations = numbers.drop(1).zip(ops)
//            val result = operations.fold(numbers.first()) { acc, (op: Operator, num) ->
//                op.apply(acc, num)
//            }
//            yield(result to operations)
//        }
//    }


    fun part2(input: List<String>): Long {

        return 0
    }
}
