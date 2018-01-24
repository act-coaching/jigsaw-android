package act.coaching.jigsawandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import act.coaching.jigsawandroid.service.LogoutService;
import act.coaching.jigsawandroid.service.ProjectService;

/**
 * Created by actmember on 2018. 1. 23..
 */

public class MyPageActivity extends AppCompatActivity {
    ProjectService projectService;
    LogoutService logoutService;

    void setprojectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    ProjectService getProjectService() {
        if (projectService == null) {
            return BaseApplication.retrofit.create(ProjectService.class);
        }
        return projectService;
    }

    public void setLogoutService(LogoutService logoutService) {
        this.logoutService = logoutService;
    }


    public LogoutService getLogoutService() {
        if (logoutService == null) {
            return BaseApplication.retrofit.create(LogoutService.class);
        }
        return logoutService;
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
