import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day03Test {
    private val day = "03"

    private val testInput = readInput("Day${day}_test")
    private val input = readInput("Day${day}")
    private var sut = Day03()

    @Test
    fun `Day 3 Part 1 test`() {
        expect(322) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day 3 Part 2 test`() {
        expect(234) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 3 Part 1 actual`() {
        expect(178794710) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 3 Part 2 actual`() {
        expect(76729637) {
            sut.part2(input)
        }
    }

}
