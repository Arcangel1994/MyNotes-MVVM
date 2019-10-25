package com.example.roompatronesdiseo

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import android.widget.Switch
import android.widget.RadioGroup
import android.widget.EditText
import com.example.roompatronesdiseo.model.NotaEntity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NuevaNotaDialogFragment : DialogFragment() {

    private lateinit var viewModel: NuevaNotaDialogViewModel
    private var root: View? = null

    private var etTitulo: EditText? = null
    private var etContenido: EditText? = null
    private var rgColor: RadioGroup? = null
    private var swNotaFavorita: Switch? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NuevaNotaDialogViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
           val builder = MaterialAlertDialogBuilder(it)
            builder.setTitle("Nueva nota")
            builder.setMessage("Introduzca los datos de la nueva nota?")
                .setPositiveButton("Guardar",
                    DialogInterface.OnClickListener { dialog, id ->
                        val titulo = etTitulo!!.getText().toString()

                        val contenido = etContenido!!.getText().toString()

                        var color = "azul"

                        when (rgColor!!.getCheckedRadioButtonId()) {

                            R.id.radioButtonColorRojo ->
                                color = "rojo"
                            R.id.radioButtonColorVerde ->
                                color = "verde"
                        }


                        val esFavorita = swNotaFavorita!!.isChecked()

                        viewModel.insertarNota(NotaEntity(titulo, contenido, esFavorita, color))
                        dialog.dismiss()

                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })

            var inflater: LayoutInflater = activity!!.layoutInflater
            root = inflater.inflate(R.layout.nueva_nota_dialog_fragment, null)

            etTitulo = root!!.findViewById(R.id.editTextTitulo);

            etContenido = root!!.findViewById(R.id.editTextContenido);

            rgColor = root!!.findViewById(R.id.radioGroupColor);

            swNotaFavorita = root!!.findViewById(R.id.switchNotaFavorita);

            builder.setView(root)
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

}
