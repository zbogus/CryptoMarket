package com.zahitbogus.cryptocrazyapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.zahitbogus.cryptocrazyapp.model.Crypto
import com.zahitbogus.cryptocrazyapp.util.Resource
import com.zahitbogus.cryptocrazyapp.viewmodel.CryptoDetailViewModel

@Composable
fun CryptoDetailScreen(
    id: String,
    price: String,
    navController: NavController,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {
    // Değişkeni hatasız mutableStateOf kullanımı ile tanımlıyoruz
    var cryptoItem = remember { mutableStateOf<Resource<Crypto>>(Resource.Loading()) }

    // LaunchedEffect kullanarak coroutine'i başlatıyoruz
    LaunchedEffect(key1 = id) {
        cryptoItem.value = viewModel.getCrypto(id)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when (val crypto = cryptoItem.value) {
                is Resource.Success -> {
                    val selectedCrypto = crypto.data!![0]
                    Text(
                        text = selectedCrypto.name,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    Image(
                        painter = rememberImagePainter(data = selectedCrypto.logo_url),
                        contentDescription = selectedCrypto.name,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(200.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )

                    Text(
                        text = price,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        textAlign = TextAlign.Center
                    )
                }

                is Resource.Error -> {
                    Text(text = crypto.message ?: "Error")
                }

                is Resource.Loading -> {
                    Text(text = "Loading")
                }
            }
        }
    }
}
