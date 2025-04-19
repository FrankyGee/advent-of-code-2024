import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/main/resources/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

val whitespaceRegex = Regex("""\s+""")


// Define ANSI color codes
object ConsoleColors {
    const val RESET = "\u001B[0m"
    const val BLACK = "\u001B[30m"
    const val RED = "\u001B[31m"
    const val GREEN = "\u001B[32m"
    const val YELLOW = "\u001B[33m"
    const val BLUE = "\u001B[34m"
    const val PURPLE = "\u001B[35m"
    const val CYAN = "\u001B[36m"
    const val WHITE = "\u001B[37m"

    // Bold
    const val BOLD = "\u001B[1m"
    // Underline
    const val UNDERLINE = "\u001B[4m"
}

fun String.bold() = "${ConsoleColors.BOLD}$this${ConsoleColors.RESET}"
fun String.red() = "${ConsoleColors.RED}$this${ConsoleColors.RESET}"

//fun main() {
//    val input = readInput("Day01")
//
//    val executionTime1 = measureTime {
//        for (i in 0..10000) {
//            splitIntoColumns(input)
//        }
//    }
//    println("splitIntoColumns(): $executionTime1")
//
//    val executionTime2 = measureTime {
//        for (i in 0..10000) {
//            simpleSplit(input)
//        }
//    }
//    println("simpleSplit(): $executionTime2")
//}
