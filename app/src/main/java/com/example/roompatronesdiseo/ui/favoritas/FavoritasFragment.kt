package com.example.roompatronesdiseo.ui.favoritas


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roompatronesdiseo.NuevaNotaDialogViewModel

import com.example.roompatronesdiseo.R
import com.example.roompatronesdiseo.adapter.MyNotaFavoritasRecyclerViewAdapter
import com.example.roompatronesdiseo.model.NotaEntity

class FavoritasFragment : Fragment() {

    private lateinit var viewModel: NuevaNotaDialogViewModel

    private var adapterNotas: MyNotaFavoritasRecyclerViewAdapter? = null
    private var recyclerView: RecyclerView? = null

    private var notaEntityList: List<NotaEntity>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(NuevaNotaDialogViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_favoritas, container, false)

        recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        recyclerView!!.setLayoutManager(LinearLayoutManager(context));

        adapterNotas = activity?.let { notaEntityList?.let { it1 -> MyNotaFavoritasRecyclerViewAdapter(it1, it) } }
        recyclerView!!.setAdapter(adapterNotas)

        lanzarViewModel()

        return root

    }

    fun lanzarViewModel(){

        viewModel.allFavoritasNotas.observe(this, object : Observer<List<NotaEntity>> {
            override fun onChanged(t: List<NotaEntity>?) {
                if (t != null) {
                    adapterNotas!!.setNuevasNotas(t)
                }
            }
        })

    }

}
