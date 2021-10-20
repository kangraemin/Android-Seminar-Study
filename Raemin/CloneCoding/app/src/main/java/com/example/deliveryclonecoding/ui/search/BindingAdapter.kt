package com.example.deliveryclonecoding.ui.search

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import io.reactivex.subjects.BehaviorSubject

@BindingAdapter("android:textChangesSubject")
fun bindTextChangeEvent(view: EditText, behaviorSubject: BehaviorSubject<String>) {
    view.addTextChangedListener {
        behaviorSubject.onNext(it.toString())
    }
}

interface ActionAfterTextChanged {
    fun doAfterTextChanged(text: String)
}

@BindingAdapter("android:textChangeEvent")
fun EditText.textChanges(listener: ActionAfterTextChanged) {
    doAfterTextChanged {
        listener.doAfterTextChanged(it.toString())
    }
}