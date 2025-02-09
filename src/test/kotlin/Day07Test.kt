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
        expect(11387) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 7 Part 1 actual`() {
        expect(4364915411363) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 7 Part 2 actual`() {
        expect(38322057216320) {
            sut.part2(input)
        }
    }

}
