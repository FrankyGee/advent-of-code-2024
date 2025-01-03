class Day04 {

    enum class Direction(
        private val rowModifier: Int,
        private val colModifier: Int
    ) {
        LEFT(0, -1),
        RIGHT(0, 1),
        UP(-1, 0),
        DOWN(1, 0),
        UP_LEFT(-1, -1),
        UP_RIGHT(-1, 1),
        DOWN_LEFT(1, -1),
        DOWN_RIGHT(1, 1);

        fun reverse(): Direction {
            return when (this) {
                LEFT -> RIGHT
                RIGHT -> LEFT
                UP -> DOWN
                DOWN -> UP
                UP_LEFT -> DOWN_RIGHT
                DOWN_RIGHT -> UP_LEFT
                UP_RIGHT -> DOWN_LEFT
                DOWN_LEFT -> UP_RIGHT
            }
        }

        fun directionsToSearch(): List<Direction> {
            val components = this.name.split("_")
            if (components.size != 2) return emptyList()
            return listOf(Direction.valueOf(components[0]), Direction.valueOf(components[1]))
        }
        
        fun translate(col: Int, row: Int, distance: Int = 1): Pair<Int, Int> = Pair(col + (colModifier * distance), row + (rowModifier * distance))
    }

    private fun checkNextChar(
        input: List<String>,
        col: Int,
        row: Int,
        searchString: String,
        direction: Direction
    ): Boolean {
        // Success condition
        if (searchString.isEmpty()) {
            return true
        }

        val (newCol, newRow) = direction.translate(col, row)

        // Position is past an edge
        if (isOutsideBounds(input, newCol, newRow)) {
            return false
        }

        val nextChar = input[newRow][newCol]
        if (nextChar == searchString.first()) {
            if (checkNextChar(input, newCol, newRow, searchString.substring(1), direction)) {
                return true
            }
        }
        return false
    }

    private fun isOutsideBounds(input: List<String>, newCol: Int, newRow: Int) =
        newCol < 0 || newCol >= input.first().length ||
                newRow < 0 || newRow >= input.size

    fun part1(input: List<String>): Long {
        val searchString = "XMAS"
        var count = 0L

        // Lines
        for ((y, line) in input.withIndex()) {
            // Characters in line
            for ((x, char) in line.withIndex()) {
                // Found the first letter
                if (char == searchString.first()) {  // 'X'
                    // Look in each direction
                    for (direction in Direction.entries) {
                        // Check for subsequent chars recursively
                        if (checkNextChar(input, x, y, searchString.substring(1), direction)) {
                            count++
//                            println("Line $y : match found at $x in direction $direction")
                        }
                    }
                }
            }

        }

        /*input.windowed(SEARCH_TERM.length).forEach { window ->
            window.forEach { line ->
                line.

            }
        }*/

        return count
    }

    fun part2(input: List<String>): Long {
        val searchString = "MAS"
        var count = 0L

        // Lines
        for ((y, line) in input.withIndex()) {
            // Characters in line
            for ((x, char) in line.withIndex()) {
                // Found the first letter
                if (char == searchString.first()) {  // 'X'
                    // Look in each direction
                    for (direction in Direction.entries) {
                        // Check for subsequent chars recursively
                        if (checkNextChar(input, x, y, searchString.substring(1), direction)) {

                            // Look in the orthogonal directions adjacent to the diagonal direction of the first hit
                            // for the other letters
                            var foundM = false
                            var foundS = false
                            for (possibleDirection in direction.directionsToSearch()) {
                                val (newX, newY) = possibleDirection.translate(x, y, 2)
                                if (isOutsideBounds(input, newX, newY)) continue
                                if (input[newY][newX] == 'M') foundM = true
                                if (input[newY][newX] == 'S') foundS = true
                            }
                            if (foundM && foundS) count++
                        }
                    }
                }
            }

        }
        // Finding every instance of MAS, but an X is made of two instances, so divide the final answer by 2
        check(count % 2 == 0L) { "Expected an even number, because all hits are counted twice, but instead got $count" }
        return count / 2
    }
}
