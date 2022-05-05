package com.news

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.news.databinding.ActivityMainBinding
import com.news.di.main.DaggerMainComponent
import com.news.di.main.MainInjector
import com.news.navigation.NavigatorBinder
import com.news.presentation.extensions.viewModelsFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navBinder: NavigatorBinder

    private val viewModel: MainViewModel by viewModelsFactory {
        MainViewModel(
            MainInjector(),
            DaggerMainComponent.builder()
                .appComponent(App.appComponent)
                .build()
        )
    }

    private val fragmentsCallback = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
            viewModel.inject(f)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.inject(this)
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentsCallback, true)

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        navBinder.bind(findNavController(R.id.nav_host_fragment))
    }

    override fun onPause() {
        navBinder.unbind()
        super.onPause()
    }

    override fun onDestroy() {
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentsCallback)
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }
}