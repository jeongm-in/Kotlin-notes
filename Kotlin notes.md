# Kotlin-notes
- 071821 initial commit with some notes for future edit
- 071821 add more code to the inheritance section, add syntax highlighting 
- 073021 add List, elaborate on new syntax, modify inheritance
- 073121 more List
- 080221 Collections: Set and Maps, Lambda
- 090721 Null safety


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

# List and Mutable List 
## List
- ![List Documentation](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/)
- List is immutable in Kotlin 
  - `val numbers: List<Int> = listOf(1, 2, 3)`
- Access list members using `[]` 
  - but what if that index is invalid? In Java, you would generally use `.get` method which would return null value. 
  - You'd get `ArrayIndexOutOfBoundsException`.. 
- `.first()` and `.last()` methods: return the element
- `.contains(val)` method: returns boolean
- `.reversed()` and `.sorted()`: does not affect the original list


## Mutable List
- `var numbers: MutableList<Int> = mutableListOf<Int>()`
- `.add(value)`: add one element
- `.addAll(List of variable)`: add multiple elements
	- Of course, type should always match
- `.remove(value)`: removes the first occurence
- `.removeAt(index)`
- `.clear()`: wipe clean

## Iteration
- supports `for (name in names){}` syntax!!
- More examples from [Google Android Tutorial: Use Lists in Kotlin](https://developer.android.com/codelabs/basic-android-kotlin-training-lists?authuser=1&continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-2-pathway-3%3Fauthuser%3D1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-lists#3)

```kotlin
for (item in list) print(item) // Iterate over items in a list

for (item in 'b'..'g') print(item) // Range of characters in an alphabet

for (item in 1..5) print(item) // Range of numbers

for (item in 5 downTo 1) print(item) // Going backward

for (item in 3..6 step 2) print(item) // Prints: 35
```

# Collections
## Set
- `setOf(values1, values2, ...)` and `mutableSetOf(values1, values2, ...)` 
- `aList.toSet()` to convert a list to set 
- set is not sorted (unlike C++ set)

## Map
```kotlin
val myMap = mutableMapOf<String, Int>(
	"A" to 1,
	"B" to 2,
	"C" to 3
)
```
- use `map.put(key, value)` method or do `map[key] = value`
## Methods for collections
- `forEach`: special identifier `it` is used
```kotlin
myMap.forEach{print("${it.key}: ${it.value}")}
```
- `map` method: transformation to each item in collection
```kotlin
println(myMap.map { "${it.key}: ${it.value}" }.joinToString(", ") )
// A: 1, B: 2, C: 3
```
  - note that `joinToString` can omit the last comma
- `filter`: apply filter to find items that match particular condition
```kotlin
val filteredValues = myMap.filter { it.value >= 2}
// {B=2, C=3}
```
  - returned type is `LinkedHashMap`

# Lambda
- curly braces that follow a function name without parenthesis
  - `forEach{}`, `map{}`, `filter{}` 
- must match the specified input and return types!
```kotlin
    val triple: (Int) -> Int = { a: Int -> a * 3 }
    val tripleShorthand: (Int) -> Int = { it * 3 }

    println(triple(5))
    println(tripleShorthand(5))
    
    peopleAges.sortedWith(str1:String, str2:String -> str1.length - str2.length)
```
- if input values of lambda function are unused, can replace with `_`

# Null safety in Kotlin 
Some variables can be null. In order to safely access such variables, use `?` 
- `intent?.extras?.getString("letter").toString()`
	- if `intent` is null, don't even attempt to access `extras`
	- if `extras` is null, don't even attempt to call `getString()` 
