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
import com.example.pethome.Adapter.CatBreedAdapter
import com.example.pethome.Data.CatBreedData
import com.example.pethome.Data.CatBreedPic
import com.example.pethome.R
import kotlinx.android.synthetic.main.fragment_catbreed.*

class CatBreedFragment : Fragment() {

    private lateinit var viewModel: CatHomeViewModel
    private var breedList: ArrayList<CatBreedData> = ArrayList()
    private var breedPicList: ArrayList<CatBreedPic> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catbreed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CatHomeViewModel::class.java)

        val recyclerView = catbreed_recycler_view
        val adapter = this.context?.let { CatBreedAdapter(breedPicList, it) }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)

        viewModel.catBreedDataList.observe(this, Observer { datas ->
            breedList.clear()
            breedPicList.clear()
            breedList.addAll(datas)

            viewModel.catBreedPicList.observe(this, Observer { picDatas ->
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