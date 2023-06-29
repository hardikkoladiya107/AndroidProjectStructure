package com.demo.structure.injection.module;

import static com.demo.structure.network.NetConst.BASEURL;

import android.content.Context;

import com.demo.structure.injection.anotations.ForApplication;
import com.demo.structure.network.ApiInterface;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created By Hardik Koladiya,Android,UNIKWORD LLP
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    ApiInterface getApiService(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    OkHttpClient.Builder getOkHttpClient(HttpLoggingInterceptor interceptor,@ForApplication Context context){
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);
        client.addInterceptor(interceptor);
        return client;
    }

    @Singleton
    @Provides
    Retrofit getRetrofit(OkHttpClient.Builder client) {
        return new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

}
