package eu.marcelomorais.countries.countryview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import eu.marcelomorais.countries.CountriesApp
import eu.marcelomorais.countries.databinding.FragmentCountryDetailsBinding
import org.koin.core.parameter.parametersOf

class CountryDetailsFragment : Fragment() {

    private val countryArg: CountryDetailsFragmentArgs by navArgs()
    private lateinit var viewDataBinding: FragmentCountryDetailsBinding

    private val viewModel by viewModels<CountryDetailsViewModel> {
        CountryDetailsViewModelFactory(
            (requireContext().applicationContext as CountriesApp).countriesRepository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentCountryDetailsBinding.inflate(
            inflater,
            container,
            false)

        viewDataBinding.vieModel = viewModel
        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        Log.d("CountryDetailsFragment", "countryArg = " + parametersOf(countryArg.country))


        return viewDataBinding.root
    }

}