package vlab.android.architecture.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * prefer GithubBrowserSample example
 * https://github.com/googlesamples/android-architecture-components/blob/17c315a050745c61ae8e79000bc0251305bd148b/
 *      GithubBrowserSample/app/src/main/java/com/android/example/github/viewmodel/GithubViewModelFactory.kt
 */
class DaggerViewModelFactory : ViewModelProvider.Factory {

    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>

    @Inject constructor(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) {
        this.creators = creators
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull { it ->
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")

        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}