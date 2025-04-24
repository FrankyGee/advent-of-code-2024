import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day12Test {
    private val testInput = readInput("Day12_test")
    private val input = readInput("Day12")
    private var sut = Day12()

    @Test
    fun `Day 12 Part 1 test`() {
        expect(1930) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day 12 Part 1 actual`() {
        expect(0) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 12 Part 2 test`() {
        expect(0) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 12 Part 2 actual`() {
        expect(0) {
            sut.part2(input)
        }
    }
}
