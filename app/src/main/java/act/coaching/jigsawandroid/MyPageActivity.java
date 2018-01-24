package act.coaching.jigsawandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import act.coaching.jigsawandroid.service.ProjectService;

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
    }
}
