package pe.isil.nikeshop.features.home.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import pe.isil.nikeshop.features.home.data.InMemoryProductRepository
import pe.isil.nikeshop.features.home.domain.ProductRepository

class HomeViewModel(
    private val repository: ProductRepository = InMemoryProductRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        _state.update { it.copy(isLoading = true) }
        val products = repository.getProducts()
        _state.update { it.copy(products = products, isLoading = false) }
    }

    fun toggleFavorite(productId: Int) {
        _state.update { current ->
            val newFavorites = if (productId in current.favoriteIds) {
                current.favoriteIds - productId
            } else {
                current.favoriteIds + productId
            }
            current.copy(favoriteIds = newFavorites)
        }
    }

    fun addToCart(productId: Int) {
        _state.update { it.copy(cartIds = it.cartIds + productId) }
    }
}