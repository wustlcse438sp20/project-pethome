package com.example.pethome.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pethome.Adapter.DogBreedAdapter
import com.example.pethome.Data.DogBreedData
import com.example.pethome.Data.DogBreedPic
import com.example.pethome.R
import kotlinx.android.synthetic.main.fragment_dogbreed.*

class DogBreedFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var breedList: ArrayList<DogBreedData> = ArrayList()
    private var breedPicList: ArrayList<DogBreedPic> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dogbreed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val recyclerView = dogbreed_recycler_view
        val adapter = this.context?.let { DogBreedAdapter(breedPicList, it) }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)

        viewModel.dogBreedDataList.observe(this, Observer { datas ->
            breedList.clear()
            breedPicList.clear()
            breedList.addAll(datas)

            viewModel.dogBreedPicList.observe(this, Observer { picDatas ->
                breedPicList.addAll(picDatas)
                adapter?.notifyDataSetChanged()
            })
            for (breed in breedList){
                viewModel.getBreedPic(breed.id)
            }
        })
        viewModel.getBreeds()

    }
}