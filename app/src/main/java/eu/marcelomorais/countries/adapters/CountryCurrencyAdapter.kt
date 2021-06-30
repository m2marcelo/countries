package eu.marcelomorais.countries.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.marcelomorais.countries.databinding.CurrencyItemBinding
import eu.marcelomorais.countries.restApi.models.Currency

class CountryCurrencyAdapter : ListAdapter<Currency, CountryCurrencyAdapter.CountryCurrencyViewHolder>(CurrencyDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryCurrencyViewHolder {
        return CountryCurrencyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CountryCurrencyViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item)
    }

    class CountryCurrencyViewHolder(val dataBind: CurrencyItemBinding) : RecyclerView.ViewHolder(dataBind.root) {

        companion object {
            fun from(parent: ViewGroup) = CountryCurrencyViewHolder(
                CurrencyItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        fun binding(item: Currency) {
            dataBind.currency = item
            dataBind.executePendingBindings()
        }

    }

    companion object CurrencyDiffCallBack : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem == newItem
        }
    }
}
