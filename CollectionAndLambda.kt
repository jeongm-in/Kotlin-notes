fun main() {
val words = listOf("about", "acute", "awesome", "balloon", "best", "brief", "class", "coffee", "creative")


//     val wordsStartWithB = words.filter{it[0] == 'b'}
	val wordsStartWithB = words.filter{it.startsWith("b", ignoreCase = true)}
    println(wordsStartWithB)
    
    val shuffledWordsStartWithB = wordsStartWithB.shuffled()
    println(shuffledWordsStartWithB)
    
    // get first n items in the collection
    val firstTwo = shuffledWordsStartWithB.take(2).sorted()
    println(firstTwo)
    
    // one random word that starts with the letter c 
    val oneRandomWordStartWithC = words.filter{it.startsWith("c", ignoreCase = true)}.shuffled().take(1)
    println(oneRandomWordStartWithC)

}
