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
    lateinit var parentActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentActivity = activity as MainActivity
        initToolBar(view)
        Log.d("MyTag", this.javaClass.simpleName)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun initToolBar(view: View) {
        val navController = findNavController()
        val appBarConfiguration = parentActivity.appBarConfiguration
        val toolbarId = when (this.javaClass.simpleName) {
//            "HomeFragment" -> R.id.toolbar_home
            "NotificationFragment" -> R.id.toolbar_notification
            "CommunityFragment" -> R.id.toolbar_news
            "InfoFragment" -> R.id.toolbar_setting
            else -> return
        }
        val toolbar = view.findViewById<Toolbar>(toolbarId)
        toolbar
            .setupWithNavController(navController, appBarConfiguration)
    }

    fun setBottomNavigationVisibility(visibilityMode: Int) {
        if (visibilityMode==View.VISIBLE || visibilityMode==View.INVISIBLE) {
            parentActivity.bottomNav.apply {
                visibility = visibilityMode
            }
        }
    }


}