package com.example.pethome.ui.pet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.pethome.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pethome.Adapter.CatPetAdapter
import com.example.pethome.Data.Animal
import com.example.pethome.Data.Animals
import kotlinx.android.synthetic.main.fragment_catpet.*

class CatPetFragment : Fragment() {

    private lateinit var viewModel: PetViewModel
    private var animalList: ArrayList<Animals> = ArrayList()
    private var storeList: ArrayList<Animals> = ArrayList()
    private lateinit var searchType: Array<String>
    private var searchWay: String  = "Breed"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catpet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PetViewModel::class.java)

        val recyclerView = catpet_recycler_view
        val adapter = this.context?.let { CatPetAdapter(animalList, it) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)

        viewModel.token.observe(this, Observer { datas ->
            val param = "Bearer "+datas.access_token
            viewModel.animal.observe(this, Observer { datas ->
                animalList.clear()
                animalList.addAll(datas.animals)
                adapter?.notifyDataSetChanged()
            })
            viewModel.getTypeCat(param)
        })
        viewModel.getToken()

        searchType = resources.getStringArray(R.array.pet_searches)
        val spinnerAdapter =
            this.context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, searchType) }

        petSpinner.adapter = spinnerAdapter

        petSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                searchWay = searchType[position]
                catPetSearch.hint = "Search Pet By "+searchType[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                searchWay = "Breed"
                catPetSearch.hint = "Search Pet"
            }
        }

        catPetSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (searchWay.equals("Breed")){
                    val breed = catPetSearch.text.toString()
                    if (breed != "") {
                        var tempList: ArrayList<Animals> = ArrayList()
                        for (animal in storeList){
                            if (animal.breeds.primary.indexOf(breed) != -1){
                                tempList.add(animal)
                            }
                        }
                        animalList.clear()
                        animalList.addAll(tempList)
                        adapter?.notifyDataSetChanged()
                    } else{
                        viewModel.getToken()
                    }
                } else {
                    val name = catPetSearch.text.toString()
                    if (name != "") {
                        var tempList: ArrayList<Animals> = ArrayList()
                        for (animal in storeList){
                            if (animal.name.indexOf(name) != -1){
                                tempList.add(animal)
                            }
                        }
                        animalList.clear()
                        animalList.addAll(tempList)
                        adapter?.notifyDataSetChanged()
                    } else{
                        viewModel.getToken()
                    }
                }

                val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                true
            }
            false
        }
    }
}