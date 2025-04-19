import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day10Test {
    private val day = "10"

    private val testInput = readInput("Day${day}_test")
    private val input = readInput("Day${day}")
    private var sut = Day10()

    @Test
    fun `find trailheads`() {
        val grid = Day10.Grid(
            """
                ..90..9
                ...1.98
                ...2..7
                6543456
                765.987
                876....
                987....
            """.trimIndent().lines()
        )
        val trailheads = sut.getTrailheads(grid)
        expect(1) {
            trailheads.count()
        }
        expect(Day10.Coordinate(3, 0)) {
            trailheads.first()
        }
    }

    @Test
    fun `countTrails 4`() {
        val grid = Day10.Grid(
            """
                ..90..9
                ...1.98
                ...2..7
                6543456
                765.987
                876....
                987....
            """.trimIndent().lines()
        )
        val trailheads = sequenceOf(Day10.Coordinate(3, 0))
        expect(4) {
            sut.countTrails(trailheads.first(), grid)
        }
    }

    @Test
    fun `countTrails 1`() {
        val grid = Day10.Grid(
            """
                10..9..
                2...8..
                3...7..
                4567654
                ...8..3
                ...9..2
                .....01
            """.trimIndent().lines()
        )
        val trailheads = sequenceOf(Day10.Coordinate(1, 0))
        expect(1) {
            sut.countTrails(trailheads.first(), grid)
        }
    }

    @Test
    fun `countTrails 2`() {
        val grid = Day10.Grid(
            """
                10..9..
                2...8..
                3...7..
                4567654
                ...8..3
                ...9..2
                .....01
            """.trimIndent().lines()
        )
        val trailheads = sequenceOf(Day10.Coordinate(5, 6))
        expect(2) {
            sut.countTrails(trailheads.first(), grid)
        }
    }

    @Test
    fun `Day 10 Part 1 test`() {
        expect(36) {
            sut.part1(testInput)
        }
    }

    @Test
    fun `Day 10 Part 1 actual`() {
        expect(0) {
            sut.part1(input)
        }
    }

    @Test
    fun `Day 10 Part 2 test`() {
        expect(0) {
            sut.part2(testInput)
        }
    }

    @Test
    fun `Day 10 Part 2 actual`() {
        expect(0) {
            sut.part2(input)
        }
    }

}
