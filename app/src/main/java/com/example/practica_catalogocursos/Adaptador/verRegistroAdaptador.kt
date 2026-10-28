package com.example.practica_catalogocursos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.practica_catalogocursos.R
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practica_catalogocursos.modelo.dataCatalogoCursos

class verRegistroAdaptador(private var listaRegistros: List<dataCatalogoCursos>) :
    RecyclerView.Adapter<verRegistroAdaptador.RegistroViewHolder>() {

    class RegistroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCurso: ImageView = itemView.findViewById(R.id.imgcurso)
        val txtNombreCurso: TextView = itemView.findViewById(R.id.txtnombrecurso)
        val txtInscritos: TextView = itemView.findViewById(R.id.txtinscritos)
        val txtTotalCupo: TextView = itemView.findViewById(R.id.txtlimitealumnos)
        val progressBarCurso: ProgressBar = itemView.findViewById(R.id.pb_inscritos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_registro, parent, false)
        return RegistroViewHolder(view)
    }

    override fun onBindViewHolder(holder: RegistroViewHolder, position: Int) {
        val registro = listaRegistros[position]
        val context = holder.itemView.context
        holder.txtNombreCurso.text = registro.nombreCurso

        val labelInscritos = context.getString(R.string.txtinscritos)
        val labelLimite = context.getString(R.string.txtlimitealumnos)

        holder.txtInscritos.text = "$labelInscritos ${registro.totalRegistros}"
        holder.txtTotalCupo.text = "$labelLimite ${registro.cupoTotalCurso}"
        val cupoMax = registro.cupoTotalCurso.toIntOrNull() ?: 50
        holder.progressBarCurso.max = cupoMax
        holder.progressBarCurso.progress = registro.totalRegistros

        val baseUrl = "http://192.168.100.53/catalogocursos/"
        val urlImagen = baseUrl + registro.rutaImagen

        Glide.with(context)
            .load(urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgCurso)
    }

    override fun getItemCount(): Int {
        return listaRegistros.size
    }
    fun actualizarLista(nuevaLista: List<dataCatalogoCursos>) {
        listaRegistros = nuevaLista
        notifyDataSetChanged()
    }
}