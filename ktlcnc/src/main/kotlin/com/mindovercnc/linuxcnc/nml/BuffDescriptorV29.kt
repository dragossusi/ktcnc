package com.mindovercnc.linuxcnc.nml

import java.util.*

class BuffDescriptorV29 : BuffDescriptor {

  override val entries: Map<Key, DecodingInfo> = createMap()

  private fun createMap(): Map<Key, DecodingInfo> {
    val map: MutableMap<Key, DecodingInfo> = EnumMap(Key::class.java)

    // Mapping for the fields of EMC_TASK_STAT
    map[Key.TaskMode] = DecodingInfo(212, DecodingInfo.DataType.Integer)
    map[Key.TaskState] = DecodingInfo(216, DecodingInfo.DataType.Integer)
    map[Key.ExecState] = DecodingInfo(220, DecodingInfo.DataType.Integer)
    map[Key.InterpreterState] = DecodingInfo(224, DecodingInfo.DataType.Integer)
    map[Key.SubroutineCallLevel] = DecodingInfo(228, DecodingInfo.DataType.Integer)
    map[Key.MotionLine] = DecodingInfo(232, DecodingInfo.DataType.Integer)
    map[Key.CurrentLine] = DecodingInfo(236, DecodingInfo.DataType.Integer)
    map[Key.ReadLine] = DecodingInfo(240, DecodingInfo.DataType.Integer)
    map[Key.IsOptionalStop] = DecodingInfo(244, DecodingInfo.DataType.Byte)
    map[Key.IsBlockDelete] = DecodingInfo(245, DecodingInfo.DataType.Byte)
    map[Key.IsDigitalInTimeout] = DecodingInfo(246, DecodingInfo.DataType.Byte)
    map[Key.LoadedFilePath] = DecodingInfo(247, DecodingInfo.DataType.String)
    map[Key.Command] = DecodingInfo(502, DecodingInfo.DataType.String)
    map[Key.G5xOffsetXStart] = DecodingInfo(1016, DecodingInfo.DataType.Object)
    map[Key.G5xActiveIndex] = DecodingInfo(1088, DecodingInfo.DataType.Integer)
    map[Key.G92OffsetXStart] = DecodingInfo(1096, DecodingInfo.DataType.Object)
    map[Key.RotationXY] = DecodingInfo(1168, DecodingInfo.DataType.Double)
    map[Key.ToolOffsetXStart] = DecodingInfo(1176, DecodingInfo.DataType.Object)
    map[Key.ActiveGCodes] = DecodingInfo(1248, DecodingInfo.DataType.Object)
    map[Key.ActiveMCodes] = DecodingInfo(1316, DecodingInfo.DataType.Object)
    map[Key.ActiveSettings] =
      DecodingInfo(1360, DecodingInfo.DataType.Object) // 40 bytes -> 5 double values
    map[Key.ProgramUnits] = DecodingInfo(1400, DecodingInfo.DataType.Integer)
    map[Key.InterpreterErrorCode] = DecodingInfo(1404, DecodingInfo.DataType.Integer)
    map[Key.TaskPaused] = DecodingInfo(1408, DecodingInfo.DataType.Integer)
    map[Key.DelayLeft] = DecodingInfo(1416, DecodingInfo.DataType.Double)
    map[Key.QueuedMdiCommands] = DecodingInfo(1424, DecodingInfo.DataType.Integer)
    // Mapping for EMC_MOTION_STAT -> EMC_TRAJ_STAT
    map[Key.LinearUnits] = DecodingInfo(1648, DecodingInfo.DataType.Double)
    map[Key.AngularUnits] = DecodingInfo(1656, DecodingInfo.DataType.Double)
    map[Key.CycleTimeSeconds] = DecodingInfo(1664, DecodingInfo.DataType.Double)
    map[Key.JointsCount] = DecodingInfo(1672, DecodingInfo.DataType.Integer)
    map[Key.SpindlesCount] = DecodingInfo(1676, DecodingInfo.DataType.Integer)
    map[Key.AxisMask] = DecodingInfo(1684, DecodingInfo.DataType.Integer)
    map[Key.MotionMode] = DecodingInfo(1688, DecodingInfo.DataType.Integer)
    map[Key.IsEnabled] = DecodingInfo(1692, DecodingInfo.DataType.Byte)
    map[Key.IsInPosition] = DecodingInfo(1693, DecodingInfo.DataType.Byte)
    map[Key.PendingMotions] = DecodingInfo(1696, DecodingInfo.DataType.Integer)
    map[Key.ActiveMotions] = DecodingInfo(1700, DecodingInfo.DataType.Integer)
    map[Key.IsMotionQueueFull] = DecodingInfo(1704, DecodingInfo.DataType.Byte)
    map[Key.CurrentMotionId] = DecodingInfo(1708, DecodingInfo.DataType.Integer)
    map[Key.IsMotionPaused] = DecodingInfo(1712, DecodingInfo.DataType.Byte)
    map[Key.VelocityScaleFactor] = DecodingInfo(1720, DecodingInfo.DataType.Double)
    map[Key.RapidScaleFactor] = DecodingInfo(1728, DecodingInfo.DataType.Double)
    map[Key.CommandedPositionXStart] =
      DecodingInfo(1736, DecodingInfo.DataType.Object) // current commanded position
    map[Key.ActualPositionXStart] =
      DecodingInfo(1808, DecodingInfo.DataType.Object) // current actual position, from forward kins
    map[Key.SystemVelocity] = DecodingInfo(1880, DecodingInfo.DataType.Double)
    map[Key.SystemAcceleration] = DecodingInfo(1888, DecodingInfo.DataType.Double)
    map[Key.MaxVelocity] = DecodingInfo(1896, DecodingInfo.DataType.Double)
    map[Key.MaxAcceleration] = DecodingInfo(1904, DecodingInfo.DataType.Double)
    map[Key.ProbedPositionXStart] = DecodingInfo(1912, DecodingInfo.DataType.Object)
    map[Key.IsProbeTripped] = DecodingInfo(1984, DecodingInfo.DataType.Byte)
    map[Key.IsProbing] = DecodingInfo(1985, DecodingInfo.DataType.Byte)
    map[Key.ProbeInputValue] = DecodingInfo(1988, DecodingInfo.DataType.Integer)
    map[Key.KinematicsType] = DecodingInfo(1992, DecodingInfo.DataType.Integer)
    map[Key.MotionType] = DecodingInfo(1996, DecodingInfo.DataType.Integer)
    map[Key.CurrentMoveDtg] = DecodingInfo(2000, DecodingInfo.DataType.Double)
    map[Key.DtgPositionXStart] = DecodingInfo(2008, DecodingInfo.DataType.Object)
    map[Key.CurrentMoveVelocity] = DecodingInfo(2080, DecodingInfo.DataType.Double)
    map[Key.IsFeedOverrideEnabled] = DecodingInfo(2088, DecodingInfo.DataType.Byte)
    map[Key.IsAdaptiveFeedEnabled] = DecodingInfo(2089, DecodingInfo.DataType.Byte)
    map[Key.IsFeedHoldEnabled] = DecodingInfo(2090, DecodingInfo.DataType.Byte)
    map[Key.Joint0] = DecodingInfo(2168, DecodingInfo.DataType.Object)
    map[Key.Joint1] = DecodingInfo(2384, DecodingInfo.DataType.Object)
    map[Key.Joint0Type] = DecodingInfo(2276, DecodingInfo.DataType.Integer)
    map[Key.Joint0Units] = DecodingInfo(2280, DecodingInfo.DataType.Double)
    map[Key.Joint0Backlash] = DecodingInfo(2288, DecodingInfo.DataType.Double)
    map[Key.Joint0MinPositionLimit] = DecodingInfo(2296, DecodingInfo.DataType.Double)
    map[Key.Joint0MaxPositionLimit] = DecodingInfo(2304, DecodingInfo.DataType.Double)
    map[Key.Joint0MaxFollowingError] = DecodingInfo(2312, DecodingInfo.DataType.Double)
    map[Key.Joint0MinFollowingError] = DecodingInfo(2320, DecodingInfo.DataType.Double)
    map[Key.Joint0FollowingErrorCurrent] = DecodingInfo(2328, DecodingInfo.DataType.Double)
    map[Key.Joint0FollowingErrorHighMark] = DecodingInfo(2336, DecodingInfo.DataType.Double)
    map[Key.Joint0CommandedOutputPosition] = DecodingInfo(2344, DecodingInfo.DataType.Double)
    map[Key.Joint0CurrentInputPosition] = DecodingInfo(2352, DecodingInfo.DataType.Double)
    map[Key.Joint0CurrentVelocity] = DecodingInfo(2360, DecodingInfo.DataType.Double)
    map[Key.Joint0IsInPosition] = DecodingInfo(2368, DecodingInfo.DataType.Byte)
    map[Key.Joint0IsHoming] = DecodingInfo(2369, DecodingInfo.DataType.Byte)
    map[Key.Joint0IsHomed] = DecodingInfo(2370, DecodingInfo.DataType.Byte)
    map[Key.Joint0IsFaulted] = DecodingInfo(2371, DecodingInfo.DataType.Byte)
    map[Key.Joint0IsEnabled] = DecodingInfo(2372, DecodingInfo.DataType.Byte)
    map[Key.Joint0IsMinSoftLimitReached] = DecodingInfo(2373, DecodingInfo.DataType.Byte)
    map[Key.Joint0IsMaxSoftLimitReached] = DecodingInfo(2374, DecodingInfo.DataType.Byte)
    map[Key.Joint0IsMinHardLimitReached] = DecodingInfo(2375, DecodingInfo.DataType.Byte)
    map[Key.Joint0IsMaxHardLimitReached] = DecodingInfo(2376, DecodingInfo.DataType.Byte)
    map[Key.Joint0IsLimitOverrideOn] = DecodingInfo(2377, DecodingInfo.DataType.Byte)
    map[Key.Axis0] = DecodingInfo(5624, DecodingInfo.DataType.Object)
    map[Key.Axis1] = DecodingInfo(5760, DecodingInfo.DataType.Object)
    map[Key.Axis0MinPositionLimit] = DecodingInfo(5736, DecodingInfo.DataType.Double)
    map[Key.Axis0MaxPositionLimit] = DecodingInfo(5744, DecodingInfo.DataType.Double)
    map[Key.Axis0Velocity] = DecodingInfo(5752, DecodingInfo.DataType.Double)
    map[Key.Spindle0] = DecodingInfo(6848, DecodingInfo.DataType.Object)
    map[Key.Spindle1] = DecodingInfo(7016, DecodingInfo.DataType.Object)
    map[Key.Spindle0Speed] = DecodingInfo(6952, DecodingInfo.DataType.Double)
    map[Key.Spindle0Scale] = DecodingInfo(6960, DecodingInfo.DataType.Double)
    map[Key.Spindle0CssMaximum] = DecodingInfo(6968, DecodingInfo.DataType.Double)
    map[Key.Spindle0CssFactor] = DecodingInfo(6976, DecodingInfo.DataType.Double)
    map[Key.Spindle0State] = DecodingInfo(6984, DecodingInfo.DataType.Integer)
    map[Key.Spindle0Direction] = DecodingInfo(6988, DecodingInfo.DataType.Integer)
    map[Key.Spindle0Brake] = DecodingInfo(6992, DecodingInfo.DataType.Integer)
    map[Key.Spindle0Increasing] = DecodingInfo(6996, DecodingInfo.DataType.Integer)
    map[Key.Spindle0Enabled] = DecodingInfo(7000, DecodingInfo.DataType.Integer)
    map[Key.Spindle0OrientState] = DecodingInfo(7004, DecodingInfo.DataType.Integer)
    map[Key.Spindle0OrientFault] = DecodingInfo(7008, DecodingInfo.DataType.Integer)
    map[Key.Spindle0OverrideEnabled] = DecodingInfo(7012, DecodingInfo.DataType.Byte)
    map[Key.Spindle0Homed] = DecodingInfo(7013, DecodingInfo.DataType.Byte)
    map[Key.Motion64DigitalInputsInt] = DecodingInfo(8192, DecodingInfo.DataType.Object)
    map[Key.Motion64DigitalOutputsInt] = DecodingInfo(8448, DecodingInfo.DataType.Object)
    map[Key.Motion64AnalogInputsDouble] = DecodingInfo(8704, DecodingInfo.DataType.Object)
    map[Key.Motion64AnalogOutputsDouble] = DecodingInfo(9216, DecodingInfo.DataType.Object)
    map[Key.MotionDebug] = DecodingInfo(9984, DecodingInfo.DataType.Integer)
    map[Key.MotionOnSoftLimit] = DecodingInfo(9988, DecodingInfo.DataType.Integer)
    map[Key.ExternalOffsetsApplied] = DecodingInfo(9992, DecodingInfo.DataType.Integer)
    map[Key.ExternalOffsetsPositionXStart] = DecodingInfo(10000, DecodingInfo.DataType.Object)
    map[Key.NumExtraJoints] = DecodingInfo(10072, DecodingInfo.DataType.Integer)
    map[Key.IoCycleTime] = DecodingInfo(10192, DecodingInfo.DataType.Double)
    map[Key.IoDebug] = DecodingInfo(10200, DecodingInfo.DataType.Integer)
    map[Key.IoReason] = DecodingInfo(10204, DecodingInfo.DataType.Integer)
    map[Key.IoFaultDuringM6] = DecodingInfo(10208, DecodingInfo.DataType.Integer)
    map[Key.IoPocketPrepared] = DecodingInfo(10320, DecodingInfo.DataType.Integer)
    map[Key.IoLoadedTool] = DecodingInfo(10324, DecodingInfo.DataType.Integer)
    map[Key.IoCoolantMist] = DecodingInfo(10544, DecodingInfo.DataType.Integer)
    map[Key.IoCoolantFlood] = DecodingInfo(10548, DecodingInfo.DataType.Integer)
    map[Key.IoAuxEstop] = DecodingInfo(10656, DecodingInfo.DataType.Integer)
    map[Key.IoAuxLubeOn] = DecodingInfo(10768, DecodingInfo.DataType.Integer)
    map[Key.IoAuxLubeLevelOk] = DecodingInfo(10772, DecodingInfo.DataType.Integer)
    return map
  }
}
