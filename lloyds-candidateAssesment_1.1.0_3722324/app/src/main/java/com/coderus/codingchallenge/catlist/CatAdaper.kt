package com.coderus.codingchallenge.catlist

import android.database.DatabaseUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.coderus.codingchallenge.R
import com.coderus.codingchallenge.data.CatResponseItem
import com.coderus.codingchallenge.databinding.RecycleviewItemBinding


class CatAdaper(
    private val catlist: List<CatResponseItem>
) : RecyclerView.Adapter<CatAdaper.CatViewHolder>(){
    override fun getItemCount() = catlist.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CatViewHolder(
           DataBindingUtil.inflate(
               LayoutInflater.from(parent.context),
               R.layout.recycleview_item,
               parent,
               false
           )

    )



    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.recyclerviewItemBinding.catresponseitem= catlist.get(position)
    }
    inner class CatViewHolder(
        val recyclerviewItemBinding: RecycleviewItemBinding
    ) : RecyclerView.ViewHolder(recyclerviewItemBinding.root)
}
