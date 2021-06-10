package eu.marcelomorais.countries.countryview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import eu.marcelomorais.countries.R
import eu.marcelomorais.countries.databinding.SearchCountryFragmentBinding

class SearchCountryFragment : Fragment() {

    private val viewModel: SearchCountryViewModel by lazy {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = SearchCountryViewModelFactory(application)
        ViewModelProvider(this, viewModelFactory)
            .get(SearchCountryViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: SearchCountryFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.search_country_fragment,
            container,
            false
        )

        Log.d("SearchCountryFragment", "onCreateView")

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }
}