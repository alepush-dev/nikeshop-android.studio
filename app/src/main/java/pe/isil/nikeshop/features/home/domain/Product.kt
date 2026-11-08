package pe.isil.nikeshop.features.home.domain

data class Product(
    val id: Int,
    val name: String,
    val brand: String,
    val price: Double,
    val image: String,
    val rating: Double
)