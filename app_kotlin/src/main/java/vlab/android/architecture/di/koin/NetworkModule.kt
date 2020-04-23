package vlab.android.architecture.di.koin

import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vlab.android.architecture.BuildConfig

const val BASE_URL = "https://api.github.com/"

@JvmField
val networkModule = module {
    // Simple Presenter Factory
    this.single { provideRetrofit(BASE_URL) }
}

private fun provideRetrofit(baseUrl : String): Retrofit {
    val builder = OkHttpClient.Builder()
    //builder.interceptors().add(AuthenticatedHeader(sessionRepository))

    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.interceptors().add(logging)
    }

    //builder.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS);
    val client = builder.build()

    // Gson parser
    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(client)
        .build()
}