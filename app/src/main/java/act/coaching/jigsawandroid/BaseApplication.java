package act.coaching.jigsawandroid;

import android.app.Application;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by actmember on 2018. 1. 23..
 */

public class BaseApplication extends Application{

    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.0.185:3000")
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                return chain.proceed(
                                        chain.request().newBuilder()
                                                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                                .build());
                            }
                        }).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
