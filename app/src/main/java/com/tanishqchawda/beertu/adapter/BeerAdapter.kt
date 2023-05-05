package com.tanishqchawda.beertu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tanishqchawda.beertu.R
import com.tanishqchawda.beertu.databinding.BottomDetailsBeerBinding
import com.tanishqchawda.beertu.databinding.SingleItemBinding
import com.tanishqchawda.beertu.model.BeerResponseModelItem

class BeerAdapter(private val context: Context, val onShareClick:(BeerResponseModelItem) ->Unit):PagingDataAdapter<BeerResponseModelItem,BeerAdapter.MyViewHolder>(BeerDiffUtils()) {
    inner class MyViewHolder(val binding: SingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(beerResponseModelItem: BeerResponseModelItem) {
           binding.beerModel = beerResponseModelItem

        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BeerAdapter.MyViewHolder {
        val binding =
            SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeerAdapter.MyViewHolder, position: Int) {
        val beer =getItem(position)
        holder.bind(beer!!)
        holder.binding.apply {
            Glide.with(context).load(beer.imageUrl).override(100, 100).into(iBeerImage)
            share.setOnClickListener {
                onShareClick(beer)
            }

        }
        holder.itemView.setOnLongClickListener {
            details(getItem(position)!!,position)
            true
        }
    }


    private fun details(beerResponseModelItem: BeerResponseModelItem, position:Int) {
        val binding: BottomDetailsBeerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.bottom_details_beer,
            null,
            false
        )
        val dialog = BottomSheetDialog(context, R.style.BottomSheetDialog)
        binding.apply {
            beerModelData = beerResponseModelItem
            positionBeer = position
            close.setOnClickListener {
                dialog.dismiss()
            }

        }

        dialog.setContentView(binding.root)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.show()
    }
}
class BeerDiffUtils : DiffUtil.ItemCallback<BeerResponseModelItem>() {
    override fun areItemsTheSame(
        oldItem: BeerResponseModelItem,
        newItem: BeerResponseModelItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: BeerResponseModelItem,
        newItem: BeerResponseModelItem
    ): Boolean {
        return oldItem == newItem
    }
}
