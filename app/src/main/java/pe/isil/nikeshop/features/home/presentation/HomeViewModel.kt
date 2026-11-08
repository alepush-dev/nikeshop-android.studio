package pe.isil.nikeshop.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.isil.nikeshop.features.home.domain.GetProductsUseCase


class HomeViewModel(
    private val getProducts: GetProductsUseCase = GetProductsUseCase()
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val products = getProducts()
                _state.update { it.copy(products = products, isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = "No se pudo cargar los productos") }
            }
        }
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