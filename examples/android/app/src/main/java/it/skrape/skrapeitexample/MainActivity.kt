package it.skrape.skrapeitexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.extract
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.h3
import it.skrape.selects.html5.img
import it.skrape.selects.html5.li
import it.skrape.selects.html5.ol
import it.skrape.skrapeitexample.ui.HintText
import it.skrape.skrapeitexample.ui.SkrapeItLogo
import it.skrape.skrapeitexample.ui.StargazerTile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {

    private val stargazerViewModel by viewModels<StargazerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFFcae7f7),
                                    Color(0xFFeeb8e1)
                                )
                            )
                        )
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Button(
                                    onClick = { stargazerViewModel.updateUsers() },
                                ) {
                                    Text(
                                        text = """Who starred
                                    |skrape{it}?
                                """.trimMargin()
                                    )
                                }
                                Button(
                                    onClick = { stargazerViewModel.flush() },
                                    modifier = Modifier.padding(top = 16.dp)
                                ) {
                                    Text(
                                        text = "Reset"
                                    )
                                }
                            }
                            SkrapeItLogo()
                        }
                        StargazerScreen(stargazerViewModel)
                    }
                }
            }
        }
    }

}

@Composable
private fun StargazerScreen(stargazerViewModel: StargazerViewModel) {
    val items: List<User> by stargazerViewModel.users.observeAsState(listOf())
    if (items.isEmpty()) HintText() else LazyColumn {
        items(items) {
            StargazerTile(data = it)
        }
    }
}

data class User(
    val imageUrl: String,
    val name: String,
)

class StargazerViewModel : ViewModel() {

    private var _users: MutableLiveData<List<User>> = MutableLiveData(emptyList())
    val users: LiveData<List<User>> = _users

    fun updateUsers() {
        viewModelScope.launch {
            _users.postValue(fetch())
        }
    }

    fun flush() {
        _users.postValue(emptyList())
    }
}

private suspend fun fetch(): List<User> =
    withContext(Dispatchers.IO) {
        skrape(HttpFetcher) {
            request {
                url = "https://github.com/skrapeit/skrape.it/stargazers"
            }
            extract {
                htmlDocument {
                    ol {
                        withClass = "follow-list"
                        li {
                            findAll {
                                map {
                                    User(
                                        name = it.h3 {
                                            withClass = "follow-list-name"
                                            findFirst { text }
                                        },
                                        imageUrl = it.img {
                                            withClass = "avatar-user"
                                            findFirst { attribute("src") }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
