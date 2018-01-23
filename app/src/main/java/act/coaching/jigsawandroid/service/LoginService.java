package act.coaching.jigsawandroid.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by actmember on 2018. 1. 23..
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("login")
    Call<String> login(
            @Field("employeeNumber") String employeeNumber,
            @Field("cellPhoneNumber") String cellPhoneNumber
            );
}
