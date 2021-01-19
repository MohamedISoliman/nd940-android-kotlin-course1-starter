package com.udacity.shoestore.login

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.DataManager

class LoginViewModel : ViewModel() {


    private val isValidCred = MutableLiveData<Boolean>()
    fun isValidCred(): LiveData<Boolean> = isValidCred


    fun signIn(email: String, password: String) {
        val notEmptyFields = email.isNotEmpty() && password.isNotEmpty()
        val validCred =
            notEmptyFields && email == DataManager.email && password == DataManager.password
        isValidCred.postValue(validCred)
    }

    fun signUp(email: String, password: String) {
        val notEmptyFields = email.isNotEmpty() && password.isNotEmpty()
        if (notEmptyFields && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            DataManager.email = email
            DataManager.password = password
            isValidCred.postValue(true)
        } else {
            isValidCred.postValue(false)
        }
    }
}