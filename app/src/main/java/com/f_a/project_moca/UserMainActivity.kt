package com.f_a.project_moca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.f_a.project_moca.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class UserMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)

        navigateFragment(MovieFragment())
        nav_bottom.setOnItemSelectedListener { id ->
            when(id){
                R.id.movie -> navigateFragment(MovieFragment())
                R.id.tv_show -> navigateFragment(TvShowFragment())
                R.id.favourite -> navigateFragment(FavouriteFragment())
                R.id.user -> navigateFragment(UserFragment())
            }
        }
    }

    private fun navigateFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
