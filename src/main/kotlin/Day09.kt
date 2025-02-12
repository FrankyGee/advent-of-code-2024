class Day09 {

    fun part1(input: List<String>): Long {
        // Only 1 line for this puzzle
        val startingDiskMap = input[0]

        val filesystem = expand(startingDiskMap)
        val compactedFilesystem = compact(filesystem)
        println(compactedFilesystem)

        val checksum = calculateChecksum(compactedFilesystem)
        println(checksum)

        return checksum
    }

    private fun calculateChecksum(filesystem: List<Int>): Long {
        var checksum = 0L
        for ((index, fileId) in filesystem.withIndex()) {
            checksum += index * fileId
        }
        return checksum
    }

    fun compact(filesystem: List<Int>): List<Int> {
        // Working backwards, sort each block
        val compacted = mutableListOf<Int>()
        var lastFileBlockIndex = filesystem.size

        for ((blockIndex, block) in filesystem.withIndex()) {
            val tempLastFileBlockIndex = getLastFileBlockIndex(filesystem, lastFileBlockIndex - 1)
            if (tempLastFileBlockIndex < blockIndex) break  // We've compacted everything so stop
            if (block >= 0) {
                // Belongs to a file
                compacted.add(block)
            } else {
                // Is free space
                compacted.add(filesystem[tempLastFileBlockIndex])
                lastFileBlockIndex = tempLastFileBlockIndex
            }
//            println(compacted)
        }
        return compacted
    }

    fun getLastFileBlockIndex(filesystem: List<Int>, fromIndex: Int): Int {
        for (index in fromIndex downTo 0) {
            if (filesystem[index] >= 0) {
                // Belongs to a file
                return index
            }
        }
        return -1
    }

    fun expand(diskMap: String): List<Int> {
        return diskMap
            .map { it.digitToInt() }
            .mapIndexed { index, number ->
                if (index % 2 == 0) {
                    // represents a file
                    List(number) { ((index + 1) / 2) }
                } else {
                    // represents free space
                    List(number) { -1 }
                }
            }
            .flatten()
            .also { it.println() }
    }

    fun part2(input: List<String>): Long {

        return 0
    }

}
