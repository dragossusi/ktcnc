package com.mindovercnc.linuxcnc.screen.tools.list.tabs.cuttinginsert.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mindovercnc.linuxcnc.screen.tools.add.cuttinginsert.AddEditCuttingInsertState
import com.mindovercnc.model.InsertClearance
import com.mindovercnc.model.InsertShape
import com.mindovercnc.model.MountingAndChipBreaker
import com.mindovercnc.model.ToleranceClass

@Composable
fun StandardInsert(
    state: AddEditCuttingInsertState,
    insertShapeChange: (InsertShape) -> Unit,
    insertClearanceChange: (InsertClearance) -> Unit,
    toleranceClassChange: (ToleranceClass) -> Unit,
    mountingChipBreakerChange: (MountingAndChipBreaker) -> Unit,
    modifier: Modifier = Modifier
) {
    val insertClearances = remember { InsertClearance.entries }
    val toleranceClasses = remember { ToleranceClass.entries }

    Row(modifier.padding(horizontal = 8.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        InsertShape(state, insertShapeChange, Modifier.weight(1f))
        InsertLetter(
            modifier = Modifier.weight(1f),
            items = insertClearances,
            dropDownWidth = 50.dp,
            nothingSelectedString = "--",
            selectedItem = state.insertClearance,
            onValueChanged = insertClearanceChange
        ) {
            Row(
                modifier = Modifier.width(50.dp).padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = it.name, style = MaterialTheme.typography.bodyMedium)
                Text(text = "${it.angle}°", style = MaterialTheme.typography.bodySmall)
            }
        }
        InsertLetter(
            modifier = Modifier.weight(1f),
            items = toleranceClasses,
            dropDownWidth = 50.dp,
            nothingSelectedString = "--",
            selectedItem = state.toleranceClass,
            onValueChanged = toleranceClassChange
        ) {
            Row(
                modifier = Modifier.width(50.dp).padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = it.name, style = MaterialTheme.typography.bodyMedium)
            }
        }
        MountingAndChipBreaker(state, mountingChipBreakerChange, Modifier.weight(1f))
    }
}

@Composable
private fun MountingAndChipBreaker(
    state: AddEditCuttingInsertState,
    mountingChipBreakerChange: (MountingAndChipBreaker) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = remember { MountingAndChipBreaker.entries }

    InsertLetter(
        modifier = modifier,
        items = items,
        dropDownWidth = 50.dp,
        nothingSelectedString = "--",
        selectedItem = state.mountingAndChipBreaker,
        onValueChanged = mountingChipBreakerChange
    ) {
        Row(
            modifier = Modifier.width(200.dp).padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(it.name, style = MaterialTheme.typography.bodyMedium)
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Mounting: ",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "${it.mountingType}", style = MaterialTheme.typography.bodySmall)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Chip Break: ",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "${it.chipBreaker}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
private fun InsertShape(
    state: AddEditCuttingInsertState,
    insertShapeChange: (InsertShape) -> Unit,
    modifier: Modifier
) {
    val items = remember { InsertShape.entries }
    InsertLetter(
        modifier = modifier,
        items = items,
        dropDownWidth = 50.dp,
        nothingSelectedString = "--",
        selectedItem = state.insertShape,
        onValueChanged = insertShapeChange
    ) { shape ->
        Row(
            modifier = Modifier.width(150.dp).padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = shape.name, style = MaterialTheme.typography.bodyMedium)
            val subhead = remember(shape) {
                buildString {
                    append(shape.shape)
                    shape.angle?.let { angle ->
                        append(" ${angle}°")
                    }
                }
            }
            Text(
                text = subhead,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
