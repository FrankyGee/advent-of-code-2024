import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day08Test {
    private val day = "08"

    private val testInput = readInput("Day${day}_test")
    private val input = readInput("Day${day}")
    private var sut = Day08()

    @Test
    fun `Day 8 Part 1 test`() {
        expect(14) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day 8 Part 2 test`() {
        expect(34) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 8 Part 1 actual`() {
        expect(280) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 8 Part 2 actual`() {
        expect(958) {
            sut.part2(input)
        }
    }

}
