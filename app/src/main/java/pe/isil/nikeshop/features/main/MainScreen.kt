package pe.isil.nikeshop.features.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pe.isil.nikeshop.core.desingsystems.theme.AccentYellow
import pe.isil.nikeshop.core.desingsystems.theme.ScreenBackground
import pe.isil.nikeshop.features.cart.presentation.CartScreen
import pe.isil.nikeshop.features.favorites.presentation.FavoritesScreen
import pe.isil.nikeshop.features.home.data.InMemoryProductRepository
import pe.isil.nikeshop.features.home.presentation.HomeScreen
import pe.isil.nikeshop.features.home.presentation.HomeState
import pe.isil.nikeshop.features.home.presentation.HomeViewModel

private data class BottomTab(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

private val tabs = listOf(
    BottomTab("Inicio", Icons.Filled.Home, Icons.Outlined.Home),
    BottomTab("Favoritos", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder),
    BottomTab("Carrito", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart)
)

@Composable
fun MainScreen(viewModel: HomeViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    MainContent(
        state = state,
        onFavoriteClick = viewModel::toggleFavorite,
        onAddToCart = viewModel::addToCart
    )
}

@Composable
private fun MainContent(
    state: HomeState,
    onFavoriteClick: (Int) -> Unit,
    onAddToCart: (Int) -> Unit
) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        containerColor = ScreenBackground,
        bottomBar = {
            NavigationBar(containerColor = ScreenBackground, tonalElevation = 0.dp) {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = {
                            Icon(
                                imageVector = if (selectedTab == index) tab.selectedIcon else tab.unselectedIcon,
                                contentDescription = tab.label
                            )
                        },
                        label = { Text(text = tab.label, fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = Color.Black,
                            unselectedIconColor = Color.Black,
                            unselectedTextColor = Color.Black,
                            indicatorColor = AccentYellow
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)

        when (selectedTab) {
            0 -> HomeScreen(
                state = state,
                onFavoriteClick = onFavoriteClick,
                modifier = contentModifier
            )
            1 -> FavoritesScreen(
                favorites = state.favorites,
                cartIds = state.cartIds,
                onAddToCart = onAddToCart,
                modifier = contentModifier
            )
            else -> CartScreen(
                products = state.cartProducts,
                total = state.total,
                modifier = contentModifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainContent(
        state = HomeState(products = InMemoryProductRepository().getProducts()),
        onFavoriteClick = {},
        onAddToCart = {}
    )
}