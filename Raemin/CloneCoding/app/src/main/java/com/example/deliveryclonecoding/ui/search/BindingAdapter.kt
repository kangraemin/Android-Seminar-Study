package com.example.deliveryclonecoding.ui.search

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import io.reactivex.subjects.BehaviorSubject

@BindingAdapter("android:textChangesSubject")
fun EditText.bindTextChangeEvent(behaviorSubject: BehaviorSubject<String>) {
    addTextChangedListener {
        behaviorSubject.onNext(it.toString())
    }
}

interface ActionSearchButtonClicked {
    fun actionAfterSearchButton()
}

@BindingAdapter("android:doOnImeOptionEvent")
fun EditText.bindImeOptionEvent(listener: ActionSearchButtonClicked) {
    setOnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            listener.actionAfterSearchButton()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
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