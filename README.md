# Kotlin-notes
- 071821 initial commit with some notes for future edit

# What is Kotlin?
It seems like Kotlin is Java but more modern

# Some new syntax
- iteration
  - `repeat` 
- `when` 
- `with` 


# Class inheritance
- Code from https://developer.android.com/codelabs/basic-android-kotlin-training-classes-and-inheritance
- `abstract`
- `override` 
- `open` 
- Class constructors
```
abstract class Dwelling(private var residents:Int){
    abstract val buildingMaterial: String 
    abstract val capacity: Int 
    
    fun hasRoom(): Boolean{
        return residents < capacity
    }
    
    
}

class SquareCabin (residents: Int): Dwelling(residents){
    override val buildingMaterial = "Wood"
    override val capacity = 6
}

open class RoundHut(residents: Int): Dwelling(residents){
    override val buildingMaterial = "Straw"
    override val capacity = 4
}

class RoundTower(residents: Int) : RoundHut(residents){
    override val buildingMaterial = "Stone"
    override val capacity = 4
}

fun main(){
    val squareCabin = SquareCabin(6)
    val roundHut = RoundHut(3)
 	val roundTower = RoundTower(4)

    with(squareCabin){
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")    
    }
    
    with(roundHut){
        println("\nRound Hut\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")    
    }

    with(roundTower) {
        println("\nRound Tower\n==========")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
    }

}
```
