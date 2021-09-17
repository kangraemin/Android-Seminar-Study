package com.dohyun.baeminapp.ui.utils

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.databinding.SocialLoginBinding

class CustomLoginButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var binding : SocialLoginBinding

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.social_login, this, true)
        binding = SocialLoginBinding.bind(view)
    }

    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginBtn)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginBtn, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val bgResId = typedArray.getResourceId(R.styleable.LoginBtn_bgColor, R.color.white)
        binding.socialLoginLayout.setBackgroundResource(bgResId)

        val imgResId = typedArray.getResourceId(R.styleable.LoginBtn_imgColor, R.drawable.ic_apple)
        binding.socialLoginSymbol.setBackgroundResource(imgResId)

        val textColor = typedArray.getColor(R.styleable.LoginBtn_textColor,0)
        binding.socialLoginTitle.setTextColor(textColor)

        val text = typedArray.getText(R.styleable.LoginBtn_text)
        binding.socialLoginTitle.text = text

        typedArray.recycle()
    }
}