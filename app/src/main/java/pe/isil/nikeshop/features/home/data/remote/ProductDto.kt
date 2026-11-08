package pe.isil.nikeshop.features.home.data.remote

data class ProductDto(
    val id: Int,
    val title: String,
    val brand: String,
    val price: Double,
    val description: String,
    val image: String,
    val category: String,
    val rating: Double,
    val stock: Int
)