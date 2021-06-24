package eu.marcelomorais.countries.adapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import eu.marcelomorais.countries.R
import eu.marcelomorais.countries.database.CountriesDBModel

@BindingAdapter("app:country_items")
fun addRecyclerViewContent(recyvlerViewList: RecyclerView, items: List<CountriesDBModel>?) {
    val adapter = recyvlerViewList.adapter as CountriesAdapter
    adapter.submitList(items)
}

//@BindingAdapter("app:country_flag")
//fun getCountryFlag(view: ImageView, src: String?) {
//    src?.let {
//        val uri = src.toUri().buildUpon().scheme("https").build()
//        Picasso.get()
//            .load(uri)
//            .error(R.drawable.ic_flag)
//            .placeholder(R.drawable.ic_flag)
//            .into(view)
//    }
//}

@BindingAdapter("app:country_flag")
fun getCountryFlag(imageView: ImageView, imgUrl: String?) {
    if (imgUrl != null) {
        GlideToVectorYou
            .init()
            .with(imageView.context)
            .setPlaceHolder(R.drawable.ic_flag, R.drawable.ic_flag)
            .load(imgUrl.toUri(), imageView)
    }
}
