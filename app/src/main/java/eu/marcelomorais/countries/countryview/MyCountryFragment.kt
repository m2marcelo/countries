package eu.marcelomorais.countries.countryview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import eu.marcelomorais.countries.R
import eu.marcelomorais.countries.databinding.CountriesFragmentBinding

class MyCountryFragment : Fragment() {

    private val viewModel: MyCountryViewModel by lazy {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = MyCountryViewModelFactory(application)
        ViewModelProvider(this, viewModelFactory)
            .get(MyCountryViewModel::class.java)

    }

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
}