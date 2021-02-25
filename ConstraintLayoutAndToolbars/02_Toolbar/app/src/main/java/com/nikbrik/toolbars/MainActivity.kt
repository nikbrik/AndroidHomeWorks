package com.nikbrik.toolbars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
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
            Toast.makeText(this, "navigation", Toast.LENGTH_SHORT).show()
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
                TODO("Not yet implemented")
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                TODO("Not yet implemented")
            }

        })

        val longContent = findViewById<TextView>(R.id.long_content)
        val actionSearchView = search.actionView as SearchView
        actionSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (longContent.text.contains(it)) {
//                        longContent.setHint()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }
}