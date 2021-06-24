package eu.marcelomorais.countries.countryview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import eu.marcelomorais.countries.CountriesApp
import eu.marcelomorais.countries.adapters.CountriesAdapter
import eu.marcelomorais.countries.databinding.CountriesFragmentBinding

class CountriesFragment : Fragment() {
    private val viewModel by viewModels<CountriesViewModel> {
            CountriesViewModelFactory(
                (requireContext().applicationContext as CountriesApp).countriesRepository)
    }

    private lateinit var viewDataBinding: CountriesFragmentBinding
    private lateinit var listAdapter: CountriesAdapter


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewDataBinding = CountriesFragmentBinding.inflate(
            inflater,
            container,
            false)

        viewDataBinding.countriesViewModel = viewModel

        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        viewDataBinding.countriesList.adapter = CountriesAdapter()

        Log.d("CountriesFragment", "onCreateView")

//        viewModel.getAllCountries()

        return viewDataBinding.root
    }
}