package kr.co.douchgosum.android.coinradar.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.os.Handler
import android.util.AttributeSet
import android.view.View

class RadarView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val LOG = "RadarView"
    private val POINT_ARRAY_SIZE = 25
    private val fps = 100
    private var showCircles = true

    var latestPoint =
        arrayOfNulls<Point>(POINT_ARRAY_SIZE)
    var latestPaint =
        arrayOfNulls<Paint>(POINT_ARRAY_SIZE)
    var mHandler = Handler()
    var mTick: Runnable = object : Runnable {
        override fun run() {
            invalidate()
            mHandler.postDelayed(this, 1000 / fps.toLong())
        }
    }

    fun startAnimation() {
        mHandler.removeCallbacks(mTick)
        mHandler.post(mTick)
    }

    fun stopAnimation() {
        mHandler.removeCallbacks(mTick)
    }

    fun setShowCircles(showCircles: Boolean) {
        this.showCircles = showCircles
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //원 그릴 영역의 크기 구하기
        val width = width
        val height = height
        val r = Math.min(width, height)
        val i = r / 2 //최대 반지름
        val j = i - 1 //보정 반지름

        //레이더 원 그리기
        val localPaint = latestPaint[0] // GREEN
        if (showCircles) {
            canvas.drawCircle(i.toFloat(), i.toFloat(), j.toFloat(), localPaint!!)
            canvas.drawCircle(i.toFloat(), i.toFloat(), (j * 3 / 4).toFloat(), localPaint)
            canvas.drawCircle(i.toFloat(), i.toFloat(), (j * 2 / 4).toFloat(), localPaint)
            canvas.drawCircle(i.toFloat(), i.toFloat(), (j / 4).toFloat(), localPaint)
        }

        //레이더 막대 영역 구하기
        alpha -= 0.5f
        if (alpha < -360) alpha = 0f
        val angle = Math.toRadians(alpha.toDouble())
        val offsetX = (i + (i * Math.cos(angle)).toFloat()).toInt()
        val offsetY = (i - (i * Math.sin(angle)).toFloat()).toInt()
        latestPoint[0] = Point(offsetX, offsetY)
        for (x in POINT_ARRAY_SIZE - 1 downTo 1) {
            latestPoint[x] = latestPoint[x - 1]
        }
        val lines = 0
        for (x in 0 until POINT_ARRAY_SIZE) {
            val point = latestPoint[x]
            if (point != null) {
                canvas.drawLine(
                    i.toFloat(),
                    i.toFloat(),
                    point.x.toFloat(),
                    point.y.toFloat(),
                    latestPaint[x]!!
                )
                //                Paint tmpPaint = new Paint();
//                tmpPaint.setColor(Color.RED);
//                tmpPaint.setAntiAlias(true);
//                tmpPaint.setStyle(Paint.Style.STROKE);
//                tmpPaint.setStrokeWidth(1.0F);
//                tmpPaint.setAlpha(255);
//                canvas.drawPoint(point.x, point.y, tmpPaint);
            }
        }

//        for (Point p : latestPoint) if (p != null) lines++;
    }

    init {
        val localPaint = Paint()
        localPaint.color = Color.GREEN
        localPaint.isAntiAlias = true
        localPaint.style = Paint.Style.STROKE
        localPaint.strokeWidth = 1.0f
        localPaint.alpha = 0
        val alpha_step = 255 / POINT_ARRAY_SIZE
        for (i in latestPaint.indices) {
            latestPaint[i] = Paint(localPaint)
            latestPaint[i]!!.alpha = 255 - i * alpha_step
        }
        latestPoint[0] = Point()
    }
}