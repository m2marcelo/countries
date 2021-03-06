package eu.marcelomorais.countries.countryview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import eu.marcelomorais.countries.CountriesApp
import eu.marcelomorais.countries.R
import eu.marcelomorais.countries.databinding.MyCountryFragmentBinding
import eu.marcelomorais.countries.restApi.models.CurrentCountry
import eu.marcelomorais.countries.utils.LocationUtils
import eu.marcelomorais.countries.utils.NetworkConnection
import eu.marcelomorais.countries.utils.PermissionHandler

class MyCountryFragment : Fragment(), PermissionHandler.PermissionListener, LocationUtils.LocationListener {

    private val viewModel by viewModels<MyCountryViewModel> {
        MyCountryViewModelFactory(
            (requireContext().applicationContext as CountriesApp).countriesRepository
        )
    }

    private val permissionUtil = PermissionHandler(this)
    private val locationUtil = LocationUtils(this)
    private lateinit var viewDataBinding: MyCountryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = MyCountryFragmentBinding.inflate(
            inflater,
            container,
            false)

        viewDataBinding.viewModel = viewModel
        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        viewModel.myCountryInfo.observe(viewLifecycleOwner){
        }

        viewModel.loading.observe(viewLifecycleOwner) {
        }

        viewModel.myCountryDestinations.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.clearMyCountryNavigationLiveData()
                findNavController().navigate(it)
            }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        permissionUtil.checkPermissions(this)
    }

    override fun onDestroyView() {
        permissionUtil.unregister()
        viewModel.showProgressBar(false)
        super.onDestroyView()
    }

    @SuppressLint("MissingPermission")
    override fun onPermissionGranted() {
        if(NetworkConnection(requireContext()).isConnected()) {
            locationUtil.getLocation(requireActivity(), requireContext())
        } else {
            showNetworkError()
            viewModel.showProgressBar(false)
        }
    }

    override fun onPermissionDenied() {
        viewModel.showProgressBar(false)
        Toast.makeText(
            requireContext(),
            getString(R.string.error_permission_denied),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onCurrentLocationReady(location: CurrentCountry) {
        viewModel.showProgressBar(false)

        if(NetworkConnection(requireContext()).isConnected()) {
            viewModel.getCurrentCountry(location)
        } else {
            showNetworkError()
        }
    }
}