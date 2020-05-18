package com.f_a.project_moca.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.f_a.project_moca.R
import com.f_a.project_moca.model.MMovie

class AMovie (val context: Context, alMovie: ArrayList<MMovie>) : RecyclerView.Adapter<AMovie.MovieVH>(),
    Filterable {

    var onItemClick: ((MMovie) -> Unit)? = null
    var alMovie: ArrayList<MMovie> = alMovie
    lateinit var alMovieFull: ArrayList<MMovie>

    inner class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var poster: ImageView
        var title: TextView
        var rating: TextView
        var detail: CardView

        init {
            poster = itemView.findViewById(R.id.iv_poster)
            title = itemView.findViewById(R.id.tv_title)
            rating = itemView.findViewById(R.id.rating)
            detail = itemView.findViewById(R.id.item_movie)
            itemView.setOnClickListener {
                onItemClick?.invoke(alMovie[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie,
            parent, false)
        return MovieVH(v)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val currentItem = alMovie[position]

        val encodeByte: ByteArray = android.util.Base64.decode(currentItem.poster, android.util.Base64.DEFAULT)
        val bM = BitmapFactory.decodeByteArray(
            encodeByte, 0,
            encodeByte.size
        )

        holder.poster.setImageBitmap(bM)
        holder.title.text = currentItem.title
        holder.rating.text = currentItem.rating
    }

    override fun getItemCount(): Int {
        return alMovie.size
    }

    override fun getFilter(): Filter {
        return alMovieFilter
    }

    private val alMovieFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<MMovie> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(alMovieFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in alMovieFull) {
                    if (item.title!!.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            alMovie.clear()
            alMovie.addAll(results.values as ArrayList<MMovie>)
            notifyDataSetChanged()
        }
    }
    init {
        alMovieFull = ArrayList(alMovie)
    }
}