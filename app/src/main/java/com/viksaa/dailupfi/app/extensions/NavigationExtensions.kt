package com.viksaa.dailupfi.app.extensions

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


/**
 * Inline fun to transition between Activities
 */
inline fun <reified T : AppCompatActivity> AppCompatActivity.launchActivity(bundle: Bundle? = null, finishOldActivity: Boolean = true) {
    startActivity(Intent(this, T::class.java).also {
        if (bundle != null) {
            it.putExtras(bundle)
        }
    })
    if (finishOldActivity) {
        this.finish()
    }
}


inline fun <reified T : Fragment>
        newFragmentInstance(bundle: Bundle? = null) =
        T::class.java.newInstance().apply {
            arguments = bundle
        }

/**
 *  Inline extension to do fragment transactions
 */
inline fun FragmentManager.inTransaction(addToBackstack: String? = null, func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.addToBackStack(addToBackstack)
    fragmentTransaction.commit()
}

/**
 * Add fragment and into given id keep stack if needed
 */
fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, addToBackstack: String? = null) {
    supportFragmentManager.inTransaction(addToBackstack) { add(frameId, fragment) }
}

/**
 * Replace the fragment into given id
 */
fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}


fun Fragment.requireAppCompatActivity() = requireActivity() as AppCompatActivity