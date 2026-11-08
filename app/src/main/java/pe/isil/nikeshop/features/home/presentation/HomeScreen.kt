package pe.isil.nikeshop.features.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.isil.nikeshop.R
import pe.isil.nikeshop.core.desingsystems.theme.AccentYellow
import pe.isil.nikeshop.core.desingsystems.theme.DarkNavy

@Composable
fun HomeScreen(
    state: HomeState,
    onFavoriteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    ProductList(
        products = state.products,
        favoriteIds = state.favoriteIds,
        onFavoriteClick = onFavoriteClick,
        modifier = modifier.fillMaxSize(),
        header = {
            Column {
                HomeHeader()
                Spacer(modifier = Modifier.height(24.dp))
                PromoBanner()
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    )
}

@Composable
private fun HomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.user),
                contentDescription = "perfil",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(AccentYellow),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = "Welcome back", fontSize = 14.sp, color = Color.Black)
                Text(
                    text = "Jon Doe",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(48.dp)
                .background(Color.White, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notificaciones",
                tint = Color.Black
            )
        }
    }
}

@Composable
private fun PromoBanner() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = DarkNavy)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.6f)) {
                Text(
                    text = "Mega oferta\nexclusiva",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = 28.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Hasta 50% dscto",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = AccentYellow
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = AccentYellow),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Text(
                        text = "Comprar ahora",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
            Image(
                painter = painterResource(R.drawable.banner),
                contentDescription = "zapatilla",
                modifier = Modifier
                    .size(130.dp)
                    .align(Alignment.CenterEnd),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState(products = InMemoryProductRepository().getProducts()),
        onFavoriteClick = {}
    )
}