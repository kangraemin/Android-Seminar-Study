package com.clonecodingbm.ui.customview

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatImageView
import com.clonecodingbm.R
import javax.inject.Inject

class RatioImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, @AttrRes defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    var ratio = 0f
        set(value) {
            field = value
            invalidate()
            requestLayout()
        }

    init {
        setupAttributes(context, attrs)
    }

    private fun setupAttributes(context: Context, attrs: AttributeSet?) {
        val obtainStyledAttributes =
            context.obtainStyledAttributes(attrs, R.styleable.RatioImageView)

        ratio = try {
            obtainStyledAttributes.getFloat(R.styleable.RatioImageView_ratio, 1f)
        } finally {
            obtainStyledAttributes.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, ((measuredWidth / ratio)).toInt())
    }
}