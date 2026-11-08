package pe.isil.nikeshop.features.home.domain

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}