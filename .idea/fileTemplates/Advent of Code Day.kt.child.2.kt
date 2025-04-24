#if (${PACKAGE_NAME}  && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}

#end
import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day${Day}Test {
    private val testInput = readInput("Day${Day}_test")
    private val input = readInput("Day${Day}")
    private var sut = Day${Day}()

    @Test
    fun `Day ${Day} Part 1 test`() {
        expect(0) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day ${Day} Part 1 actual`() {
        expect(0) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day ${Day} Part 2 test`() {
        expect(0) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day ${Day} Part 2 actual`() {
        expect(0) {
            sut.part2(input)
        }
    }
}
