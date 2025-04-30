import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.welledge.project.domain.model.ChartMode
import org.welledge.project.presentation.component.ChartFormDialog
import org.welledge.project.presentation.viewmodel.ChartViewModel
import org.welledge.project.ui.Jzy3DChart

@Composable
fun ChartScreen(
    viewModel: ChartViewModel = koinViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Main layout
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Chart display
                Box(
                    modifier = Modifier
                        .width(500.dp)
                        .height(400.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (!showDialog) {
                        Jzy3DChart(
                            equation = viewModel.input,
                            chartType = viewModel.chartType,
                            mainColor = viewModel.color,
                            chartMode = ChartMode.EQUATION,
                            trigger = viewModel.generateChartTrigger
                        )
                    }
                }

                // Button to open dialog
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Configure Chart")
                    }
                }
            }

            // Form dialog
            if (showDialog) {
                ChartFormDialog(
                    viewModel = viewModel,
                    onDismissRequest = { showDialog = false },
                    onConfirm = {
                        viewModel.triggerGenerateChart()
                        showDialog = false
                    }
                )
            }
        }
    }
}
