package eu.marcelomorais.countries.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.marcelomorais.countries.database.CountriesDBModel
import eu.marcelomorais.countries.databinding.CountryItemViewBinding


class CountriesAdapter(private val clickListener: CountryListener) : ListAdapter <CountriesDBModel, CountriesAdapter.CountryViewHolder>(CountryDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item, clickListener)
    }

    class CountryListener(val clickListener: (country: CountriesDBModel) -> Unit) {
        fun onClick(country: CountriesDBModel) = clickListener(country)
    }

    class CountryViewHolder(val dataBind: CountryItemViewBinding) : RecyclerView.ViewHolder(dataBind.root) {

        companion object {
            fun from(parent: ViewGroup) = CountryViewHolder(
                CountryItemViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        fun binding(item: CountriesDBModel, listener: CountryListener) {
            dataBind.listener = listener
            dataBind.countryItem = item
            dataBind.executePendingBindings()
        }

    }

    companion object CountryDiffCallBack : DiffUtil.ItemCallback<CountriesDBModel>() {
        override fun areItemsTheSame(oldItem: CountriesDBModel, newItem: CountriesDBModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CountriesDBModel, newItem: CountriesDBModel): Boolean {
            return oldItem == newItem
        }
    }
}

