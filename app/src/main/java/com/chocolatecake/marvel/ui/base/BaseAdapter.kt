    package com.chocolatecake.marvel.ui.base


    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.databinding.DataBindingUtil
    import androidx.databinding.ViewDataBinding
    import androidx.recyclerview.widget.RecyclerView
    import androidx.recyclerview.widget.RecyclerView.ViewHolder

    abstract class BaseAdapter<T>(
        private var items:List<T>,
        private var listener: BaseInteractionListener
        ):RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

          abstract val layoutId:Int
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),layoutId,parent,false))
        }

       abstract override fun onBindViewHolder(holder: BaseViewHolder, position: Int)


        fun setItems(newItem:List<T>){
            items=newItem
        }

        fun getItems()= items

        override fun getItemCount()=items.size

        class ItemViewHolder(binding:ViewDataBinding):BaseViewHolder(binding)
        abstract class BaseViewHolder(itemView: ViewDataBinding) :ViewHolder(itemView.root)

        }
    interface BaseInteractionListener