package com.example.coinscreencap.shared.model

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData


fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, action: (t: T) -> Unit) {
    liveData?.observe(this) { t -> action(t) }
}

fun <T> Fragment.observe(liveData: LiveData<T>?, action: (t: T) -> Unit) {
    viewLifecycleOwner.observe(liveData, action)
}