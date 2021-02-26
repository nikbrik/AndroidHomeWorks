package com.nikbrik.toolbars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            toast("navigation")
        }

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.app_bar_search -> {
                    true
                }
                else -> false
            }
        }

        val search = toolbar.menu.findItem(R.id.app_bar_search)
        search.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                toast("expand")
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                toast("collapse")
                return true
            }

        })

        val longContent = findViewById<EditText>(R.id.long_content)
        val actionSearchView = search.actionView as SearchView
        actionSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val start = longContent.text.indexOf(string = it, ignoreCase = true)
                    if (start != -1) {
                        longContent.setSelection(start, start + it.length)
                        longContent.requestFocus()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}