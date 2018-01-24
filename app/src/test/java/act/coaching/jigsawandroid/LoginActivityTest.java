package act.coaching.jigsawandroid;

import android.content.Intent;
import android.widget.EditText;

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
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import act.coaching.jigsawandroid.service.LoginService;
import act.coaching.jigsawandroid.util.JigsawPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by actmember on 2018. 1. 23..
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LoginActivityTest {

    @Mock
    private LoginService mockLoginService;

    Call<String> mockedCall;
    MainActivity mainActivity;

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
    public void requestLogin() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.setLoginService(mockLoginService);

        EditText empNo = (EditText) mainActivity.findViewById(R.id.editEmpNo);
        EditText editTelNo = (EditText)mainActivity.findViewById(R.id.editTelNo);
        empNo.setText("12345");
        editTelNo.setText("67890");

        assertEquals(mainActivity.findViewById(R.id.btnLogin).performClick(), true);

        Mockito.verify(mockLoginService).login(eq(empNo.getText().toString()), eq(editTelNo.getText().toString()));
    }

    @Test
    public void saveTokenFromResponse() {
        requestLogin();

        assertEquals("SUCCESS_TOKEN", JigsawPreference.getInstance(mainActivity).getString(JigsawPreference.TOKEN));
    }

    @Test
    public void checkRequestSuccess() {
        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        requestLogin();

        Intent expectedIntent = new Intent(mainActivity, MyPageActivity.class);
        assertEquals(expectedIntent.getComponent(), application.getNextStartedActivity().getComponent());
    }
}
