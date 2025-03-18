package viewmodel.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.BmiCategory
import viewmodel.BmiViewModel

@Composable
fun ResultScreen(viewModel: BmiViewModel) {
    val bmiValue = viewModel.uiState.bmiValue
    val bmiCategory = viewModel.uiState.bmiCategory

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your BMI Result",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier.size(160.dp)
                .background(getCategoryColor(bmiCategory).copy(alpha = 0.2f), shape = RoundedCornerShape(80.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = String.format("%.1f", bmiValue),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = getCategoryColor(bmiCategory)
                )

                Text(
                    text = "BMI",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = getCategoryColor(bmiCategory).copy(alpha = 0.1f),
            elevation = 0.dp
        ) {
            Text(
                text = getCategoryName(bmiCategory),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = getCategoryColor(bmiCategory),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        UserInfoCard(viewModel)

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { viewModel.navigateToPreviousScreen() },
                modifier = Modifier.weight(1f).height(56.dp).padding(end = 8.dp)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                Spacer(Modifier.width(8.dp))
                Text("Back")
            }

            Button(
                onClick = { viewModel.resetCalculator() },
                modifier = Modifier.weight(1f).height(56.dp).padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
            ) {
                Text("Start Over")
                Spacer(Modifier.width(8.dp))
                Icon(Icons.Default.Refresh, contentDescription = "Start Over")
            }
        }
    }
}

@Composable
fun UserInfoCard(viewModel: BmiViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Your Information",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Gender", fontWeight = FontWeight.Medium)
                Text(
                    if (viewModel.uiState.gender.name == "MALE") "Male" else "Female",
                    color = MaterialTheme.colors.primary
                )
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Height", fontWeight = FontWeight.Medium)
                Text(
                    "${viewModel.uiState.heightCm} cm",
                    color = MaterialTheme.colors.primary
                )

            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Weight", fontWeight = FontWeight.Medium)
                Text(
                    "${viewModel.uiState.weightKg} kg",
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

fun getCategoryColor(category: BmiCategory): Color = when(category) {
    BmiCategory.UNDERWEIGHT -> Color(0xFF2196F3)
    BmiCategory.NORMAL -> Color(0xFF4CAF50)
    BmiCategory.OVERWEIGHT -> Color(0xFFFFC107)
    BmiCategory.OBESE -> Color(0xFFF44336)
}

fun getCategoryName(category: BmiCategory): String = when(category) {
    BmiCategory.UNDERWEIGHT -> "Underweight"
    BmiCategory.NORMAL -> "Normal Weight"
    BmiCategory.OVERWEIGHT -> "Overweight"
    BmiCategory.OBESE -> "Obese"
}