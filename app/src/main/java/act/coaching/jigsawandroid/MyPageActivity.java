package act.coaching.jigsawandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import act.coaching.jigsawandroid.adapter.ProjectListAdapter;
import act.coaching.jigsawandroid.model.Project;
import act.coaching.jigsawandroid.service.ProjectService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by actmember on 2018. 1. 23..
 */

public class MyPageActivity extends AppCompatActivity {
    ProjectService projectService;

    void setprojectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    ProjectService getProjectService() {
        if (projectService == null) {
            return BaseApplication.retrofit.create(ProjectService.class);
        }
        return projectService;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        if (getActionBar() != null) {
            getActionBar().setTitle("my page");
        }

        init();
    }

    private void init() {
        ListView listView = (ListView) findViewById(R.id.listView);
        final ProjectListAdapter adapter = new ProjectListAdapter(this);
        listView.setAdapter(adapter);

        getProjectService().getProjects().enqueue(new Callback<ArrayList<Project>>() {
            @Override
            public void onResponse(Call<ArrayList<Project>> call, Response<ArrayList<Project>> response) {
                Log.d("TAG", "onResponse called");
                adapter.setList(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Project>> call, Throwable t) {

            }
        });
    }
}
