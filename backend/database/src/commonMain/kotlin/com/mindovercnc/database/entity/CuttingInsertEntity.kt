package com.mindovercnc.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mindovercnc.linuxcnc.tools.model.MadeOf

@Entity(tableName = "cutting_insert")
data class CuttingInsertEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "made_of") val madeOf: MadeOf,
    @ColumnInfo(name = "code") val code: String?,
    @ColumnInfo(name = "tip_radius") val tipRadius: Double,
    @ColumnInfo(name = "tip_angle") val tipAngle: Double,
    @ColumnInfo(name = "size") val size: Double,
)