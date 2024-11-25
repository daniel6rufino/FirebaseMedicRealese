package com.example.firebasemedic.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Heart_plus: ImageVector
    get() {
        if (_Heart_plus != null) {
            return _Heart_plus!!
        }
        _Heart_plus = ImageVector.Builder(
            name = "Heart_plus",
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
                moveTo(440f, 840f)
                lineTo(313f, 726f)
                quadToRelative(-72f, -65f, -123.5f, -116f)
                reflectiveQuadToRelative(-85f, -96f)
                reflectiveQuadToRelative(-49f, -87f)
                reflectiveQuadTo(40f, 339f)
                quadToRelative(0f, -94f, 63f, -156.5f)
                reflectiveQuadTo(260f, 120f)
                quadToRelative(52f, 0f, 99f, 22f)
                reflectiveQuadToRelative(81f, 62f)
                quadToRelative(34f, -40f, 81f, -62f)
                reflectiveQuadToRelative(99f, -22f)
                quadToRelative(81f, 0f, 136f, 45.5f)
                reflectiveQuadTo(831f, 280f)
                horizontalLineToRelative(-85f)
                quadToRelative(-18f, -40f, -53f, -60f)
                reflectiveQuadToRelative(-73f, -20f)
                quadToRelative(-51f, 0f, -88f, 27.5f)
                reflectiveQuadTo(463f, 300f)
                horizontalLineToRelative(-46f)
                quadToRelative(-31f, -45f, -70.5f, -72.5f)
                reflectiveQuadTo(260f, 200f)
                quadToRelative(-57f, 0f, -98.5f, 39.5f)
                reflectiveQuadTo(120f, 339f)
                quadToRelative(0f, 33f, 14f, 67f)
                reflectiveQuadToRelative(50f, 78.5f)
                reflectiveQuadToRelative(98f, 104f)
                reflectiveQuadTo(440f, 732f)
                quadToRelative(26f, -23f, 61f, -53f)
                reflectiveQuadToRelative(56f, -50f)
                lineToRelative(9f, 9f)
                lineToRelative(19.5f, 19.5f)
                lineTo(605f, 677f)
                lineToRelative(9f, 9f)
                quadToRelative(-22f, 20f, -56f, 49.5f)
                reflectiveQuadTo(498f, 788f)
                close()
                moveToRelative(280f, -160f)
                verticalLineToRelative(-120f)
                horizontalLineTo(600f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(120f)
                verticalLineToRelative(-120f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(120f)
                horizontalLineToRelative(120f)
                verticalLineToRelative(80f)
                horizontalLineTo(800f)
                verticalLineToRelative(120f)
                close()
            }
        }.build()
        return _Heart_plus!!
    }

private var _Heart_plus: ImageVector? = null
