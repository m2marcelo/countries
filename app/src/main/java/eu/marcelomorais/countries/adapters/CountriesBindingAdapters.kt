package eu.marcelomorais.countries.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.marcelomorais.countries.database.CountriesDBModel

@BindingAdapter("app:country_items")
fun addRecyclerViewContent(recyvlerViewList: RecyclerView, items: List<CountriesDBModel>?) {
    val adapter = recyvlerViewList.adapter as CountriesAdapter
    adapter.submitList(items)
}
