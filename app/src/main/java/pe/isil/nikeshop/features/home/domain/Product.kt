package pe.isil.nikeshop.features.home.domain

import androidx.annotation.DrawableRes

data class Product(
    val id: Int,
    val name: String,
    val brand: String,
    val price: Double,
    @DrawableRes val image: Int,
    val rating: Double
)