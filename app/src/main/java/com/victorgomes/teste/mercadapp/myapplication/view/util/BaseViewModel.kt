package com.victorgomes.teste.mercadapp.myapplication.view.util

import androidx.lifecycle.*
import com.victorgomes.teste.mercadapp.myapplication.data.util.tryGetFriendlyMessage
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected val _error: SingleLiveData<String> = SingleLiveData()
    val error: LiveData<String>
        get() = _error

    protected val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _loading

    protected fun setErrorValue(exception: Throwable) {
        _error.value = exception.tryGetFriendlyMessage(true)
    }

    protected fun runLoadingBlock(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                _loading.value = true
                block()
            } catch (e: Throwable) {
                logDebug(TAG, "Error launch: ", e);
                setErrorValue(e)
            } finally {
                _loading.value = false
            }
        }
    }
}
