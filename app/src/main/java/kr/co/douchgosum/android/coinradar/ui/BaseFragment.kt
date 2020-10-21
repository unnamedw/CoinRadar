package kr.co.douchgosum.android.coinradar.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kr.co.douchgosum.android.coinradar.MainActivity
import kr.co.douchgosum.android.coinradar.R

abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar(view)
        Log.d("MyTag", this.javaClass.simpleName)
    }

    private fun initToolBar(view: View) {
        val navController = findNavController()
        val appBarConfiguration = (activity as MainActivity).appBarConfiguration
        val toolbarId = when (this.javaClass.simpleName) {
//            "HomeFragment" -> R.id.toolbar_home
            "NotificationFragment" -> R.id.toolbar_notification
            "NewsFragment" -> R.id.toolbar_news
            "SettingFragment" -> R.id.toolbar_setting
            else -> return
        }
        val toolbar = view.findViewById<Toolbar>(toolbarId)
        toolbar
            .setupWithNavController(navController, appBarConfiguration)
    }

    fun showToast(msg: String, length: Int = Toast.LENGTH_SHORT) {
        val toast = Toast.makeText(
            activity as MainActivity,
            msg,
            length
        )
        toast.show()
    }


}