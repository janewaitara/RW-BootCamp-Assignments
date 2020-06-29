package com.janewaitara.movieapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            view?.let {
                if (validateUserName() && validatePassword()){
                    it.findNavController().navigate(R.id.action_loginFragment_to_movieListFragment)
                }
            }
        }
    }

    private fun validatePassword(): Boolean{
        val loginPassword = login_passwordWrapper.editText!!.text.toString().trim()

        return when {
            loginPassword.isEmpty() -> {
                login_passwordWrapper.error = "Password can not be empty"
                false
            }
            loginPassword.length < 6 -> {
                login_passwordWrapper.error = "Password should be greater than 6"
                false
            }
            else -> {
                login_passwordWrapper.isErrorEnabled = false
                true
            }
        }
    }
    private fun validateUserName(): Boolean{
        val loginUserName = login_userNameWrapper.editText!!.text.toString().trim()

        return when {
            loginUserName.isEmpty() -> {
                login_userNameWrapper.error = "User name can not be empty"
                false
            }
            loginUserName.length < 3 -> {
                login_userNameWrapper.error = "Enter a valid user name"
                false
            }
            else -> {
                login_userNameWrapper.isErrorEnabled = false
                true
            }
        }
    }


    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {

        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()

    }
}
