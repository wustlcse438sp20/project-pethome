package com.example.pethome.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pethome.Adapter.ViewPagerAdapter
import com.example.pethome.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabAdapter = ViewPagerAdapter(childFragmentManager)
        tabAdapter.addFragment(DogBreedFragment(), "DOG")
        tabAdapter.addFragment(CatBreedFragment(), "CAT ")
        homeViewPager?.adapter = tabAdapter

        homeTabs.setupWithViewPager(homeViewPager)
    }
}