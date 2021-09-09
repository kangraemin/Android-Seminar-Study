package android.anjahyun.study.view

import android.anjahyun.study.base.BaseFragment
import android.anjahyun.study.databinding.FragmentLoginBinding
import android.anjahyun.study.viewmodel.LoginViewModel
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class LoginFragment: BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate), View.OnClickListener {

    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener(this)

        binding.etId.setText("delivery")
        binding.etPw.setText("dev_baemin")

        binding.btnLogin.setOnClickListener(this)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.loginResult.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(requireContext(), "로그인 완료",0).show()
                findNavController().popBackStack()
            }
            else Toast.makeText(requireContext(), "아이디 또는 비밀번호가 일치하지 않습니다",0).show()
        })

    }

    override fun onClick(v: View?) {
        when(v) {
            binding.btnClose -> findNavController().popBackStack()
            binding.btnLogin -> viewModel.login(binding.etId.text.toString(), binding.etPw.text.toString())
        }
    }

}