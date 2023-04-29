    package com.chocolatecake.marvel.ui.base

    import android.view.View
    import androidx.databinding.ViewDataBinding
    import androidx.recyclerview.widget.RecyclerView
    import androidx.recyclerview.widget.RecyclerView.ViewHolder

    abstract class BaseAdapter<T>(
        private var items:List<T>,
        private var baseInteractionListener:BaseInteractionListener
        ):RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            val currentItem=items[position]

        }

        fun setItems(newItem:List<T>){
            items=newItem
        }

        fun getItems()= items

        override fun getItemCount()=items.size


        class ItemViewHolder(var binding:ViewDataBinding):BaseViewHolder(binding)
        abstract class BaseViewHolder(itemView: ViewDataBinding) :ViewHolder(itemView.root){

        }
    }
    interface BaseInteractionListener