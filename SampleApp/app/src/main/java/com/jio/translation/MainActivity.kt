package com.jio.translation

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jio.jiotranslationuikit.JioTranslationTokenExpireInterface
import com.jio.jiotranslationuikit.JioTranslationUiKit
import com.jio.jiotranslationuikit.ui.main.MainScreen
import com.jio.jiotranslationuikit.util.JWTUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val speechKey = ""
        val speechRegion = ""
        val textTranslationKey = ""
        val myJioSecToken = ""

        val jioTranslationUiKit = JioTranslationUiKit()
        jioTranslationUiKit.initializeModules(
            uiContext = applicationContext,
            speechKey = speechKey,
            speechRegion = speechRegion,
            textTranslationKey = textTranslationKey,
            onJioTranslationTokenExpire = object : JioTranslationTokenExpireInterface {
                override fun onTokenExpire() {
                    jioTranslationUiKit.refreshToken("")
                }
            })
        var myJioToken = ""
        if (myJioToken == null) {
            myJioToken = JWTUtil.getJwtToken("",myJioSecToken) ?: ""
        }
        setContent {
            MainScreen(myJioToken)
        }
    }
}