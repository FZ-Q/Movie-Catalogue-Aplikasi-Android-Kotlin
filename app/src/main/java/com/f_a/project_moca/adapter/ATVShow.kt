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
import com.f_a.project_moca.model.MTVShow

class ATVShow (val context: Context, alTVShow: ArrayList<MTVShow>) : RecyclerView.Adapter<ATVShow.TVShowVH>(),
    Filterable{

    var onItemClick: ((MTVShow) -> Unit)? = null
    var alTVShow: ArrayList<MTVShow> = alTVShow
    lateinit var alTVShowFull: ArrayList<MTVShow>

    inner class TVShowVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        var poster: ImageView
        var title: TextView
        var rating: TextView
        var detail: CardView

        init {
            poster = itemView.findViewById(R.id.iv_poster)
            title = itemView.findViewById(R.id.tv_title)
            rating = itemView.findViewById(R.id.rating)
            detail = itemView.findViewById(R.id.item_tv_show)
            itemView.setOnClickListener {
                onItemClick?.invoke(alTVShow[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowVH{
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show,
            parent, false)
        return TVShowVH(v)
    }

    override fun onBindViewHolder(holder: TVShowVH, position: Int){
        val currentItem = alTVShow[position]

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
        return alTVShow.size
    }

    override fun getFilter(): Filter {
        return alTVShowFilter
    }

    private val alTVShowFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<MTVShow> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(alTVShowFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in alTVShowFull) {
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
            alTVShow.clear()
            alTVShow.addAll(results.values as ArrayList<MTVShow>)
            notifyDataSetChanged()
        }
    }
    init {
        alTVShowFull = ArrayList(alTVShow)
    }
}