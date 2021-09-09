import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class LoginFragment: BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate), View.OnClickListener {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener(this)

        binding.etId.setText("delivery")
        binding.etPw.setText("dev_baemin")

        binding.btnLogin.setOnClickListener(this)
    }
}