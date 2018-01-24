package act.coaching.jigsawandroid;

import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import act.coaching.jigsawandroid.model.Project;
import act.coaching.jigsawandroid.service.LoginService;
import act.coaching.jigsawandroid.service.ProjectService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * Created by actmember on 2018. 1. 24..
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MyPageActivityTest {
    private MyPageActivity myPageActivity;
    private ActivityController controller;

    @Mock
    private ProjectService projectService;

    @Mock
    Call<ArrayList<Project>> mockedCall;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(projectService.getProjects()).thenReturn(mockedCall);

        controller = Robolectric.buildActivity(MyPageActivity.class);
        myPageActivity = (MyPageActivity) controller.get();
        myPageActivity.setprojectService(projectService);
    }

    @Test
    public void getMyProjectsAndExists(){
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<ArrayList<Project>> callback = invocation.getArgument(0);

                ArrayList<Project> response = new ArrayList<>();
                response.add(new Project());
                callback.onResponse(mockedCall, Response.success(response));
                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));
        controller.create().start().resume();

        assertEquals(1, ((ListView)myPageActivity.findViewById(R.id.listView)).getAdapter().getCount());
    }

    @Test
    public void getMyProjectsAndEmpty() {
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<ArrayList<Project>> callback = invocation.getArgument(0);

                ArrayList<Project> response = new ArrayList<>();
                callback.onResponse(mockedCall, Response.success(response));
                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));
        controller.create().start().resume();

        assertEquals(0, ((ListView)myPageActivity.findViewById(R.id.listView)).getAdapter().getCount());
    }

}