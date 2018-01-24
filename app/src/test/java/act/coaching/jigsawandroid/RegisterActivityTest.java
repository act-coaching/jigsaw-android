package act.coaching.jigsawandroid;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.VoidAnswer1;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import act.coaching.jigsawandroid.service.RegisterService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by actmember on 2018. 1. 23..
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RegisterActivityTest {

    @Mock
    private RegisterService mockRegisterService;

    Call<Void> mockedCall;

    RegisterActivity registerActivity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockedCall = Mockito.mock(Call.class);
        Mockito.when(mockRegisterService.register(anyString(), anyString(), anyString(), anyString(),
                anyString(), anyString(),anyString(), anyString(), anyString(), anyString())).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<Void> callback = invocation.getArgument(0);

                callback.onResponse(mockedCall, Response.<Void>success(null));
                // or callback.onResponse(mockedCall, Response.error(404. ...);
                // or callback.onFailure(mockedCall, new IOException());

                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));
    }

    @Test
    public void 회원가입버튼클릭(){
        registerActivity = Robolectric.setupActivity(RegisterActivity.class);

        registerActivity.setRegisterService(mockRegisterService);

        EditText empNo = (EditText) registerActivity.findViewById(R.id.empNo);
        EditText name = (EditText) registerActivity.findViewById(R.id.name);
        EditText nickName = (EditText) registerActivity.findViewById(R.id.nickName);
        EditText telNo = (EditText) registerActivity.findViewById(R.id.telNo);
        EditText email = (EditText) registerActivity.findViewById(R.id.email);
        RadioButton radioMale = (RadioButton) registerActivity.findViewById(R.id.radioMale);
        RadioButton radioFemale = (RadioButton) registerActivity.findViewById(R.id.radioFemale);
        EditText teamName = (EditText) registerActivity.findViewById(R.id.teamName);
        EditText depName = (EditText) registerActivity.findViewById(R.id.depName);
        EditText grade = (EditText) registerActivity.findViewById(R.id.grade);
        EditText jobName = (EditText) registerActivity.findViewById(R.id.jobName);

        Button btnJoin = (Button) registerActivity.findViewById(R.id.btnJoin);

        //Given
        empNo.setText("423232");
        name.setText("name");
        nickName.setText("nickname");
        email.setText("mail");
        radioMale.setChecked(true);
        telNo.setText("3450");
        teamName.setText("teamname");
        depName.setText("depname");
        grade.setText("grade");
        jobName.setText("jobname");

        //when
        btnJoin.performClick();

        //than
        Mockito.verify(mockRegisterService).register("423232", "name", "nickname", "mail", "M", "3450", "teamname",
                "depname", "grade", "jobname");

    }

    @Test
    public void 회원가입_성공시_화면이동(){
        회원가입버튼클릭();

        Intent expectedIntent = new Intent(registerActivity, MainActivity.class);
        assertEquals(expectedIntent.getComponent(), shadowOf(RuntimeEnvironment.application).getNextStartedActivity().getComponent());
    }

}