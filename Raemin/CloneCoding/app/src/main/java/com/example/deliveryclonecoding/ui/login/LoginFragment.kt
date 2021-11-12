package com.example.deliveryclonecoding.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deliveryclonecoding.R
import com.example.deliveryclonecoding.ui.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseViewModelFragment<LoginViewModel>() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun createViewModel(): LoginViewModel {
        return loginViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                LoginView(loginViewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.loginResult.observe(viewLifecycleOwner, { successToLogin ->
            Log.d("login result", "success to login : $successToLogin")
        })
    }
}

@Composable
private fun LoginEditText(textValue: String, hintValue: String = "", onValueChange: (String) -> Unit) {
    TextField(
        value = textValue,
        onValueChange = onValueChange,
        label = {
            Text(hintValue)
        }, modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
    )
}

@Composable
private fun LoginView(loginViewModel: LoginViewModel = hiltViewModel()) {
    LoginViewDetail(loginAction = { id, password ->
        loginViewModel.login(
            id = id,
            password = password
        )
    })
}

@Composable
private fun LoginViewDetail(loginAction: (String, String) -> Unit) {

    var emailText by remember { mutableStateOf("delivery") }
    var passwordText by remember { mutableStateOf("dev_baemin") }

    Surface(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 30.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginEditText(
                textValue = emailText,
                hintValue = "아이디 또는 이메일",
                onValueChange = { emailText = it })
            LoginEditText(
                textValue = passwordText,
                hintValue = "비밀번호",
                onValueChange = { passwordText = it })
            Button(
                onClick = { loginAction(emailText, passwordText) },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.bm_green)),
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "로그인",
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 720)
@Composable
private fun DefaultPreview() {
    LoginViewDetail { id, password ->

    }
}