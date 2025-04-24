import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day11Test {
    private val day = "11"
    private val testInput = readInput("Day${day}_test")
    private val input = readInput("Day${day}")
    private var sut = Day11()

//    @Test
//    fun applyRules() {
//        expect(listOf(253000L)) {
//            sut.applyRules((125L))
//        }
//        expect(listOf(1L, 7L)) {
//            sut.applyRules((17L))
//        }
//        expect(listOf(253L, 0L)) {
//            sut.applyRules(253000)
//        }
//                expect (listOf(14168L)) {
//            sut.applyRules((7))
//        }
//    }

    @Test
    fun `Day 11 Part 1 test`() {
        expect(55312) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day 11 Part 1 actual`() {
        expect(194557) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 11 Part 2 test`() {
        expect(65601038650482) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 11 Part 2 actual`() {
        expect(231532558973909) {
            sut.part2(input)
        }
    }
}
