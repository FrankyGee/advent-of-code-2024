class Day05 {

    fun part1(input: List<String>): Long {
        val indexOfBlankLine = input.indexOfFirst { s -> s.isBlank() }
        val rawRules = input.subList(0, indexOfBlankLine)
        val rawUpdates = input.subList(indexOfBlankLine + 1, input.size)

        // Process rules
        // ===============

        // For each line until blank line
        //   split('|') -> Pair
        val rules: Map<Int, List<Int>> = rawRules.map {
            val left = it.substringBefore('|').toInt()
            val right = it.substringAfter('|').toInt()
            left to right
        }.groupBy({ it.first }, { it.second })

        rules.println()

        // Extract Updates
        // ===============

        // One update per line
        // Test validity of update
        //     Track pages seen
        //     For each page, look up rules and ensure that no rules values are in the list of pages seen
        // If valid, get middle value & add to running total
        // filter(::isValid).map(getMiddleItem).sum()
        var total = 0L
        val incorrectlyOrderedUpdates = mutableListOf<List<Int>>()
        for (update in rawUpdates) {
            val seenPages = mutableListOf<Int>()
            val pages = update.split(',').map(String::toInt)
            require(pages.size % 2 == 1) { "There must be an odd number of pages" }

            // Apply rules
            var valid = true
            for (page in pages) {
                val pagesRequiredAfter: List<Int> = rules.getOrDefault(page, emptyList())
                if (pagesRequiredAfter.any() { p -> p in seenPages }) {
                    valid = false
                    break
                }
                seenPages.add(page)
            }

            println("Result of $update is $valid")
            if (valid) {
                val middleIndex = ((pages.size - 1) / 2)
                println("Middle index is $middleIndex with value ${pages[middleIndex]}")
                total += pages[middleIndex]
            } else {
                incorrectlyOrderedUpdates.add(pages)
            }
        }


//        rawUpdates
//            .map { it.split(',').map { it.toInt() } }
//            .filter { page ->
//                page
//            }

        return total
    }

    fun part2(input: List<String>): Long {
        val indexOfBlankLine = input.indexOfFirst { s -> s.isBlank() }
        val rawRules = input.subList(0, indexOfBlankLine)
        val rawUpdates = input.subList(indexOfBlankLine + 1, input.size)

        // Process rules
        // ===============

        val rules: Map<Int, List<Int>> = rawRules.map {
            val left = it.substringBefore('|').toInt()
            val right = it.substringAfter('|').toInt()
            left to right
        }.groupBy({ it.first }, { it.second })

        rules.println()

        // Extract Updates
        // ===============

        val incorrectlyOrderedUpdates = mutableListOf<List<Int>>()
        for (update in rawUpdates) {
            val seenPages = mutableListOf<Int>()
            val pages = update.split(',').map(String::toInt)
            require(pages.size % 2 == 1) { "There must be an odd number of pages" }

            // Find invalid updates
            for (page in pages) {
                val pagesRequiredAfter: List<Int> = rules.getOrDefault(page, emptyList())
                if (pagesRequiredAfter.any() { p -> p in seenPages }) {
                    incorrectlyOrderedUpdates.add(pages)
                    break
                }
                seenPages.add(page)
            }
        }

        var total = 0L

        // Fix incorrect updates and sum middle values
        for (update in incorrectlyOrderedUpdates) {
            val reorderedUpdate = update.sortedWith { i1, i2 ->
                if (i2 in rules.getOrDefault(i1, emptyList())) {
                    1
                } else {
                    -1
                }
            }
            val middleIndex = ((reorderedUpdate.size - 1) / 2)
            println("Fixed update: $reorderedUpdate")
            println("Middle index is $middleIndex with value ${reorderedUpdate[middleIndex]}")
            total += reorderedUpdate[middleIndex]
        }

        return total
    }
}
