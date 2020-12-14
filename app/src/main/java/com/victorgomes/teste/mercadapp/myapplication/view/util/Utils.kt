package com.victorgomes.teste.mercadapp.myapplication.view.util

import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val Any.TAG: String
    get() {
        return javaClass.simpleName
    }

fun logDebug(tag: String, message: String, e: Throwable? = null) {
    runCatching {
        if (e == null) {
            Log.d(tag, message + "")
        } else {
            Log.d(tag, message + "", e)
        }
    }
}

fun <T> T?.valueOrDefault(default: T): T {
    return this ?: default
}

fun String?.valueOrNoDescritpion(default: String? = null): String {
    return this ?: (default ?: "Sem descrição")
}

suspend fun runInIo(block: suspend () -> Unit) {
    withContext(Dispatchers.IO) {
        block.invoke()
    }
}

suspend fun runInDefault(block: suspend () -> Unit) {
    withContext(Dispatchers.Default) {
        block.invoke()
    }
}

fun Fragment.setTitle(title: String = "") {
    kotlin.runCatching {
        this.activity?.let { activity ->
            if (activity is AppCompatActivity) {
                activity.supportActionBar?.let { toolbar ->
                    toolbar.title = title
                }
            }
        }
    }
}

fun <T> MutableLiveData<T>?.setValueSafe(valueSet: T?) {
    if (isMainThread()) {
        this?.value = valueSet
    } else {
        this?.postValue(valueSet)
    }
}

fun isMainThread(): Boolean = Looper.myLooper() == Looper.getMainLooper()

private lateinit var mToast: Toast

private fun stopToast() {
    kotlin.runCatching {
        if (::mToast.isInitialized) {
            mToast.cancel()
        }
    }
}

fun Context?.showLongToastMessage(message: String) {
    kotlin.runCatching {
        this.let {
            mToast = Toast.makeText(this, message, Toast.LENGTH_LONG).apply {
                stopToast()
                show()
            }
        }
    }
}

fun Context?.showShortToastMessage(message: String) {
    kotlin.runCatching {
        this.let {
            mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
                stopToast()
                show()
            }
        }
    }
}

fun Context.calculateNoOfColumns(): Int {
    val displayMetrics = resources.displayMetrics
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    return (dpWidth / 160).toInt()
}
