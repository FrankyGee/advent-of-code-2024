import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day09Test {
    private val day = "09"

    private val testInput = readInput("Day${day}_test")
    private val input = readInput("Day${day}")
    private var sut = Day09()

    @Test
    fun `Day 9 expand(12345)`() {
        val expected = "0..111....22222".toList().map { if (it == '.') -1 else it.digitToInt() }
        expect(expected) {
            sut.expand("12345")
        }
    }

    @Test
    fun `Day 9 expand(2333133121414131402)`() {
        val expected = "00...111...2...333.44.5555.6666.777.888899".toList().map { if (it == '.') -1 else it.digitToInt() }
        expect(expected) {
            sut.expand("2333133121414131402")
        }
    }

    @Test
    fun `Day 9 compact(12345)`() {
        val input = "0..111....22222".toList().map { if (it == '.') -1 else it.digitToInt() }
        val expected = "022111222".toList().map { it.digitToInt() }
        expect(expected) {
            sut.compact(input)
        }
    }

    @Test
    fun `Day 9 compact(2333133121414131402)`() {
        val input = "00...111...2...333.44.5555.6666.777.888899".toList().map { if (it == '.') -1 else it.digitToInt() }
        val expected = "0099811188827773336446555566".toList().map { it.digitToInt() }
        expect(expected) {
            sut.compact(input)
        }
    }

    @Test
    fun `Day 9 Part 1 test`() {
        expect(1928) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day 9 Part 1 actual`() {
        expect(6415184586041) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 9 Part 2 test`() {
        expect(-1) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 9 Part 2 actual`() {
        expect(-1) {
            sut.part2(input)
        }
    }

}
