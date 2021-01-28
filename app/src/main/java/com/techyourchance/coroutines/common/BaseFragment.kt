package com.techyourchance.coroutines.common

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.techyourchance.coroutines.MainActivity

abstract class BaseFragment : Fragment() {

    protected open val screenTitle = ""

    protected val compositionRoot get() = (requireActivity() as MainActivity).compositionRoot

    protected lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screensNavigator = compositionRoot.screensNavigator
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toolbarManipulator = compositionRoot.toolbarManipulator
        toolbarManipulator.setScreenTitle(screenTitle)
        if (screensNavigator.isRootScreen()) {
            toolbarManipulator.hideUpButton()
        } else {
            toolbarManipulator.showUpButton()
        }
    }

    fun hideKeyboard(view: View){
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}