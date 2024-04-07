package com.example.expensetrackerapp.data.model

enum class LoginStatus(val value: String) {
    NONE("none"),
    ONBOARDING("on_boarding"),
    NOT_LOGGED_IN("not_logged_in"),
    LOGGED_IN("logged_in"),
    EMAIL_REGISTER_VERIFICATION("email_register_verification"),
    FORGOT_PASSWORD("forgot_password");

    companion object {
        fun fromString(value: String): LoginStatus {
            return when (value) {
                LOGGED_IN.value -> LOGGED_IN
                NOT_LOGGED_IN.value -> NOT_LOGGED_IN
                else -> throw IllegalArgumentException("Invalid LoginStatus value: $value")
            }
        }
    }
}
