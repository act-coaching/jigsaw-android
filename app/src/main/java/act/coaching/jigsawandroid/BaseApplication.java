package act.coaching.jigsawandroid;

import android.app.Application;

import java.io.IOException;

import act.coaching.jigsawandroid.util.JigsawPreference;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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
                                Request.Builder builder = chain.request().newBuilder();
                                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
//                                if(!JigsawPreference.getInstance(BaseApplication.this).getString(JigsawPreference.TOKEN).isEmpty()) {
//                                    builder.addHeader("x-access-token", JigsawPreference.getInstance(BaseApplication.this).getString(JigsawPreference.TOKEN));
//                                }
                                return chain.proceed(builder.build());
                            }
                        }).addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
