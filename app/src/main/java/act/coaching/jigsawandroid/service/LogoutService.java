package act.coaching.jigsawandroid.service;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by actmember on 2018. 1. 24..
 */

public interface LogoutService {
    @GET("logout")
    Call<Void> logout();
}
