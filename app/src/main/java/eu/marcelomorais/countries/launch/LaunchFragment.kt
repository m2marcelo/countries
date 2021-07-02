package eu.marcelomorais.countries.launch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import eu.marcelomorais.countries.R
import eu.marcelomorais.countries.databinding.FragmentLaunchBinding
import eu.marcelomorais.countries.utils.NetworkConnection

class LaunchFragment : Fragment() {

    private lateinit var binding: FragmentLaunchBinding

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

    private fun showNetworkError() {
        Toast.makeText(
            requireContext(),
            R.string.network_error,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun navigateToSearchCountry() {
        if(NetworkConnection(requireContext()).isConnected()) {
            this.findNavController().navigate(LaunchFragmentDirections
                .actionLaunchFragmentToSearchCountryFragment())
        } else {
            showNetworkError()
        }
    }

    private fun navigateToMyCountry() {
        if(NetworkConnection(requireContext()).isConnected()) {
            this.findNavController().navigate(LaunchFragmentDirections
                .actionLaunchFragmentToMyCountryFragment())
        } else {
            showNetworkError()
        }
    }

    private fun navigateToAllCountries() {
        if(NetworkConnection(requireContext()).isConnected()) {
            this.findNavController().navigate(LaunchFragmentDirections
                .actionLaunchFragmentToCountriesFragment())
        } else {
            showNetworkError()
        }
    }
}