package com.example.anoji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.anoji.ui.theme.AnojiTheme
import androidx.lifecycle.lifecycleScope
import com.example.anoji.googleSign.GoogleAuthUiClient
import com.example.anoji.screen.SignIn
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

data class ItemData(val name: String, val imageResId: Int)

class MainActivity : ComponentActivity() {

    private val viewModel : ChatViewModel by viewModels()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            viewModel = viewModel,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnojiTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        val launcher =
                            rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = {
                                result ->
                                if(result.resultCode== RESULT_OK){
                                    lifecycleScope.launch {
                                    val signInResult= googleAuthUiClient.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                }
                                }
                            })
                        SignIn(onSignInClick = {
                            lifecycleScope.launch {
                                val signInIntentSender= googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntentSender ?: return@launch
                                    ).build()
                                )
                            }
                        })
                    }
            }
        }
    }
}















