package com.marina.rickandmorty.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marina.rickandmorty.R
import com.marina.rickandmorty.presentation.character.list.CharactersListFragment
import com.marina.rickandmorty.presentation.episode.list.EpisodesListFragment
import com.marina.rickandmorty.presentation.location.list.LocationsListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchFragment(CharactersListFragment())
        findViewById<BottomNavigationView>(R.id.bottom_nav_bar).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.characters_menu -> {
                    launchFragment(CharactersListFragment())
                    true
                }

                R.id.locations_menu -> {
                    launchFragment(LocationsListFragment())
                    true
                }

                R.id.episodes_menu -> {
                    launchFragment(EpisodesListFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}