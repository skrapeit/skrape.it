package it.skrape.skrapeitexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.coil.rememberCoilPainter
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.extract
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.h3
import it.skrape.selects.html5.img
import it.skrape.selects.html5.li
import it.skrape.selects.html5.ol
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val stargazerViewModel by viewModels<StargazerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Background()
                    Column {
                        Button(onClick = { stargazerViewModel.updateUsers() }) {
                            Text(text = "load users")
                        }
                        StargazerScreen(stargazerViewModel)
                    }
                }
            }
        }
    }

}

@Composable
private fun Background() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background),
        contentDescription = "background",
        contentScale = ContentScale.Crop,
        alpha = .3f
    )
}

@Composable
private fun StargazerScreen(stargazerViewModel: StargazerViewModel) {
    val items: List<User> by stargazerViewModel.users.observeAsState(listOf())
    LazyColumn {
        items(items) {
            StargazerTile(data = it)
        }
    }
}

@Composable
internal fun StargazerTile(data: User) {
    Surface(
        color = Color.LightGray,
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                modifier = Modifier.align(Alignment.Start),
                painter = rememberCoilPainter(
                    request = data.imageUrl,
                    fadeIn = true
                ),
                contentDescription = ""
            )
            Text(
                text = data.name,
                modifier = Modifier.align(Alignment.End),
                style = MaterialTheme.typography.caption
            )
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
            // _users.postValue(fetch())
            _users.postValue(dummyData)
        }
    }
}

private suspend fun fetch(): List<User> =
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

private val dummyData = listOf(
    User("https://avatars.githubusercontent.com/u/3396272?s=180&v=4", "user 1"),
    User("https://avatars.githubusercontent.com/u/6132300?s=180&v=4", "user 2"),
    User("https://avatars.githubusercontent.com/u/32306780?s=180&v=4", "user 3"),
)



