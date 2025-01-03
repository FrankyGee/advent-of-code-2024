import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day01Test {
    private val day = "01"

    @Test
    fun `Day 1 Part 1 test`() {
        val sut = Day01()
        val input = readInput("Day${day}_test")  // Test input

        expect(11, {
            sut.part1(input)
        })

    }

    @Test
    fun `Day 1 Part 1 actual`() {
        val sut = Day01()
        val input = readInput("Day${day}")  // Test input

        expect(1660292) {
            sut.part1(input)
        }

    }

    @Test
    fun `Day 1 Part 2 actual`() {
        val sut = Day01()
        val input = readInput("Day${day}")  // Test input

        expect(22776016) {
            sut.part2(input)
        }
    }

    @Test
    fun `Day 1 Part 2 alt`() {
        val sut = Day01()
        val input = readInput("Day${day}")  // Test input

        expect(22776016) {
            sut.altPart2(input)
        }

    }
}
