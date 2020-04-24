package com.example.pethome.ui.pet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pethome.Adapter.ViewPagerAdapter
import com.example.pethome.Data.Token
import com.example.pethome.R
import kotlinx.android.synthetic.main.fragment_pet.*

class PetFragment : Fragment() {

    private lateinit var viewModel: PetViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabAdapter = ViewPagerAdapter(childFragmentManager)
        tabAdapter.addFragment(DogPetFragment(), "DOG")
        tabAdapter.addFragment(CatPetFragment(), "CAT")
        petViewPager?.adapter = tabAdapter

        petTabs.setupWithViewPager(petViewPager)
    }


}