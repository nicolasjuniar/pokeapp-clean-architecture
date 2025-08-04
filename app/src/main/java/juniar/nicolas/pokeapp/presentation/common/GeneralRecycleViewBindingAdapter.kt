package juniar.nicolas.pokeapp.presentation.common

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class GeneralRecyclerViewBindingAdapter<T : Any, VB : ViewBinding>(
    private val diffCallback: DiffCallback,
    private val holderResBinding: (parent: ViewGroup) -> ViewBinding,
    private val onBind: (T, VB, pos: Int) -> Unit,
    private val itemListener: (T, pos: Int, VB) -> Unit = { _, _, _ -> run { } },
) : RecyclerView.Adapter<GeneralRecyclerViewBindingAdapter.GeneralViewBindingHolder<T, VB>>() {

    private val listData = mutableListOf<T>()
    private lateinit var itemBinding: VB

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GeneralViewBindingHolder<T, VB> {
        itemBinding = holderResBinding.invoke(p0) as VB
        return GeneralViewBindingHolder(itemBinding)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(p0: GeneralViewBindingHolder<T, VB>, p1: Int) {
        p0.bindView(listData[p0.absoluteAdapterPosition], onBind, itemListener)
    }

    fun setData(datas: List<T>) {
        calculateDiff(datas)
    }

    fun addData(newDatas: List<T>) {
        val list = ArrayList(this.listData)
        list.addAll(newDatas)
        calculateDiff(list)
    }

    fun clearData() {
        calculateDiff(emptyList())
    }

    private fun calculateDiff(newDatas: List<T>) {
        diffCallback.setList(listData, newDatas)
        val result = DiffUtil.calculateDiff(diffCallback)
        with(listData) {
            clear()
            addAll(newDatas)
        }
        result.dispatchUpdatesTo(this)
    }

    fun getItemAt(pos: Int): T? {
        if (pos > listData.size) {
            return null
        }

        return listData[pos]
    }

    class GeneralViewBindingHolder<T : Any, VB : ViewBinding>(
        private val itemBinding: VB,
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindView(
            item: T,
            onBind: (T, VB, pos: Int) -> Unit,
            itemListener: (T, pos: Int, VB) -> Unit
        ) {
            with(itemBinding) {
                onBind.invoke(item, this, absoluteAdapterPosition)
                root.setOnClickListener {
                    itemListener.invoke(item, absoluteAdapterPosition, this)
                }
            }
        }
    }
}