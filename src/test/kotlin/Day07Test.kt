import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day07Test {
    private val day = "07"

    private val testInput = readInput("Day${day}_test")
    private val input = readInput("Day${day}")
    private var sut = Day07()

    @Test
    fun `Day 7 Part 1 test`() {
        expect(3749) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day 7 Part 2 test`() {
        expect(-1) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 7 Part 1 actual`() {
        expect(-1) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 7 Part 2 actual`() {
        expect(-1) {
            sut.part2(input)
        }
    }

}
