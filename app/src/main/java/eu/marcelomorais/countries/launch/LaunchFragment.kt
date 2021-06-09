package eu.marcelomorais.countries.launch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
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
        return inflater.inflate(R.layout.fragment_launch, container, false)
    }
}