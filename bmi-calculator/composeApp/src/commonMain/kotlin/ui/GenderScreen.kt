package viewmodel.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Gender
import viewmodel.BmiViewModel

@Composable
fun GenderScreen(viewModel: BmiViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select Your Gender",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GenderCard(
                gender = Gender.MALE,
                isSelected = viewModel.uiState.gender == Gender.MALE,
                onSelect = { viewModel.setGender(Gender.MALE) }
            )

            GenderCard(
                gender = Gender.FEMALE,
                isSelected = viewModel.uiState.gender == Gender.FEMALE,
                onSelect = { viewModel.setGender(Gender.FEMALE) }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { viewModel.navigateToNextScreen() },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text("Next")
            Spacer(Modifier.width(8.dp))
            Icon(Icons.Default.ArrowForward, contentDescription = "Next")
        }
    }
}

@Composable
fun GenderCard(gender: Gender, isSelected: Boolean, onSelect: () -> Unit) {
    Card(
        modifier = Modifier
            .size(140.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onSelect() },

        backgroundColor = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Column(
                modifier = Modifier.size(64.dp)
                    .background(if (isSelected) Color.White else Color.Gray, shape = RoundedCornerShape(32.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (gender == Gender.MALE) "M" else "F",
                    color = if(isSelected) MaterialTheme.colors.primary else Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (gender == Gender.MALE) "Male" else "Female",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}