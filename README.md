# Kotlin-notes
- 071821 initial commit with some notes for future edit
- 071821 add more code to the inheritance section, add syntax highlighting 


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
- 
```kotlin
import kotlin.math.PI
import kotlin.math.sqrt

abstract class Dwelling(private var residents:Int){
    abstract val buildingMaterial: String 
    abstract val capacity: Int 
    abstract fun floorArea(): Double
    fun hasRoom(): Boolean{
        return residents < capacity
    }
    
    fun getRoom(){
        if(hasRoom()){
            residents++
            println("Room assigned")
        }else{
            println("Not enough room")
        }
    }
    
    
    
    
}

class SquareCabin (residents: Int, val length:Double): Dwelling(residents){
    override val buildingMaterial = "Wood"
    override val capacity = 6
    override fun floorArea():Double{
        return length * length
    }
}

open class RoundHut(residents: Int, val radius: Double): Dwelling(residents){
    override val buildingMaterial = "Straw"
    override val capacity = 4
    override fun floorArea(): Double{
        return radius * radius * PI
    }
    fun calculateMaxCarpetSize(): Double{
        val diameter = 2 * radius
        return sqrt(diameter * diameter / 2)
    }
}

class RoundTower(residents: Int, radius: Double, val floors:Int = 2) : RoundHut(residents, radius){
    override val buildingMaterial = "Stone"
    override val capacity = 4
    override fun floorArea(): Double{
        return super.floorArea() * floors
    }
}

fun main(){
    val squareCabin = SquareCabin(6, 3.0)
    val roundHut = RoundHut(3, 3.0)
 	val roundTower = RoundTower(4, 5.25)

    with(squareCabin){
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")    
        println("Floor area: ${floorArea()}")
        getRoom()
		getRoom()
    }
    
    with(roundHut){
        println("\nRound Hut\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")    
        println("Floor area: ${floorArea()}")
		getRoom()
        getRoom()
        println("Carpet size: ${calculateMaxCarpetSize()}")

    }

    with(roundTower) {
        println("\nRound Tower\n==========")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
        getRoom()
        getRoom()
        println("Carpet size: ${calculateMaxCarpetSize()}")


    }

}
```
