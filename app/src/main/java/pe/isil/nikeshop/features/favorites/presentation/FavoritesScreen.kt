package pe.isil.nikeshop.features.favorites.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.isil.nikeshop.core.desingsystems.theme.AccentYellow
import pe.isil.nikeshop.core.desingsystems.theme.CardBackground
import pe.isil.nikeshop.core.desingsystems.theme.DarkNavy
import pe.isil.nikeshop.features.home.data.InMemoryProductRepository
import pe.isil.nikeshop.features.home.domain.Product
import java.util.Locale

@Composable
fun FavoritesScreen(
    favorites: List<Product>,
    cartIds: Set<Int>,
    onAddToCart: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favoritos",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (favorites.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Aún no tienes productos favoritos", color = Color.Gray)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favorites, key = { it.id }) { product ->
                    FavoriteItem(
                        product = product,
                        inCart = product.id in cartIds,
                        onAddToCart = { onAddToCart(product.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun FavoriteItem(
    product: Product,
    inCart: Boolean,
    onAddToCart: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(product.image),
                contentDescription = product.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(90.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.brand, fontSize = 12.sp, color = Color.Gray)
                Text(
                    text = product.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = AccentYellow,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = product.rating.toString(), fontSize = 12.sp, color = Color.Gray)
                }
                Text(
                    text = String.format(Locale.US, "$ %.1f", product.price),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onAddToCart,
                    enabled = !inCart,
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkNavy,
                        contentColor = Color.White,
                        disabledContainerColor = Color(0xFF9CA3AF),
                        disabledContentColor = Color.White
                    ),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text(
                        text = if (inCart) "En el carrito" else "Agregar al carrito",
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    val products = InMemoryProductRepository().getProducts()
    FavoritesScreen(
        favorites = listOf(products[1]),
        cartIds = emptySet(),
        onAddToCart = {}
    )
}