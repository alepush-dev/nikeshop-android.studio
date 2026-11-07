package pe.isil.nikeshop.features.onboarding.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.isil.nikeshop.R

@Composable
fun OnBoardingScreen(
    onNextClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    val accentYellow = Color(0xFFFFC107)
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))

        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = "Nike Sneaker Onboarding",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        )
        Spacer(modifier = Modifier.weight(0.5f))

        Text(
            text = "Diseñadas para destacar",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(0.1f))

        Text(
            text = "Encuentra las zapatillas exclusivas que definen tu ritmo y elevan cada paso que das.",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.weight(0.8f))

        FloatingActionButton(
            onClick = {onNextClick()},
            containerColor = accentYellow,
            contentColor = Color.Black,
            shape = FloatingActionButtonDefaults.shape,
            modifier = Modifier.size(64.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Siguiente",
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}
@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingScreen()
}