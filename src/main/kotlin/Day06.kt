import Day06.Direction.*
import io.github.oshai.kotlinlogging.KotlinLogging

private val log = KotlinLogging.logger {}

class Day06 {

    data class Coordinate(var col: Int, var row: Int) {
        override fun toString() = "($col,$row)"
    }

    data class GuardState(var position: Coordinate, var direction: Direction)

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

        val visitedLocations = calculatePatrolPath(input)

        return visitedLocations.size.toLong()
    }

    private fun calculatePatrolPath(input: List<String>): Set<Coordinate> {
        val width = input.first().length
        val height = input.size
        val visitedLocations = mutableSetOf<Coordinate>()

        // Starting conditions
        var guardPosition = findStartingPosition(input)
        var direction = UP

        // Iteratively look up the guard's position, and the next location in the next direction
        while (guardPosition.col in 0..<width && guardPosition.row in 0..<height) {
            log.debug { "Went $direction to $guardPosition. Found ${input[guardPosition.row][guardPosition.col]}" }
            visitedLocations.add(guardPosition)
            val projectedPosition = projectNextPosition(direction, guardPosition)
            if (outOfBounds(projectedPosition, width, height)) break
            if (input[projectedPosition.row][projectedPosition.col] == '#') {
                direction = direction.rotate()
            } else {
                guardPosition = projectedPosition
            }
        }

        return visitedLocations.toSet()
    }

    fun part2(input: List<String>): Long {
        var numberOfLoops = 0L
        log.debug { "Calculating path without obstacles..." }
        val patrolPath = calculatePatrolPath(input)

        for (locationForObstacle in patrolPath.drop(1)) {
            log.debug { "TESTING WITH OBSTACLE IN POSITION: $locationForObstacle" }
            var guardPosition = findStartingPosition(input)
            var direction = UP

            val visitedLocations = mutableSetOf<GuardState>()
            val width = input.first().length
            val height = input.size

            log.debug { "Starting in position $guardPosition" }
            while (guardPosition.col in 0..<width && guardPosition.row in 0..<height) {
                val guardState = GuardState(guardPosition, direction)
                if (guardState in visitedLocations) {
                    log.debug { "Loop detected!" }
                    numberOfLoops++
                    visitedLocations.add(guardState)
                    break
                }
                visitedLocations.add(guardState)

                val projectedPosition = projectNextPosition(direction, guardPosition)
                if (outOfBounds(projectedPosition, width, height)) {
                    log.debug { "Escaped!" }
                    break
                }

                if (input[projectedPosition.row][projectedPosition.col] == '#') {
                    log.debug { "Next space contains an obstacle, turning..." }
                    direction = direction.rotate()
                } else if (locationForObstacle == projectedPosition) {
                    log.debug { "Next space contains the temp obstacle, turning..." }
                    direction = direction.rotate()
                } else {
                    log.debug { "Next space is free, moving forward..." }
                    guardPosition = projectedPosition
                }

            }
        }
        return numberOfLoops
    }

    private fun outOfBounds(projectedPosition: Coordinate, width: Int, height: Int): Boolean {
        if (projectedPosition.col < 0 || projectedPosition.col >= width ||
            projectedPosition.row < 0 || projectedPosition.row >= height
        ) {
            return true
        }
        return false
    }

    private fun projectNextPosition(direction: Direction, guardPosition: Coordinate) = when (direction) {
        UP -> Coordinate(guardPosition.col, guardPosition.row - 1)
        DOWN -> Coordinate(guardPosition.col, guardPosition.row + 1)
        RIGHT -> Coordinate(guardPosition.col + 1, guardPosition.row)
        LEFT -> Coordinate(guardPosition.col - 1, guardPosition.row)
    }

    private fun findStartingPosition(input: List<String>) = Coordinate(
        input.first { it.contains('^') }.indexOf('^'),
        input.indexOfFirst { it.contains('^') }
    )

}
