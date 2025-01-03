import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.time.measureTime

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
