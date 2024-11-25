package com.example.firebasemedic.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Health_metrics: ImageVector
    get() {
        if (_Health_metrics != null) {
            return _Health_metrics!!
        }
        _Health_metrics = ImageVector.Builder(
            name = "Health_metrics",
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
                moveTo(280f, 880f)
                verticalLineToRelative(-200f)
                horizontalLineTo(80f)
                verticalLineToRelative(-400f)
                horizontalLineToRelative(200f)
                verticalLineToRelative(-200f)
                horizontalLineToRelative(400f)
                verticalLineToRelative(200f)
                horizontalLineToRelative(200f)
                verticalLineToRelative(400f)
                horizontalLineTo(680f)
                verticalLineToRelative(200f)
                close()
                moveTo(160f, 440f)
                horizontalLineToRelative(200f)
                quadToRelative(10f, 0f, 19f, 5f)
                reflectiveQuadToRelative(14f, 13f)
                lineToRelative(35f, 52f)
                lineToRelative(54f, -162f)
                quadToRelative(4f, -12f, 14.5f, -20f)
                reflectiveQuadToRelative(23.5f, -8f)
                quadToRelative(10f, 0f, 19f, 5f)
                reflectiveQuadToRelative(14f, 13f)
                lineToRelative(68f, 102f)
                horizontalLineToRelative(179f)
                verticalLineToRelative(-80f)
                horizontalLineTo(600f)
                verticalLineToRelative(-200f)
                horizontalLineTo(360f)
                verticalLineToRelative(200f)
                horizontalLineTo(160f)
                close()
                moveToRelative(200f, 360f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(-200f)
                horizontalLineToRelative(200f)
                verticalLineToRelative(-80f)
                horizontalLineTo(600f)
                quadToRelative(-10f, 0f, -19f, -5f)
                reflectiveQuadToRelative(-15f, -13f)
                lineToRelative(-34f, -52f)
                lineToRelative(-54f, 162f)
                quadToRelative(-4f, 12f, -15f, 20f)
                reflectiveQuadToRelative(-24f, 8f)
                quadToRelative(-10f, 0f, -19f, -5f)
                reflectiveQuadToRelative(-14f, -13f)
                lineToRelative(-68f, -102f)
                horizontalLineTo(160f)
                verticalLineToRelative(80f)
                horizontalLineToRelative(200f)
                close()
                moveToRelative(120f, -320f)
            }
        }.build()
        return _Health_metrics!!
    }

private var _Health_metrics: ImageVector? = null
