package com.example.br_search_jetpack.local

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.br_search_jetpack.ui.theme.BR_Search_jetpackTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.br_search_jetpack.BViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BR_Search_jetpackTheme {
                val viewModel = viewModel<BViewModel>()
                val searchText by viewModel.searchText.collectAsState()
                val persons by viewModel.persons.collectAsState()
                val isSearching by viewModel.isSearching.collectAsState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = searchText,
                        onValueChange = viewModel::onSearchTextChange,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "searching") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (isSearching) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    LazyColumn(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                    ) {

                        items(persons) { person ->
                            Text(text = "${person.firstName} ${person.lastName}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp))
                        }
                    }
                }
            }
        }
    }
}

