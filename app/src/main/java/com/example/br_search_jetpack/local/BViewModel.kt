package com.example.br_search_jetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class BViewModel: ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    // private val _searchedTermHolder = MutableStateFlow(listOf<SearchedTermHolder>())
    private val _persons = MutableStateFlow(allSample)
    val persons = searchText
        .debounce(500L)
        /*.onEach { _isSearching.update { true } }*/
        .combine(_persons) { text, persons ->
            if (text.isBlank()) {
                persons
            } else {
                delay(2000L)
                persons.filter {
                    it.dosMatchingSearchQuery(text)
                }
            }

        }
        /*.onEach { _isSearching.update { false } }*/
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _persons.value)

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

}

data class Person(
    val firstName: String,
    val lastName: String,
) {
    fun dosMatchingSearchQuery(query: String): Boolean {
        val matching = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "${firstName.first()} ${lastName.first()}"

        )
        return matching.any{
            it.contains(query, true)
        }
    }
}


private val allSample = listOf(
    Person("a", "a"),
    Person("b", "b"),
    Person("c", "c"),
    Person("d", "d"),
    Person("e", "e"),
    Person("f", "f"),
    Person("g", "g")
)
