import Day06.Direction.*

class Day06 {

    data class Coordinate (var col: Int, var row: Int) {
        override fun toString() = "($col,$row)"
    }

    enum class Direction {
        UP, DOWN, LEFT, RIGHT;

        fun rotate(): Direction {
            return when (this) {
                UP -> RIGHT
                RIGHT -> DOWN
                DOWN -> LEFT
                LEFT -> UP
            }
        }
    }


    fun part1(input: List<String>): Long {
//        val map = mutableMapOf<Pair<Int, Int>, Map<Direction, Pair<Int, Int>>>()
        val visitedLocations = mutableSetOf<Coordinate>()

//        // Create map of each location and the location of the next obstacle (or last free space?) in each direction
//        for ((line, lineContent) in input.withIndex()) {
//            for ((column, columnContent) in lineContent.withIndex()) {
//                if (columnContent == '#') {
//                    // Impassable
//                    continue
//                }
//                val coordinate = column to line
//                map.getOrDefault(coordinate, emptyMap())
//            }
//
//        }

        // Iteratively look up the guard's position, and the next location in the next direction
        val width = input.first().length
        val height = input.size
//        val map = input.reduce { acc, s -> acc + s }

        var guardPosition = Coordinate(
            input.first { it.contains('^') }.indexOf('^'),
            input.indexOfFirst { it.contains('^') }
        )
        var direction = UP

        while (guardPosition.col in 0..<width && guardPosition.row in 0..<height) {
            println("Went $direction to $guardPosition. Found ${input[guardPosition.row][guardPosition.col]}")
            visitedLocations.add(guardPosition)
            val newGuardPosition = when (direction) {
                UP -> Coordinate(guardPosition.col, guardPosition.row - 1)
                DOWN -> Coordinate(guardPosition.col, guardPosition.row + 1)
                RIGHT -> Coordinate(guardPosition.col + 1, guardPosition.row)
                LEFT -> Coordinate(guardPosition.col - 1, guardPosition.row)
            }
            if (newGuardPosition.col < 0 || newGuardPosition.col >= width ||
                newGuardPosition.row < 0 || newGuardPosition.row >= height) {
                break
            }
            if (input[newGuardPosition.row][newGuardPosition.col] == '#') {
                direction = direction.rotate()
            } else {
                guardPosition = newGuardPosition
            }
        }


//        for ((line, lineContent) in input.withIndex()) {
//            for ((column, columnContent) in lineContent.withIndex()) {
//
//            }
//        }

        return visitedLocations.size.toLong()
    }

    fun part2(input: List<String>): Long {

        return 0
    }
}
