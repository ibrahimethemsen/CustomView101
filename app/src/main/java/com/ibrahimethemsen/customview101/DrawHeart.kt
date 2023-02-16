package com.ibrahimethemsen.customview101

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes

private enum class HeartColor {
    START,
    SECOND,
    FINISH;

    fun next() = when (this) {
        START -> SECOND
        SECOND -> FINISH
        FINISH -> START
    }
}

class DrawHeart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val right = 300f
    private val left = 100f
    private val sweepAngle = 225f
    private val heartText = "Sevdiklerinizi Kırmayın"
    private var heightF: Float = 0.0f
    private var paintColor = HeartColor.START
    private var paintSecond = 0
    private var paintFinish = 0
    private val heartPaint = Paint().apply {
        isAntiAlias = true
    }
    private val heartTextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 20f * resources.displayMetrics.scaledDensity
    }
    private val heartPath = Path()

    init {
        isClickable = true
        context.withStyledAttributes(attrs, R.styleable.DrawHeart) {
            paintSecond = getColor(R.styleable.DrawHeart_secondColor, 0)
            paintFinish = getColor(R.styleable.DrawHeart_finishColor, 0)
        }
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true
        paintColor = paintColor.next()
        invalidate()
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        heightF = (h.toFloat() / 2)
        heartPath.apply {
            addArc(left, heightF - 100, right, heightF + 100f, -225f, sweepAngle)
            arcTo(
                left + 200f,
                heightF - 100,
                right + 200f,
                heightF + 100f,
                -180f,
                sweepAngle,
                false
            )
            lineTo(left + 200f, heightF + 240)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        when (paintColor) {
            HeartColor.START -> {
                heartPaint.color = Color.RED
                heartTextPaint.color = Color.RED
            }
            HeartColor.SECOND -> {
                heartPaint.color = paintSecond
                heartTextPaint.color = paintSecond
            }
            HeartColor.FINISH -> {
                heartPaint.color = paintFinish
                heartTextPaint.color = paintFinish
            }
        }
        canvas?.apply {
            drawPath(heartPath, heartPaint)
            drawText(heartText, right + 250, heightF + 100f, heartTextPaint)
        }
    }
}