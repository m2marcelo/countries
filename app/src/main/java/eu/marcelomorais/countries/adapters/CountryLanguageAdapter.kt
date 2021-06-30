package eu.marcelomorais.countries.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.marcelomorais.countries.databinding.LanguageItemBinding
import eu.marcelomorais.countries.restApi.models.Language

class CountryLanguageAdapter : ListAdapter<Language, CountryLanguageAdapter.CountryLanguageViewHolder>(LanguageDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryLanguageViewHolder {
        return CountryLanguageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CountryLanguageViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding(item)
    }

    class CountryLanguageViewHolder(val dataBind: LanguageItemBinding) : RecyclerView.ViewHolder(dataBind.root) {

        companion object {
            fun from(parent: ViewGroup) = CountryLanguageViewHolder(
                LanguageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        fun binding(item: Language) {
            dataBind.language = item
            dataBind.executePendingBindings()
        }

    }

    companion object LanguageDiffCallBack : DiffUtil.ItemCallback<Language>() {
        override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean {
            return oldItem.iso639_1 == newItem.iso639_1
        }

        override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean {
            return oldItem == newItem
        }
    }
}


