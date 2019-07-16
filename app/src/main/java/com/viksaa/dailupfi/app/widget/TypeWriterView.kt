package com.viksaa.dailupfi.app.widget


import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.viksaa.dailupfi.app.extensions.then


class TypeWriterView : AppCompatTextView {

    companion object {
        const val DEFAULT_DELAY = 300L
        const val DEFAULT_INDEX = 0
    }

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


    private lateinit var typeWriterViewListener: TypeWriterViewListener
    private fun isTypeWriterViewListenerInitialized() = ::typeWriterViewListener.isInitialized
    private var typeWriterText: CharSequence? = null
    private var index: Int = DEFAULT_INDEX
    var delay: Long = DEFAULT_DELAY
    private val typeWriterAnimationHandler = Handler()
    private val characterAdder = object : Runnable {

        override fun run() {
            text = typeWriterText?.subSequence(0, index++)

            typeWriterText?.let {
                if (index <= it.length) {
                    typeWriterAnimationHandler.postDelayed(this, delay)
                } else {
                    notifyTypeWritingEnded()
                }
            }

        }
    }

    fun setTypeWriterViewListener(listener: TypeWriterViewListener) {
        typeWriterViewListener = listener
    }

    fun animateText(inputText: CharSequence) {
        typeWriterText = inputText
        index = DEFAULT_INDEX

        text = ""
        typeWriterAnimationHandler.removeCallbacks(characterAdder)
        typeWriterAnimationHandler.postDelayed(characterAdder, delay)
    }

    fun stopAnimating() {
        typeWriterText?.let {
            text = it
            typeWriterAnimationHandler.removeCallbacks(characterAdder)
        }
    }

    private fun notifyTypeWritingEnded() {
        if (isTypeWriterViewListenerInitialized()) {
            typeWriterViewListener.onTypeWritingEnd(id)
        }
    }

    fun getTotalAnimationDuration(): Long = (typeWriterText == null) then 0L
            ?: typeWriterText!!.length * delay
}