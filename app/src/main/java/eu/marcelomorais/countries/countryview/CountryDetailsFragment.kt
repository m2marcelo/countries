package eu.marcelomorais.countries.countryview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import eu.marcelomorais.countries.CountriesApp
import eu.marcelomorais.countries.adapters.CountryCurrencyAdapter
import eu.marcelomorais.countries.adapters.CountryLanguageAdapter
import eu.marcelomorais.countries.databinding.FragmentCountryDetailsBinding

class CountryDetailsFragment : Fragment() {

    private val countryArg: CountryDetailsFragmentArgs by navArgs()
    private lateinit var viewDataBinding: FragmentCountryDetailsBinding


    private val viewModel by viewModels<CountryDetailsViewModel> {
        CountryDetailsViewModelFactory(
            (requireContext().applicationContext as CountriesApp).countriesRepository,
            countryArg.country)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentCountryDetailsBinding.inflate(
            inflater,
            container,
            false)

        viewDataBinding.viewModel = viewModel
        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        viewModel.currentCountryDetails.observe(viewLifecycleOwner) {
            Log.d("CountryDetailsFragment", "result = ${it.first()}")
        }

        viewDataBinding.recyclerViewLanguage.adapter = CountryLanguageAdapter()
        viewDataBinding.recyclerViewLanguage.addItemDecoration(
            DividerItemDecoration(requireContext(),
            LinearLayoutManager.VERTICAL)
        )
        viewDataBinding.recyclerViewCurrency.adapter = CountryCurrencyAdapter()
        viewDataBinding.recyclerViewCurrency.addItemDecoration(
            DividerItemDecoration(requireContext(),
                LinearLayoutManager.VERTICAL)
        )

        return viewDataBinding.root
    }
}