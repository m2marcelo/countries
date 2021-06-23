package eu.marcelomorais.countries.countryview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import eu.marcelomorais.countries.CountriesApp
import eu.marcelomorais.countries.R
import eu.marcelomorais.countries.databinding.CountriesFragmentBinding

class CountriesFragment : Fragment() {
    private val viewModel by viewModels<CountriesViewModel> {
            CountriesViewModelFactory(
                (requireContext().applicationContext as CountriesApp).countriesRepository)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: CountriesFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.countries_fragment,
            container,
            false)

        Log.d("CountriesFragment", "onCreateView")

        binding.countriesViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getAllCountries()

        return binding.root
    }
}