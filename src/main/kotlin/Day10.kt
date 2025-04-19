class Day10 {

    data class Coordinate(var col: Int, var row: Int) {
        override fun toString() = "($col,$row)"
    }

    enum class Direction(private val horizontal: Int, private val vertical: Int) {
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP(0, -1),
        DOWN(0, 1);

        fun reverse(): Direction {
            return when (this) {
                LEFT -> RIGHT
                RIGHT -> LEFT
                UP -> DOWN
                DOWN -> UP
            }
        }

        fun translate(coordinate: Coordinate): Coordinate {
            return Coordinate(coordinate.col + horizontal, coordinate.row + vertical)
        }
    }

    class Grid(input: List<String>) {
        val rows: Array<Array<Int>> = Array(input.size) { row ->
            input[row].map {
                if (it == '.') {
                    -1
                } else {
                    it.digitToInt()
                }
            }.toTypedArray()
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

    fun getTrailheads(grid: Grid): Sequence<Coordinate> {
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

    fun findTrailScore(trailhead: Coordinate, grid: Grid): Int {
        // Check each direction for a trail (n + 1)
        val elevation = grid.get(trailhead)


        // For each trail, follow it
        // If it reaches 9, add 1 to the score
        // If there are no trails, stop
        return 0
    }

    fun countTrails(position: Coordinate, grid: Grid): Int {
        if (grid.get(position) == 9) return 1

        var completeRoutes = 0
        for (direction in Direction.entries) {
            val nextPosition = direction.translate(position)
            if (grid.isInBounds(nextPosition) && grid.get(nextPosition) > grid.get(position)) {
                completeRoutes += countTrails(nextPosition, grid)
            }
        }
        return completeRoutes
    }


    fun part2(input: List<String>): Long {


        return -1
    }

}
