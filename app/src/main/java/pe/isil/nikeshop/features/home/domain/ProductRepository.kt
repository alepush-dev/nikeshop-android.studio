package pe.isil.nikeshop.features.home.domain

interface ProductRepository {
    fun getProducts(): List<Product>
}