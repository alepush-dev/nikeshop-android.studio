package pe.isil.nikeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pe.isil.nikeshop.core.desingsystems.theme.NikeShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NikeShopTheme(dynamicColor = false) {
                AppNavHost()
            }
        }
    }
}