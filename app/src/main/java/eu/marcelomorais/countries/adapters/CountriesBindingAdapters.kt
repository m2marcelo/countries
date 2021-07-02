package eu.marcelomorais.countries.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import eu.marcelomorais.countries.R
import eu.marcelomorais.countries.database.CountriesDBModel
import eu.marcelomorais.countries.restApi.models.Currency
import eu.marcelomorais.countries.restApi.models.Language

fun View.fadeIn() {
    this.alpha = 0f
    this.visibility = View.VISIBLE
    this.animate()
        .alpha(1f)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                this@fadeIn.alpha = 1f
            }
        })
}

fun View.fadeOut() {
    this.animate()
        .alpha(0f)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                this@fadeOut.alpha = 1f
                this@fadeOut.visibility = View.GONE
            }
        })
}

@BindingAdapter("app:country_items")
fun addRecyclerViewContent(recyvlerViewList: RecyclerView, items: List<CountriesDBModel>?) {
    val adapter = recyvlerViewList.adapter as CountriesAdapter
    adapter.submitList(items)
}

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

@BindingAdapter("app:currency_items")
fun addRecyclerViewCurrencyItems(recyclerViewList: RecyclerView, items: List<Currency>?) {
    val adapter = recyclerViewList.adapter as CountryCurrencyAdapter
    adapter.submitList(items)
}

@BindingAdapter("app:language_items")
fun addRecyclerViewLanguageItems(recyclerViewList: RecyclerView, items: List<Language>?) {
    val adapter = recyclerViewList.adapter as CountryLanguageAdapter
    adapter.submitList(items)
}

@BindingAdapter("android:show_progress")
fun showProgressBar(view: ProgressBar, loadingData: LiveData<Boolean?>) {
    loadingData.value?.let {
        if (it) {
            view.fadeIn()
        } else {
            view.fadeOut()
        }
    }
}

@BindingAdapter("android:toggle_text")
fun showMyCountryLabel(textView: TextView, loadingData: LiveData<Boolean?>) {
        loadingData.value?.let {
            val displayText = textView.context.getString(
                if (it) {
                    R.string.my_location_searching
                } else {
                    R.string.my_location_greeting
                }
            )
            textView.text = displayText
            textView.fadeIn()
        }
}

@BindingAdapter("android:show_button")
fun showProgressBar(view: Button, loadingData: LiveData<Boolean?>) {
    loadingData.value?.let {
        if (it) {
            view.fadeOut()
        } else {
            view.fadeIn()
        }
    }
}

@BindingAdapter("android:show_error")
fun showError(view: TextView, listResult: LiveData<Int?>) {
    listResult.value.let {
        if (0 == it) {
            view.fadeIn()
        } else {
            view.fadeOut()
        }
    }
}

@BindingAdapter("android:show_search_label")
fun showSearchLabel(view: TextView, listResult: LiveData<Int?>) {
    listResult.value.let {
        if (it != null) {
            if (it > 0) {
                view.fadeIn()
            } else {
                view.fadeOut()
            }
        }
    }
}
