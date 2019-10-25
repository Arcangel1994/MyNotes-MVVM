package com.example.roompatronesdiseo.adapter

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.roompatronesdiseo.NuevaNotaDialogViewModel
import com.example.roompatronesdiseo.R
import com.example.roompatronesdiseo.model.NotaEntity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import xyz.hasnat.sweettoast.SweetToast


class MyNotaRecyclerViewAdapter(
    private var mValues: List<NotaEntity>,
    private val ctx: Context,
    private val viewModel: NuevaNotaDialogViewModel = ViewModelProviders.of(ctx as AppCompatActivity).get(NuevaNotaDialogViewModel::class.java)
) : RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nota, parent, false)
        return ViewHolder(view)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setIsRecyclable(false)
        holder.mItem = mValues[position]
        holder.tvTitulo.text = holder.mItem!!.titulo
        holder.tvContenido.text = holder.mItem!!.contenido

        if (holder.mItem!!.isFavorita) {

            holder.ivFavorita.setImageResource(R.drawable.ic_star)

        }

        holder.itemView.setOnClickListener {

            if(holder.mItem!!.isFavorita){
                holder.mItem!!.isFavorita = false
                holder.ivFavorita.setImageResource(R.drawable.ic_star_border)
                SweetToast.warning(ctx, "Se ha desmarcado como favorita");
            }else{
                holder.mItem!!.isFavorita = true
                holder.ivFavorita.setImageResource(R.drawable.ic_star)
                SweetToast.success(ctx, "Se ha marcado como favorita");
            }
            viewModel.updateNota(holder.mItem!!)
        }

    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    fun setNuevasNotas(nuevasNotas: List<NotaEntity>){
        mValues = nuevasNotas;
        notifyDataSetChanged()
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        var tvTitulo: TextView
        var tvContenido: TextView
        var ivFavorita: ImageView
        var mItem: NotaEntity? = null

        init {
            tvTitulo = mView.findViewById(R.id.textViewTitulo)
            tvContenido = mView.findViewById(R.id.textViewContenido)
            ivFavorita = mView.findViewById(R.id.imageViewFavorita)
        }

    }

}