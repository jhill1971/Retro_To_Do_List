package com.jameshill.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.util.SparseBooleanArray
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing the Array Lists, and the adapter
        var itemList = arrayListOf<String>()
        //An Adapter object acts as a bridge between an AdapterView and the underlying
        //data for that view. The Adapter provides access to the data items. The Adapter
        //is also responsible for making a View for each item in the data set.
        var adapter = ArrayAdapter<String>(
            this, //this = MainActivity
            android.R.layout.simple_list_item_multiple_choice, itemList
            //R.layout.row_layout,itemList //Experiment to change listView text attributes

        )

        //Add items to the list when the Add button is pressed.
        add.setOnClickListener {
            //add text to list as String
            itemList.add(editText.text.toString())
            //Lets the system know that the adapter for this listView is adapter (declared above).
            listView.adapter = adapter
            //Notifies the attached observers that the underlying data has
            //been changed and any View reflecting the data set should refresh itself.
            adapter.notifyDataSetChanged()
            editText.text.clear() //Clears text entry field after use.
        }

        //Clear all items on the list when the Clear button is pressed
        clear.setOnClickListener {
            itemList.clear()
            adapter.notifyDataSetChanged()
        }

        //Show the Toast message when an item on the list is selected.
        listView.setOnItemClickListener { adapterView, view, i, l ->

            android.widget.Toast.makeText(
                this, "You selected item --> " + itemList.get(i),
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }

        //Selecting and deleting the items from the list when the Delete button is pressed.
        delete.setOnClickListener {
            //Declare variables for this operation
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count //get count of items in list
            var item = count - 1 //compensates for zero indexing.
            val pass: Unit = Unit // Assign Unit to variable pass.

            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemList.get(item))
                    position.clear() //Clear indicated position
                    adapter.notifyDataSetChanged() //inform adapter
                    item-- //Decrement item numbers
                } else { pass }
            }


        }
    }

}