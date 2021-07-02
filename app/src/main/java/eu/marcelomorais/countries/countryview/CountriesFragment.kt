package eu.marcelomorais.countries.countryview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import eu.marcelomorais.countries.CountriesApp
import eu.marcelomorais.countries.R
import eu.marcelomorais.countries.adapters.CountriesAdapter
import eu.marcelomorais.countries.databinding.CountriesFragmentBinding
import eu.marcelomorais.countries.utils.NetworkConnection

class CountriesFragment : Fragment() {
    private val viewModel by viewModels<CountriesViewModel> {
            CountriesViewModelFactory(
                (requireContext().applicationContext as CountriesApp).countriesRepository)
    }

    private lateinit var viewDataBinding: CountriesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewDataBinding = CountriesFragmentBinding.inflate(
            inflater,
            container,
            false)

        viewDataBinding.countriesViewModel = viewModel

        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        viewDataBinding.countriesList.adapter =
            CountriesAdapter(CountriesAdapter.CountryListener {
                if(NetworkConnection(requireContext()).isConnected()) {
                    viewModel.onCountryItemClicked(it.countryName)
                } else {
                    showNetworkError()
                }
            })

        viewDataBinding.countriesList.addItemDecoration(
            DividerItemDecoration(requireContext(),
                LinearLayoutManager.VERTICAL)
        )

        viewModel.navigateTo.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.clearNavigationLiveData()
                findNavController().navigate(it)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
        }

        if(NetworkConnection(requireContext()).isConnected()) {
            viewModel.updateCountriesData()
        } else {
            showNetworkError()
        }

        return viewDataBinding.root
    }

    private fun showNetworkError() {
        Toast.makeText(
            requireContext(),
            R.string.network_error,
            Toast.LENGTH_LONG
        ).show()
    }
}