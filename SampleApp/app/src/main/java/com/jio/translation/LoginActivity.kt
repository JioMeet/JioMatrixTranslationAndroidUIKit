package com.jio.translation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.jio.jiotranslationuikit.JioTranslateMainActivity
import com.jio.jiotranslationuikit.JioTranslationTokenExpireInterface
import com.jio.jiotranslationuikit.JioTranslationUiKit

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val phoneNumber = findViewById<EditText>(R.id.loginToken).text.toString()
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
            val myJioToken = JWTUtil.getJwtToken(phoneNumber, myJioSecToken) ?: ""
            val intent = Intent(this, JioTranslateMainActivity::class.java)
            intent.putExtra(JioTranslateMainActivity.MY_JIO_TOKEN, myJioToken)
            startActivity(intent)
            finish()
        }
    }
}