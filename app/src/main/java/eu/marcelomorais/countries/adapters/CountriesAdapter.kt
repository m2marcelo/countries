package eu.marcelomorais.countries.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.marcelomorais.countries.database.CountriesDBModel


class CountriesAdapter : ListAdapter <CountriesDBModel, CountriesAdapter.CountryViewHolder>(CountryDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    class CountryViewHolder : RecyclerView.ViewHolder() {

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

