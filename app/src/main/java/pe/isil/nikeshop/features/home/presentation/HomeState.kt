package pe.isil.nikeshop.features.home.presentation

import pe.isil.nikeshop.features.home.domain.Product

data class HomeState(
    val products: List<Product> = emptyList(),
    val favoriteIds: Set<Int> = emptySet(),
    val cartIds: Set<Int> = emptySet(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val favorites: List<Product>
        get() = products.filter { it.id in favoriteIds }

    val cartProducts: List<Product>
        get() = products.filter { it.id in cartIds }

    val total: Double
        get() = cartProducts.sumOf { it.price }
}