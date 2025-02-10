class Day08 {
    data class Coordinate(var col: Int, var row: Int) {
        override fun toString() = "($col,$row)"
    }

    data class Antenna(var name: Char, var coordinate: Coordinate)

    fun part1(input: List<String>): Long {

        // Build a mapping of coordinates for each frequency

        /*
        For each frequency
          - generate all possible connections

        For each connection
          - calculate the distance between coordinates
          - calculate the coordinates that are the same distance in each direction along the connection
          - Add new coordinates to a Set
        */

        val width = input.first().length
        val height = input.size

        val allAntinodeLocations = mutableSetOf<Coordinate>()
        val antennaLocationsByFrequency: Map<Char, List<Coordinate>> = getAntennaLocationsPerFrequency(input)
        for (frequency in antennaLocationsByFrequency.keys) {
            val locations = calculateAllAntinodes(antennaLocationsByFrequency[frequency])
            allAntinodeLocations
                .addAll(locations.filterNot { outOfBounds(it, width, height) })
        }
        return allAntinodeLocations.size.toLong()

    }

    private fun getAntennaLocationsPerFrequency(input: List<String>): Map<Char, List<Coordinate>> {
        return input.asSequence()
            .withIndex()
            .flatMap { (rowIndex: Int, row: String) ->
                row.mapIndexed { colIndex, char ->
                    char to Coordinate(colIndex, rowIndex)
                }
            }
            .filter { it.first != '.' }
            .groupBy({ it.first }, { it.second })
    }

    private fun calculateAllAntinodes(antennas: List<Coordinate>?): Set<Coordinate> {
        if (antennas.isNullOrEmpty()) return emptySet()

        val processedAntennas = mutableSetOf<Coordinate>()
        val antinodes = mutableSetOf<Coordinate>()
        for (antenna in antennas) {
            processedAntennas.add(antenna)
            for (otherAntenna in antennas.filterNot { it in processedAntennas }) {
                val antinodesForPair = calculateAntinodesForPair(antenna, otherAntenna)
                antinodes.addAll(antinodesForPair)
//                debug(antinodesForPair, antenna, otherAntenna)
            }
        }

        return antinodes
    }

    private fun debug(
        antinodesForPair: Collection<Coordinate>,
        antenna: Coordinate,
        otherAntenna: Coordinate
    ) {
        for (row in 0..11) {
            var line = ""
            for (col in 0..11) {
                val thisCoord = Coordinate(col, row)
                line += when (thisCoord) {
                    in antinodesForPair -> {
                        '+'
                    }

                    antenna, otherAntenna -> {
                        '*'
                    }

                    else -> {
                        '.'
                    }
                }
                if (col == 11) {
                    println(line)
                }
            }
        }
    }

    private fun calculateAntinodesForPair(
        antenna: Coordinate,
        otherAntenna: Coordinate
    ): Collection<Coordinate> {
        val distRow = antenna.row - otherAntenna.row
        val distCol = antenna.col - otherAntenna.col

        val one = Coordinate(antenna.col + distCol, antenna.row + distRow)
        val two = Coordinate(otherAntenna.col - distCol, otherAntenna.row - distRow)

        return listOf(
            one, two
        )
    }


    fun part2(input: List<String>): Long {
        return 0
    }


    private fun outOfBounds(projectedPosition: Coordinate, width: Int, height: Int): Boolean {
        return projectedPosition.col < 0 || projectedPosition.col >= width ||
                projectedPosition.row < 0 || projectedPosition.row >= height
    }


}
