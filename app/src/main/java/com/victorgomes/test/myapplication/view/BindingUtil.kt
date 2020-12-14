package com.victorgomes.test.myapplication.view

import android.content.res.Resources
import android.graphics.Paint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.flexbox.FlexboxLayout
import com.victorgomes.test.myapplication.R
import com.victorgomes.test.myapplication.databinding.ItemButtonOutlinedBinding


@BindingAdapter("app:numberToString")
fun TextView.setText(number: Number?) {
    text = number?.toString() ?: ""
}

@BindingAdapter("app:defaultOrNoDescription")
fun TextView.setNoDescriptionText(text: String?) {
    this.text = if (text.isNullOrBlank()) "Sem descrição" else text
}

@BindingAdapter("app:listString")
fun TextView.setListString(list: List<String>?) {
    this.text = if (list.isNullOrEmpty()) "Sem descrição" else list.joinToString(separator = "\n")
}

@BindingAdapter("app:booleanToYesOrNo")
fun TextView.booleanToYesOrNo(state: Boolean?) {
    text = if (state == true) "Sim" else "Não"
}


@BindingAdapter("app:load_image")
fun ImageView.loadImage(src: String?) {
    Glide.with(this.context)
        .load("$src")
        .error(android.R.drawable.ic_dialog_alert)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(android.R.drawable.ic_menu_upload)
        .into(this)
}

@BindingAdapter("app:itemMargin")
fun RecyclerView.createCustomItemDecoration(space: Int?) {
    kotlin.runCatching {
        simpleDivider(this, space ?: 0)?.let { decoration ->
            this.addItemDecoration(decoration)
        }
    }
}

@BindingAdapter("app:striked")
fun TextView.strikeText(striked: Boolean) {
    if (striked) {
        paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }
}

@BindingAdapter("app:addSizes")
fun FlexboxLayout.addSizesViews(sizes: List<String>?) {
    kotlin.runCatching {
        removeAllViews()
        val margin8 = dpToPx(8)
        val params = FlexboxLayout.LayoutParams(
            FlexboxLayout.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(margin8, 0, margin8, 0)
        }
        sizes?.forEach { size ->
            val outlinedButton = DataBindingUtil.inflate<ItemButtonOutlinedBinding>(
                LayoutInflater.from(context), R.layout.item_button_outlined, this, false
            )
            outlinedButton.apply {
                button.text = size
            }
            addView(outlinedButton.root.apply {
                layoutParams = params
            })
        }
    }
}


fun simpleDivider(recyclerView: RecyclerView, margin: Int): CustomItemDecoration? {
    return runCatching {
        CustomItemDecoration(
            margin,
            (recyclerView.layoutManager as LinearLayoutManager?)!!.orientation
        )
    }.getOrNull()
}

class CustomItemDecoration(itemOffset: Int, private val mOrientation: Int) :
    ItemDecoration() {
    private val mItemOffset: Int
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (mOrientation == 1) {
            outRect.left = 0
            outRect.right = 0
            outRect.bottom = mItemOffset
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 0
            } else {
                outRect.top = mItemOffset
            }
        } else {
            outRect.top = 0
            outRect.bottom = 0
            outRect.right = mItemOffset
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = 0
            } else {
                outRect.left = mItemOffset
            }
        }
    }

    init {
        val value = if (itemOffset < 0) 0 else itemOffset
        val inPx: Int = dpToPx(value)
        mItemOffset = inPx
    }
}

fun dpToPx(value: Int): Int {
    return runCatching {
        val valueFloat = value.toFloat()
        val res = Resources.getSystem()
        return (valueFloat * res.displayMetrics.density).toInt()
    }.getOrElse {
        0
    }
}