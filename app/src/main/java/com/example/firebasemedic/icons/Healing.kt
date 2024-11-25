package com.example.firebasemedic.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Healing: ImageVector
    get() {
        if (_Healing != null) {
            return _Healing!!
        }
        _Healing = ImageVector.Builder(
            name = "Healing",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(480f, 706f)
                lineTo(330f, 856f)
                quadToRelative(-23f, 23f, -56f, 23f)
                reflectiveQuadToRelative(-56f, -23f)
                lineTo(104f, 742f)
                quadToRelative(-23f, -23f, -23f, -56f)
                reflectiveQuadToRelative(23f, -56f)
                lineToRelative(150f, -150f)
                lineToRelative(-150f, -150f)
                quadToRelative(-23f, -23f, -23f, -56f)
                reflectiveQuadToRelative(23f, -56f)
                lineToRelative(114f, -114f)
                quadToRelative(23f, -23f, 56f, -23f)
                reflectiveQuadToRelative(56f, 23f)
                lineToRelative(150f, 150f)
                lineToRelative(150f, -150f)
                quadToRelative(23f, -23f, 56f, -23f)
                reflectiveQuadToRelative(56f, 23f)
                lineToRelative(114f, 114f)
                quadToRelative(23f, 23f, 23f, 56f)
                reflectiveQuadToRelative(-23f, 56f)
                lineTo(706f, 480f)
                lineToRelative(150f, 150f)
                quadToRelative(23f, 23f, 23f, 56f)
                reflectiveQuadToRelative(-23f, 56f)
                lineTo(742f, 856f)
                quadToRelative(-23f, 23f, -56f, 23f)
                reflectiveQuadToRelative(-56f, -23f)
                close()
                moveToRelative(0f, -266f)
                quadToRelative(17f, 0f, 28.5f, -11.5f)
                reflectiveQuadTo(520f, 400f)
                reflectiveQuadToRelative(-11.5f, -28.5f)
                reflectiveQuadTo(480f, 360f)
                reflectiveQuadToRelative(-28.5f, 11.5f)
                reflectiveQuadTo(440f, 400f)
                reflectiveQuadToRelative(11.5f, 28.5f)
                reflectiveQuadTo(480f, 440f)
                moveToRelative(-170f, -16f)
                lineToRelative(114f, -114f)
                lineToRelative(-150f, -150f)
                lineToRelative(-114f, 114f)
                close()
                moveToRelative(90f, 96f)
                quadToRelative(17f, 0f, 28.5f, -11.5f)
                reflectiveQuadTo(440f, 480f)
                reflectiveQuadToRelative(-11.5f, -28.5f)
                reflectiveQuadTo(400f, 440f)
                reflectiveQuadToRelative(-28.5f, 11.5f)
                reflectiveQuadTo(360f, 480f)
                reflectiveQuadToRelative(11.5f, 28.5f)
                reflectiveQuadTo(400f, 520f)
                moveToRelative(80f, 80f)
                quadToRelative(17f, 0f, 28.5f, -11.5f)
                reflectiveQuadTo(520f, 560f)
                reflectiveQuadToRelative(-11.5f, -28.5f)
                reflectiveQuadTo(480f, 520f)
                reflectiveQuadToRelative(-28.5f, 11.5f)
                reflectiveQuadTo(440f, 560f)
                reflectiveQuadToRelative(11.5f, 28.5f)
                reflectiveQuadTo(480f, 600f)
                moveToRelative(80f, -80f)
                quadToRelative(17f, 0f, 28.5f, -11.5f)
                reflectiveQuadTo(600f, 480f)
                reflectiveQuadToRelative(-11.5f, -28.5f)
                reflectiveQuadTo(560f, 440f)
                reflectiveQuadToRelative(-28.5f, 11.5f)
                reflectiveQuadTo(520f, 480f)
                reflectiveQuadToRelative(11.5f, 28.5f)
                reflectiveQuadTo(560f, 520f)
                moveToRelative(-24f, 130f)
                lineToRelative(150f, 150f)
                lineToRelative(114f, -114f)
                lineToRelative(-150f, -150f)
                close()
                moveToRelative(85f, -29f)
            }
        }.build()
        return _Healing!!
    }

private var _Healing: ImageVector? = null
