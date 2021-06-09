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
import eu.marcelomorais.countries.databinding.CountriesFragmentBinding

class CountriesFragment : Fragment() {

    private val viewModel: CountriesViewModel by lazy {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = CountriesViewModelFactory(application)
        ViewModelProvider(this, viewModelFactory)
            .get(CountriesViewModel::class.java)

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

        binding.lifecycleOwner = viewLifecycleOwner
//        binding.viewModel = viewModel

        return binding.root
    }


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.countries_fragment, container, false)
//    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}