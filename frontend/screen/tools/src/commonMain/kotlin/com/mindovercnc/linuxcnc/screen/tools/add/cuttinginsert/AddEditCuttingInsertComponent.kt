package com.mindovercnc.linuxcnc.screen.tools.add.cuttinginsert

import com.mindovercnc.linuxcnc.screen.AppScreenComponent
import com.mindovercnc.linuxcnc.screen.tools.add.AddEditItemComponent
import com.mindovercnc.linuxcnc.tools.model.CuttingInsert
import com.mindovercnc.linuxcnc.tools.model.MadeOf
import ro.dragossusi.model.InsertClearance
import ro.dragossusi.model.InsertShape
import ro.dragossusi.model.MountingAndChipBreaker
import ro.dragossusi.model.ToleranceClass

interface AddEditCuttingInsertComponent :
    AppScreenComponent<AddEditCuttingInsertState>, AddEditItemComponent<CuttingInsert> {
    fun setMadeOf(value: MadeOf)
    fun setInsertShape(value: InsertShape)
    fun setInsertClearance(value: InsertClearance)
    fun setToleranceClass(value: ToleranceClass)
    fun setMountingAndChipBreaker(value: MountingAndChipBreaker)
    fun setTipAngle(value: Int)
    fun setTipRadius(value: Double)
    fun setSize(value: Double)
    fun reloadFeedsAndSpeeds()
}
