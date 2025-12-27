package com.mindovercnc.linuxcnc.domain.gcode

import ro.dragossusi.model.Point3D

interface GcodeCommandParseScope {
    var lastPoint: Point3D?
}
