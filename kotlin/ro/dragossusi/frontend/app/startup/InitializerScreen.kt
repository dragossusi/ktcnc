package startup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import initializer.Initializer
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import org.kodein.di.compose.rememberInstance

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun InitializerScreen(onInitialise: () -> Unit, modifier: Modifier = Modifier) {
    val initializer: Initializer by rememberInstance("app")
    val stepNumber = initializer.currentStepNumber.collectAsState().value
    val stepCount = initializer.stepCount

    LaunchedEffect(Unit) {
        initializer.initialise()

        async { delay(1000L) }.join()

        onInitialise()
    }

    Surface {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Initialising", style = MaterialTheme.typography.titleMedium)
            Text("Step $stepNumber/$stepCount")
            CircularWavyProgressIndicator()
        }
    }
}
