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

        override fun toString(): String {
            return format(Coordinate(-1, -1))
        }

        fun format(highlight: Coordinate): String {
            return rows.mapIndexed { rowIndex, row ->
                row.mapIndexed { colIndex, cell ->
                    val cellStr = if (cell == -1) "." else cell.toString()
                    if (rowIndex == highlight.row && colIndex == highlight.col) {
                        cellStr.red()
                    } else {
                        cellStr
                    }
                }.joinToString(" ")
            }.joinToString("\n")
        }
    }

    fun part1(input: List<String>): Long {
        var totalScore = 0L
        val grid = Grid(input)

        for (trailhead in getTrailheads(grid)) {
            totalScore += calculateScore(trailhead, grid)
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

    fun calculateScore(trailhead: Coordinate, grid: Grid): Int {
        return findReachable9s(trailhead, grid).size
    }

    fun findReachable9s(position: Coordinate, grid: Grid): Set<Coordinate> {
//        println(grid.format(position) + "\n")
        if (grid.get(position) == 9) return setOf(position)

        val reachable9s = mutableSetOf<Coordinate>()
        for (direction in Direction.entries) {
            val nextPosition = direction.translate(position)
            if (grid.isInBounds(nextPosition) && grid.get(nextPosition) == grid.get(position) + 1) {
                reachable9s += findReachable9s(nextPosition, grid)
            }
        }
        return reachable9s
    }

    fun calculateRating(position: Coordinate, grid: Grid): Int {
//        println(grid.format(position) + "\n")
        if (grid.get(position) == 9) return 1

        var numberOfTrails = 0
        for (direction in Direction.entries) {
            val nextPosition = direction.translate(position)
            if (grid.isInBounds(nextPosition) && grid.get(nextPosition) == grid.get(position) + 1) {
                numberOfTrails += calculateRating(nextPosition, grid)
            }
        }
        return numberOfTrails
    }


    fun part2(input: List<String>): Long {
        var totalRating = 0L
        val grid = Grid(input)

        for (trailhead in getTrailheads(grid)) {
            totalRating += calculateRating(trailhead, grid)
        }

        return totalRating
    }

}
