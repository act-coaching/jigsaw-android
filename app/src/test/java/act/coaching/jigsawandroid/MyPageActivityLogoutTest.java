package act.coaching.jigsawandroid;

import android.content.Intent;
import android.widget.Button;

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

import act.coaching.jigsawandroid.service.LogoutService;
import act.coaching.jigsawandroid.util.JigsawPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by actmember on 2018. 1. 24..
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MyPageActivityLogoutTest {

    @Mock
    private LogoutService mockLogoutService;

    Call<Void> mockedCall;

    MyPageActivity myPageActivity;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        mockedCall = Mockito.mock(Call.class);
        Mockito.when(mockLogoutService.logout()).thenReturn(mockedCall);

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
    public void 로그아웃버튼클릭_로그아웃api호출(){

        //given
        myPageActivity = Robolectric.setupActivity(MyPageActivity.class);

        myPageActivity.setLogoutService(mockLogoutService);

        Button btnLogout = (Button) myPageActivity.findViewById(R.id.btnLogout);

        //when
        btnLogout.performClick();

        //then 로그아웃api 호출
        assertEquals(myPageActivity.findViewById(R.id.btnLogout).performClick(), true);

    }

    @Test
    public void saveTokenFromResponse() {
        로그아웃버튼클릭_로그아웃api호출();

        assertEquals("", JigsawPreference.getInstance(myPageActivity).getString(JigsawPreference.TOKEN));
    }

    @Test
    public void 로그아웃버튼클릭성공시_토큰정보삭제_로그인화면으로이동한다() {
        //given
        로그아웃버튼클릭_로그아웃api호출();

        //then
        Intent expectedIntent = new Intent(myPageActivity, MainActivity.class);
        assertEquals(expectedIntent.getComponent(), shadowOf(RuntimeEnvironment.application).getNextStartedActivity().getComponent());

    }
}
