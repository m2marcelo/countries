package eu.marcelomorais.countries.countryview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import eu.marcelomorais.countries.R
import eu.marcelomorais.countries.databinding.CountriesFragmentBinding
import eu.marcelomorais.countries.utils.PermissionHandler

class MyCountryFragment : Fragment(), PermissionHandler.PermissionListener  {

    private val viewModel: MyCountryViewModel by lazy {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = MyCountryViewModelFactory(application)
        ViewModelProvider(this, viewModelFactory)
            .get(MyCountryViewModel::class.java)

    }

    private val permissionUtil = PermissionHandler(this)

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: CountriesFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.countries_fragment,
            container,
            false)

        Log.d("MyCountryFragment", "onCreateView")

        binding.lifecycleOwner = viewLifecycleOwner
//        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        permissionUtil.checkPermissions(this)
    }

    override fun onDestroyView() {
        permissionUtil.unregister()
        super.onDestroyView()
    }

    override fun onPermissionGranted() {
//        All good, nothing to do here
    }

    override fun onPermissionDenied() {
        Toast.makeText(requireContext(), getString(R.string.error_permission_denied), Toast.LENGTH_LONG).show()
    }
}