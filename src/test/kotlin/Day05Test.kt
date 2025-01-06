import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day05Test {
    private val day = "05"

    private val testInput = readInput("Day${day}_test")
    private val input = readInput("Day${day}")
    private var sut = Day05()

    @Test
    fun `Day 5 Part 1 test`() {
        expect(143) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day 5 Part 2 test`() {
        expect(123) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 5 Part 1 actual`() {
        expect(4996) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 5 Part 2 actual`() {
        expect(6311) {
            sut.part2(input)
        }
    }

}
