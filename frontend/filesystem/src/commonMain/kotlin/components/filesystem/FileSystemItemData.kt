package components.filesystem

import androidx.compose.runtime.Stable
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Stable
data class FileSystemItemData(
    val title: String,
    val isDirectory: Boolean,
    val lastModified: Instant?,
    val path: String,
    val onClick: () -> Unit,
    val onCopy: () -> Unit,
)
