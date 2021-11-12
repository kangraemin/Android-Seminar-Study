package com.example.deliveryclonecoding.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.example.deliveryclonecoding.ui.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseViewModelFragment<SearchViewModel>() {

    private val searchViewModel: SearchViewModel by viewModels()

    override fun createViewModel(): SearchViewModel {
        return searchViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SearchView(searchViewModel)
            }
        }
    }
}

@Composable
fun SearchView(searchViewModel: SearchViewModel) {
    SearchViewDetail(searchAction = {
        searchViewModel.search(it)
    })
}

@Composable
fun SearchViewDetail(searchAction: (String) -> Unit) {

    var queryKeyword by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextField(
                        value = queryKeyword,
                        onValueChange = { queryKeyword = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(
                            onSearch = { searchAction(queryKeyword) }
                        ),
                        label = { Text(text = "검색어를 입력해 주세요") }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "To Home",
                            modifier = Modifier.height(50.dp)
                        )
                    }
                },
                modifier = Modifier.height(50.dp),
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        content = {

        }
    )
}

@Preview(showBackground = true, widthDp = 400, heightDp = 720)
@Composable
fun DefaultPreview() {
    SearchViewDetail(searchAction = {

    })
}

