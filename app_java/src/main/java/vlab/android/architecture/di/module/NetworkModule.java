package vlab.android.architecture.di.module;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import vlab.android.architecture.BuildConfig;

@Module
public class NetworkModule {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //builder.interceptors().add(AuthenticatedHeader(sessionRepository))

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.interceptors().add(logging);
        }

        //builder.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        // Gson parser
        Gson gson = new GsonBuilder().setDateFormat(DATE_TIME_FORMAT).create();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)
                .build();
    }
}
