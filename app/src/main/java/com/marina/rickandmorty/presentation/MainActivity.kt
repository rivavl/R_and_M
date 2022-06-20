package com.marina.rickandmorty.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marina.rickandmorty.R
import com.marina.rickandmorty.presentation.fragments.CharactersListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, CharactersListFragment())
            .commit()
    }
}