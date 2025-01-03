import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day04Test {
    private val day = "04"

    private val testInput = readInput("Day${day}_test")
    private val input = readInput("Day${day}")
    private var sut = Day04()

    @Test
    fun `Day 4 Part 1 test`() {
        expect(18) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day 4 Part 2 test`() {
        expect(9) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 4 Part 1 actual`() {
        expect(2427) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 4 Part 2 actual`() {
        expect(1900) {
            sut.part2(input)
        }
    }

}
