package eu.marcelomorais.countries.launch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import eu.marcelomorais.countries.R
import eu.marcelomorais.countries.databinding.FragmentLaunchBinding

class LaunchFragment : Fragment() {

    private  lateinit var binding: FragmentLaunchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        binding = FragmentLaunchBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.btnShowAllCountries.setOnClickListener { navigateToAllCountries() }
        binding.btnShowMyCountry.setOnClickListener { navigateToMyCountry() }
        binding.btnSearchCountry.setOnClickListener { navigateToSearchCountry() }


        return binding.root
    }

    private fun navigateToSearchCountry() {
        this.findNavController().navigate(LaunchFragmentDirections
            .actionLaunchFragmentToSearchCountryFragment())
    }

    private fun navigateToMyCountry() {
        this.findNavController().navigate(LaunchFragmentDirections
            .actionLaunchFragmentToMyCountryFragment())
    }

    private fun navigateToAllCountries() {
        Log.d("LaunchFragment", "navigateToAllCountries")
        this.findNavController().navigate(LaunchFragmentDirections
            .actionLaunchFragmentToCountriesFragment())
    }
}