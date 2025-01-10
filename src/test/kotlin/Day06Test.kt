import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day06Test {
    private val day = "06"

    private val testInput = readInput("Day${day}_test")
    private val input = readInput("Day${day}")
    private var sut = Day06()

    @Test
    fun `Day 6 Part 1 test`() {
        expect(41) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day 6 Part 2 test`() {
        expect(-1) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 6 Part 1 actual`() {
        expect(4374) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 6 Part 2 actual`() {
        expect(-1) {
            sut.part2(input)
        }
    }

}
