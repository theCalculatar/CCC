package com.example.medinow.login

import android.provider.ContactsContract.CommonDataKinds.Email

/**
 * Data validation state of the login form.
 */
data class RegisteringFormState (
    val firstNameError: Int? = null,
    val lastNameError: Int? = null,
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)