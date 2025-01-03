import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day02Test {
    private val day = "02"

    private val testInput = readInput("Day${day}_test")
    private val input = readInput("Day${day}")
    private var sut = Day02()

    @Test
    fun `Day 2 Part 1 test`() {
        expect(2) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day 2 Part 2 test`() {
        expect(4) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 2 Part 1 actual`() {
        expect(516) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 2 Part 2 actual`() {
        expect(561) {
            sut.part2(input)
        }
    }
}
