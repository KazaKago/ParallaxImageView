package com.kazakago.parallaximageview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView

open class ParallaxImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    enum class Direction(val value: Int) {
        Forward(-1),
        Reverse(1),
    }

    companion object {
        const val defaultDistance = 200f
    }

    open var direction: Direction = Direction.Forward
    open var distance: Float = defaultDistance

    val nativeImageView: ImageView

    init {
        val rootView = LayoutInflater.from(context).inflate(R.layout.view_parallax_image, this)
        nativeImageView = rootView.findViewById(R.id.nativeImageView)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ParallaxImageView, defStyleAttr, 0)
        val imageResourceId = typedArray.getResourceId(R.styleable.ParallaxImageView_src, 0)
        val directionId = typedArray.getInt(R.styleable.ParallaxImageView_direction, 0)
        typedArray.recycle()

        nativeImageView.setImageResource(imageResourceId)
        if (directionId == 1) {
            direction = Direction.Reverse
        }

        viewTreeObserver.addOnPreDrawListener {
            dispatchParallax()
            true
        }
    }

    private fun dispatchParallax() {
        val getLocationInWindowPos = IntArray(2)
        getLocationInWindow(getLocationInWindowPos)

        val getWindowVisibleDisplayFrameRect = Rect()
        getWindowVisibleDisplayFrame(getWindowVisibleDisplayFrameRect)

        val visiblePercent = getLocationInWindowPos[1].toFloat() / (getWindowVisibleDisplayFrameRect.bottom - getWindowVisibleDisplayFrameRect.top).toFloat()
        val translationY = distance * (visiblePercent - 0.5f) * direction.value
        nativeImageView.translationY = translationY
    }

    public fun setImageBitmap(bitmap: Bitmap?) {
        nativeImageView.setImageBitmap(bitmap)
    }

    public fun setImageDrawable(drawable: Drawable?) {
        nativeImageView.setImageDrawable(drawable)
    }

    public fun setImageResource(@DrawableRes resId: Int) {
        nativeImageView.setImageResource(resId)
    }

    public fun setImageURI(uri: Uri?) {
        nativeImageView.setImageURI(uri)
    }

    public fun setImageLevel(level: Int) {
        nativeImageView.setImageLevel(level)
    }

    public fun setImageState(state: IntArray?, merge: Boolean) {
        nativeImageView.setImageState(state, merge)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    public fun setImageIcon(icon: Icon?) {
        nativeImageView.setImageIcon(icon)
    }

}