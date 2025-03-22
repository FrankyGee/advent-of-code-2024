class Day10 {

    data class Coordinate(var col: Int, var row: Int) {
        override fun toString() = "($col,$row)"
    }

    enum class Direction(private val horizontal: Int, private val vertical: Int) {
        LEFT(0, -1),
        RIGHT(0, 1),
        UP(-1, 0),
        DOWN(1, 0);

        fun reverse(): Direction {
            return when (this) {
                LEFT -> RIGHT
                RIGHT -> LEFT
                UP -> DOWN
                DOWN -> UP
            }
        }
        fun translate(coordinate: Coordinate): Coordinate {
            return Coordinate(coordinate.col + horizontal , coordinate.row + vertical)
        }
    }

    class Grid(input: List<String>) {
        val rows: Array<Array<Int>> = Array(input.size) { row ->
            input[row].map { it.digitToInt() }.toTypedArray()
        }

        fun get(coordinate: Coordinate): Int {
            return rows[coordinate.row][coordinate.col]
        }

        fun isInBounds(coordinate: Coordinate): Boolean {

            return ((coordinate.row in rows.indices) && (coordinate.col >= 0) && (coordinate.col < rows[0].size))
        }

    }

    fun part1(input: List<String>): Long {
        var totalScore = 0L
        val grid = Grid(input)

        for (trailhead in getTrailheads(grid)) {
            totalScore += findTrailScore(trailhead, grid)
        }

        return totalScore
    }

    private fun getTrailheads(grid: Grid): Sequence<Coordinate> {
        return sequence {
            for ((rowIndex, row) in grid.rows.withIndex()) {
                for ((cellIndex, cell) in row.withIndex()) {
                    if (cell == 0) {
                        yield(Coordinate(cellIndex, rowIndex))
                    }
                }
            }
        }
    }

    private fun findTrailScore(trailhead: Coordinate, grid: Grid): Int {
        // Check each direction for a trail (n + 1)
        val elevation = grid.get(trailhead)


        // For each trail, follow it
            // If it reaches 9, add 1 to the score
        // If there are no trails, stop
    }

    private fun followTrail(position: Coordinate, grid: Grid): Int {
        for (direction in Direction.entries) {
            val nextPosition = direction.translate(position)
        }
    }


    fun part2(input: List<String>): Long {


        return -1
    }

}
