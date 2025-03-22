import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day10Test {
    private val day = "10"

    private val testInput = readInput("Day${day}_test")
    private val input = readInput("Day${day}")
    private var sut = Day10()


    @Test
    fun `Day 10 Part 1 test`() {
        expect(36) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day 10 Part 1 actual`() {
        expect(0) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 10 Part 2 test`() {
        expect(0) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 10 Part 2 actual`() {
        expect(0) {
            sut.part2(input)
        }
    }

}
