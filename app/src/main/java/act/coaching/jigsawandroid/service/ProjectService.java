package act.coaching.jigsawandroid.service;

import java.util.ArrayList;

import act.coaching.jigsawandroid.model.Project;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by actmember on 2018. 1. 24..
 */

public interface ProjectService {
    @GET("my/projects")
    Call<ArrayList<Project>> getProjects();
}
