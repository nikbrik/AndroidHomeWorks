package com.skillbox.github.data

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://github.com/login/oauth/authorize"
    const val TOKEN_URI = "https://github.com/login/oauth/access_token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"

    const val CLIENT_ID = "f0a1c48468ed679db013"
    const val CLIENT_SECRET = "0ea5fd99dc5f0793da37c6f885aa4ce3d465a7aa"
    const val CALLBACK_URL = "skillbox://skillbox.ru/callback"
}
