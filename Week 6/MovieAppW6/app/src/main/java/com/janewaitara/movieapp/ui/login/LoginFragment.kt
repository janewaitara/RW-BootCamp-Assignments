package com.janewaitara.movieapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.janewaitara.movieapp.storage.MovieSharedPrefs
import com.janewaitara.movieapp.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var loginPrefs : MovieSharedPrefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginPrefs = MovieSharedPrefs()

        btn_login.setOnClickListener {
            lifecycleScope.launch {
                view?.let {
                    if (validateUserName() && validatePassword()){
                        it.findNavController().navigate(R.id.action_loginFragment_to_movieListFragment)
                        loginPrefs.setLoginStatus(true)
                    }
                }
            }
        }
    }

    /**
     * a fun to validate whether the login password is empty or shorter than expected.
     * Returns a Boolean
     * */
    private fun validatePassword(): Boolean{
        val loginPassword = login_passwordWrapper.editText!!.text.toString().trim()

        return when {
            loginPassword.isEmpty() -> {
                login_passwordWrapper.error = getString(R.string.password_empty_error)
                false
            }
            loginPassword.length < 6 -> {
                login_passwordWrapper.error = getString(R.string.password_length_error)
                false
            }
            else -> {
                login_passwordWrapper.isErrorEnabled = false
                true
            }
        }
    }

    /**
     * a fun to validate whether the login userName is empty or shorter than expected.
     * Returns a Boolean
     * */
    private fun validateUserName(): Boolean{
        val loginUserName = login_userNameWrapper.editText!!.text.toString().trim()

        return when {
            loginUserName.isEmpty() -> {
                login_userNameWrapper.error = getString(R.string.username_empty_error)
                false
            }
            loginUserName.length < 3 -> {
                login_userNameWrapper.error = getString(R.string.username_length_error)
                false
            }
            else -> {
                login_userNameWrapper.isErrorEnabled = false
                true
            }
        }
    }

}
