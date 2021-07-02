package eu.marcelomorais.countries.countryview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import eu.marcelomorais.countries.CountriesApp
import eu.marcelomorais.countries.adapters.CountriesAdapter
import eu.marcelomorais.countries.databinding.SearchCountryFragmentBinding

class SearchCountryFragment : Fragment() {

    private val viewModel by viewModels<SearchCountryViewModel> {
        SearchCountryViewModelFactory(
            (requireContext().applicationContext as CountriesApp).countriesRepository)
    }

    private lateinit var viewDataBinding: SearchCountryFragmentBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewDataBinding = SearchCountryFragmentBinding.inflate(
            inflater,
            container,
            false)

        viewDataBinding.viewModel = viewModel

        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        viewDataBinding.resultList.adapter =
            CountriesAdapter(CountriesAdapter.CountryListener {
                viewModel.onCountryItemClicked(it.countryName)
            })

        viewDataBinding.resultList.addItemDecoration(
            DividerItemDecoration(requireContext(),
                LinearLayoutManager.VERTICAL)
        )

        viewModel.currentSearchList.observe(viewLifecycleOwner){
        }

        viewModel.resultSize.observe(viewLifecycleOwner) {
        }

        viewModel.loading.observe(viewLifecycleOwner){
        }

        viewModel.navigateTo.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.clearNavigationLiveData()
                findNavController().navigate(it)
            }
        }

        return viewDataBinding.root
    }
}
