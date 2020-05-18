package com.f_a.project_moca.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.f_a.project_moca.R
import com.f_a.project_moca.model.MFavourite


class AFavourite(val context: Context, aLFavourite: ArrayList<MFavourite>) : RecyclerView.Adapter<AFavourite.FavouriteVH>() {

    var  onItemClick: ((MFavourite) -> Unit)? = null
    var aLFavourite: ArrayList<MFavourite> = aLFavourite

    inner class FavouriteVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        var poster: ImageView
        var title: TextView
        var rating: TextView
        var detail: CardView

        init {
            poster = itemView.findViewById(R.id.iv_poster)
            title = itemView.findViewById(R.id.tv_title)
            rating = itemView.findViewById(R.id.rating)
            detail = itemView.findViewById(R.id.item_favourite)
            itemView.setOnClickListener {
                onItemClick?.invoke(aLFavourite[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteVH {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_favourite,
            parent, false)
        return FavouriteVH(v)
    }

    override fun onBindViewHolder(holder: FavouriteVH, position: Int) {
        val currentItem = aLFavourite[position]

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
        return aLFavourite.size
    }
}