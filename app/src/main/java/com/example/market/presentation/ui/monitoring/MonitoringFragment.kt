package com.example.market.presentation.ui.monitoring

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.market.R
import com.example.market.databinding.FragmentMonitoringBinding
import com.example.market.presentation.ui.main.MainFragment
import com.example.market.presentation.ui.monitoring.adapter.MonitoringAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MonitoringFragment:Fragment(R.layout.fragment_monitoring) {
    private lateinit var binding:FragmentMonitoringBinding
    private lateinit var adapter : MonitoringAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMonitoringBinding.bind(view)

//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Покупка"))
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Продажа"))

        val fragmentManager = requireActivity().supportFragmentManager
        adapter = MonitoringAdapter(fragmentManager,lifecycle)
        binding.viewPager.adapter = adapter


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
            MainFragment.BottomNavigationViewVisibilityLiveData.setVisibility(View.VISIBLE)
            MainFragment.FloatActionButtonVisibilityLiveData.setVisibility(View.VISIBLE)
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("TAG", "Fragment back pressed invoked")
                    // Do custom work here

                    // if you want onBackPressed() to be called as normal afterwards
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                        MainFragment.BottomNavigationViewVisibilityLiveData.setVisibility(View.VISIBLE)
                        MainFragment.FloatActionButtonVisibilityLiveData.setVisibility(View.VISIBLE)
                    }


                }
            }
            )

    }
}