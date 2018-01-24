package act.coaching.jigsawandroid.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by actmember on 2018. 1. 24..
 */

public interface RegisterService {
    @FormUrlEncoded
    @POST("users")
    Call<Void> register(
            @Field("employeeNumber") String employeeNumber,
            @Field("name") String name,
            @Field("nickName") String nickName,
            @Field("email") String email,
            @Field("gender") String gender,
            @Field("cellPhoneNumber") String cellPhoneNumber,
            @Field("team") String team,
            @Field("department") String department,
            @Field("rank") String rank,
            @Field("duty") String duty

    );

}
