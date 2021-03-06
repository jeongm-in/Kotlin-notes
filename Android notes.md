Everything in this document is from Google's Android Basics in Kotlin Tutorial (https://developer.android.com/courses/android-basics-kotlin/)

# Unit 1
- every element is called a `View`: `TextView`, `ImageView`, etc 
- `ViewGroup` is a container that can hold other views: 
  - `ConstraintLayout`
    - `layout_height` to 0: as tall as the `ConstraintLayout` minus margins
- can edit the frontend components either by text (xml) or in GUI 
- `dp`: stands for _density-independent pixels_ 
- `sp`: measure of font size, _scalable pixels_
- `ImageView`
  - import `Drawables`: `View > Tool Windows > Resource Manager`, then `Import Drawables`
  - `scaleType`: different scale types for the image: `centerInside`, `fitStart`, etc...
- `ComponentTree`
  - can decide which components come frontmost and what not
- extracting strings 
  - `app > res > values > strings.xml` 
  - add as `<string name="name_of_the_string">String Value</string>
  - access as `R.string.name_of_the_string` in the code. (also `@string/name_of_the_string` in xml context)
- `Activity`: provides the window in which the app draws UI
  - `MainActivity` is the top level or first activity
  ```kotlin
  package com.example.example

  import androidx.appcompat.app.AppCompatActivity
  import android.os.Bundle

  class MainActivity : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
     }
  }

  ```
  - `onCreate` is called when the app is opened for the first time 
    - place to add `onClickListeners` to components
- accesse components via id: `R.id.id_of_component` in code 
- `findViewById(R.id.some_id)` to get handle to the components 
- `ImageView.setImageResource(R.drawable.image_id)`

# Unit 2: Layouts
- Can edit layouts entirely in `xml`
- Stuff like `androidx.constraintlayout.widget.ConstraintLayout` is there because some components are part of Android Jetpack
- Specify namespaces:
```
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
```
- `android:id="@+id/plain_text_input"`
- `app:layout_constraintStart_toStartOf="parent"`, `app:layout_constraintTop_toTopOf="parent"`
  - use `start` and `end` because some languages can be either Right To Left or Left To Right 

- `EditText`
  - `android:hint="Cost of Service"`
  - `android:inputType="numberDecimal"`
  - `binding.costOfService.text.toString()`
  - `val cost = stringInTextField.toDoubleOrNull()`
    - always remember to handle edge cases 
- `RadioGroup`
  - `android:orientation="vertical"`
  - `android:checkedButton="@id/option_twenty_percent"`
  - include `RadioButton` within
  - `val selectedId = binding.tipOptions.checkedRadioButtonId`
- `Switch` 
  - `android:checked="true"`
  - `val roundUp = binding.roundUpSwitch.isChecked`
- View Binding 
  - accessing UI elements through `findViewById` can be tedious
  - load components beforehand for quick access 
  - in `build.gradle`: in `android` section: add `buildFeatures{ viewBinding = true }`
  - inside Activity class:
    - `lateinit var binding: ActivityBinding`
    - lateinit: promise that the code will initialize the variable before using it
  - inside `onCreate()`:
    - `binding = ActivityMainBinding.inflate(layoutInflater)
    - `setContentView(binding.root)`
  - Then can access stuff through `binding.itemId` 
    - convert the name of XML file to camel case and add "Binding" to the end 
    - reference for each view is generated by removing underscores and converting the view name to camel case
 - Formatting values 
   - `val formattedTip = NumberFormat.getCurrencyInstance().format(tip)`
     - function to take care of currency formatting
   - `binding.tipResult.text = getString(R.string.tip_amount, formattedTip)`


 ## Changing the theme 
- Color: `#FF FF FF FF`
  - alpha, red, green, blue 
  - put them in `colors.xml`, access them as `@color/color_name` in xml 
- Theme: `app > res > values > themes.xml` (`values/themes.xml`)
  - specify which colors are used for each cases 
- https://material.io/resources/color/#!/?view.left=0&view.right=0
  - color picker 
- Dark Theme 
  - `themes.xml (night)` (`values-night/themes.xml`)
- Icon: adaptive icons
  - provide different versions of app icon to support multiple screen densities 
  - located in `mipmap` because some launchers can use different density to display the icons 

- RecyclerView
  - Designed to be efficient, because once the view goes outside the window, it can be reused for different data set 
    - item: data item of the list to display
    - adapter: takes data and prepares for RecyclerView to display
      - design pattern that adapts the data into something that can be used by RecyclerView 
      - in the example app, adapter takes an `Affirmation` instance from the list returned from `loadAffirmations` and turn it into list item view

    - ViewHolders: pool of views 
    1. create layout for items
    2. create ItemAdpter class that takes the `Affirmation` list
    3. create ViewHolder as a nested  class:
    4. override adapter methods 

    ```kotlin
      class ItemAdapter(
          private val context: Context,
          private val dataset: List<Affirmation>
      ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

          class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
              val textView: TextView = view.findViewById(R.id.item_title)
          }
      }
      
      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
          val adapterLayout = LayoutInflater.from(parent.context)
              .inflate(R.layout.list_item, parent, false)

          return ItemViewHolder(adapterLayout)
      }

      override fun getItemCount() = dataset.size
      
      override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
          val item = dataset[position]
          holder.textView.text =  context.resources.getString(item.stringResourceId)
      }

    ```
    5. Update in MainActivity
    ```kotlin
      class MainActivity : AppCompatActivity() {

          override fun onCreate(savedInstanceState: Bundle?) {
              super.onCreate(savedInstanceState)
              setContentView(R.layout.activity_main)

              // Initialize data.
              val myDataset = Datasource().loadAffirmations()

              val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
              recyclerView.adapter = ItemAdapter(this, myDataset)

              // Use this setting to improve performance if you know that changes
              // in content do not change the layout size of the RecyclerView
              recyclerView.setHasFixedSize(true)
          }
      }

    ```


  - set the layout manager of RecyclerView to be: `app:layoutManager="LinearLayoutManager"`
  - `android:scrollbars="vertical"`
  - 
- Packages
  - create different packages for different parts of code: ex) data and UI 
- Datasource
  - prepare data so that other components can receive the data 
- FrameLayout: simpler than ConstaintLayout 
- MaterialCardView: Can use instead of LinearLayout

# Unit 3 
## Intents
Intent: object representing some action to be performed. Describes the request, not the actual result.
- implicit: tell the system the type of action, and the system is responsible for figuring out how to fulfill the request
- explicit: highly specific, tell the activity that to be launched

### Explicit intent 
in `LetterAdapter.kt`: `onBindViewHolder()`
  ```
    holder.button.setOnClickListener{
      val context = holder.view.context
      val intent = Intent(context, DetailActivity::class.java)
      intent.putExtra("letter", holder.button.text.toString())
      context.startActivity(intent)
    }
  ```
Extra: piece of data to be passed with intent 


### Implicit intent
```kotlin
holder.button.setOnClickListener {
    val queryUrl: Uri = Uri.parse("${DetailActivity.SEARCH_PREFIX}${item}")
    val intent = Intent(Intent.ACTION_VIEW, queryUrl)
  context.startActivity(intent)

}

```
- `CATEGORY_APP_MAPS`: launching the maps app
- `CATEGORY_APP_EMAIL`: launching the email app
- `CATEGORY_APP_GALLERY`: launching the gallery (photos) app
- `ACTION_SET_ALARM`: setting an alarm in the background
- `ACTION_DIAL`: initiating a phone call

### DetailActivity
`val letterId = intent?.extras?.getString("letter").toString()`
- both `intent` and `extras` properties are nullable. 
- in order to safely access ths object, put `?` after the name.
- If the value is null, then app won't attempt to access the functions down the chain.

### companion
- companions are basically singletons that belong to that specific class. 
```kotlin
  companion object {
    const val LETTER = "letter"
  }
```

### onCreateOptionsMenu
```kotlin
override fun onCreateOptionsMenu(menu: Menu?): Boolean {
   menuInflater.inflate(R.menu.layout_menu, menu)

   val layoutButton = menu?.findItem(R.id.action_switch_layout)
   // Calls code to set the icon based on the LinearLayoutManager of the RecyclerView
   setIcon(layoutButton)

   return true
}

private fun setIcon(menuItem: MenuItem?) {
   if (menuItem == null)
       return

   // Set the drawable for the menu icon based on which LayoutManager is currently in use

   // An if-clause can be used on the right side of an assignment if all paths return a value.
   // The following code is equivalent to
   // if (isLinearLayoutManager)
   //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
   // else menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
   menuItem.icon =
       if (isLinearLayoutManager)
           ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
       else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
   return when (item.itemId) {
       R.id.action_switch_layout -> {
           // Sets isLinearLayoutManager (a Boolean) to the opposite value
           isLinearLayoutManager = !isLinearLayoutManager
           // Sets layout and icon
           chooseLayout()
           setIcon(item)

           return true
       }
       //  Otherwise, do nothing and use the core event handling

       // when clauses require that all possible paths be accounted for explicitly,
       //  for instance both the true and false cases if the value is a Boolean,
       //  or an else to catch all unhandled cases.
       else -> super.onOptionsItemSelected(item)
   }
}

```
