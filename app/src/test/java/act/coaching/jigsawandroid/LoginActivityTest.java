package act.coaching.jigsawandroid;

import android.content.Intent;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.robolectric.Shadows.shadowOf;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import act.coaching.jigsawandroid.service.LoginService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by actmember on 2018. 1. 23..
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LoginActivityTest {

    @Mock
    private LoginService mockLoginService;

    Call<String> mockedCall;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockedCall = Mockito.mock(Call.class);
        Mockito.when(mockLoginService.login(anyString(), anyString())).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<String> callback = invocation.getArgument(0);

                callback.onResponse(mockedCall, Response.success("SUCCESS_TOKEN"));
                // or callback.onResponse(mockedCall, Response.error(404. ...);
                // or callback.onFailure(mockedCall, new IOException());

                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));
    }

    @Test
    public void clickLoginBtnAndSuccess() {
        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

        mainActivity.setLoginService(mockLoginService);

        EditText empNo = (EditText) mainActivity.findViewById(R.id.editEmpNo);
        EditText editTelNo = (EditText)mainActivity.findViewById(R.id.editTelNo);
        empNo.setText("12345");
        editTelNo.setText("67890");

        // network
//        server.enqueue(new MockResponse().setResponseCode(200)
//                .setBody("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbXBsb3llZU51bWJlciI6IjEyMzQ1IiwiaWF0IjoxNTE2Njg3MjkwLCJleHAiOjE1MTY4NjAwOTAsImlzcyI6InNkcy5hY3QuY29hY2giLCJzdWIiOiJKaWtzYXdBdXRoIn0.9_SLyFgZwgiGem0QVH6gKo-NnfUBPdVkfm5WbXbHMoQ"));

        mainActivity.findViewById(R.id.btnLogin).performClick();

        Mockito.verify(mockLoginService).login(eq("12345"), eq("67890"));

//        Intent expectedIntent = new Intent(mainActivity, MyPageActivity.class);
//        assertEquals(expectedIntent.getComponent(), application.getNextStartedActivity().getComponent());
    }

}
