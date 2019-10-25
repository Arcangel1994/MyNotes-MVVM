package com.example.roompatronesdiseo.ui.nota

import android.app.ProgressDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roompatronesdiseo.NuevaNotaDialogViewModel

import com.example.roompatronesdiseo.R
import com.example.roompatronesdiseo.adapter.MyNotaRecyclerViewAdapter
import com.example.roompatronesdiseo.model.NotaEntity
import com.example.roompatronesdiseo.NuevaNotaDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NotaFragment : Fragment() {

    private lateinit var viewModel: NuevaNotaDialogViewModel

    private var adapterNotas: MyNotaRecyclerViewAdapter? = null
    private var recyclerView: RecyclerView? = null

    private var notaEntityList: List<NotaEntity>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(NuevaNotaDialogViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_nota, container, false)

        recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        recyclerView!!.setLayoutManager(LinearLayoutManager(context));

        adapterNotas = activity?.let { notaEntityList?.let { it1 -> MyNotaRecyclerViewAdapter(it1, it) } }
        recyclerView!!.setAdapter(adapterNotas)

        lanzarViewModel()

        return root
    }

    fun lanzarViewModel(){

        viewModel.allNotas.observe(this, object : Observer<List<NotaEntity>>{
            override fun onChanged(t: List<NotaEntity>?) {
                if (t != null) {
                    adapterNotas!!.setNuevasNotas(t)
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu_nota_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_add_nota -> {
                mostrarDialogoNuevaNota()
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarDialogoNuevaNota() {
        val fm = activity!!.supportFragmentManager
        val dialogNuevaNota = NuevaNotaDialogFragment()
        dialogNuevaNota.show(fm, "NuevaNotaDialogFragment")
    }

}
