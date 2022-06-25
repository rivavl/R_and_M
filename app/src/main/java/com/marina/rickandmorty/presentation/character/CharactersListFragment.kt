package com.marina.rickandmorty.presentation.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marina.rickandmorty.DataSource
import com.marina.rickandmorty.R
import com.marina.rickandmorty.presentation.character.recycler_view.CharacterListAdapter

class CharactersListFragment : Fragment(R.layout.fragment_characters_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.char_fragment_recycler_view).apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = CharacterListAdapter().apply { submitList(DataSource.getCharacters()) }
        }
    }
}