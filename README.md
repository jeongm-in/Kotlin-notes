# Kotlin-notes
- 071821 initial commit with some notes for future edit
- 071821 add more code to the inheritance section, add syntax highlighting 
- 073021 add List, elaborate on new syntax, modify inheritance

# Some new syntax
## `repeat` 
  - `repeat(4){do()}` to call `do()` 4 times 
  
## `when` 
  - Pretty cool syntax. Think of switch cases in other languages. `->` operator to set the value of the variable depending on each condition
  - should specify `else` (or default) condition
	```kotlin
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

	```
## `with` 
  - Allows setting a scope for a variable 
  ```kotlin
    val myObject = myObject()
    println("prop_1: ${myObject.prop_1}")
    println("prop_2: ${myObject.prop_2}")
    println("prop_3: ${myObject.prop_3}")
  ```
  
  can be written as 
  
  ```kotlin
  val myObject = myObject()
  with(myObject){
    println("prop_1: ${prop_1}")
    println("prop_2: ${prop_2}")
    println("prop_3: ${prop_3}") 
  }
  ```
  
## Template literals
- Using variables in the string
  - Similar to the JavaScript template literal: `"value is ${var_name}"`
- Patterns like `%s` also exist

## `val` and `var`
- `val` for constants, `var` for variables

# Class inheritance
- Code from https://developer.android.com/codelabs/basic-android-kotlin-training-classes-and-inheritance
- `abstract`
  - same as in Java, `abstract` stuff must be implemented in children 
- `override` 
  - same as in Java`
- `open` 
  - In Kotlin, classes are by default final. In order to extend classes, the parent class must be `abstract` or marked with `open`.  
- Class constructors
- Sample code:
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


# List and Mutable List 
- List is immutable in Kotlin 
  - `val numbers = List<Int> = listOf(1, 2, 3)`
- Access list members using `[]` 
  - but what if that index is invalid? In Java, you would generally use `.get` method which would return null value. 
  - You'd get `ArrayIndexOutOfBoundsException`.. 
- `.first()` and `.last()` methods: return the element
- `.contains(val)` method: returns boolean
