package pe.isil.nikeshop.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pe.isil.nikeshop.features.home.domain.Product

@Composable
fun ProductList(
    products: List<Product>,
    favoriteIds: Set<Int>,
    onFavoriteClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            header()
        }
        items(products, key = { it.id }) { product ->
            ProductCard(
                product = product,
                isFavorite = product.id in favoriteIds,
                onFavoriteClick = { onFavoriteClick(product.id) }
            )
        }
    }
}