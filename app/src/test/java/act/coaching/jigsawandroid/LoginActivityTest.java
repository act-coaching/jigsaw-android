package act.coaching.jigsawandroid;

import android.content.Intent;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.robolectric.Shadows.shadowOf;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by actmember on 2018. 1. 23..
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LoginActivityTest {

    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.url("/");
        server.start();
    }

    @Test
    public void clickLoginBtnAndSuccess() {
        ShadowApplication application = shadowOf(RuntimeEnvironment.application);
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        EditText empNo = (EditText) mainActivity.findViewById(R.id.editEmpNo);
        EditText editTelNo = (EditText)mainActivity.findViewById(R.id.editTelNo);
        empNo.setText("12345");
        editTelNo.setText("67890");

        // network
        server.enqueue(new MockResponse().setResponseCode(200)
                .setBody("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbXBsb3llZU51bWJlciI6IjEyMzQ1IiwiaWF0IjoxNTE2Njg3MjkwLCJleHAiOjE1MTY4NjAwOTAsImlzcyI6InNkcy5hY3QuY29hY2giLCJzdWIiOiJKaWtzYXdBdXRoIn0.9_SLyFgZwgiGem0QVH6gKo-NnfUBPdVkfm5WbXbHMoQ"));

        mainActivity.findViewById(R.id.btnLogin).performClick();

        Intent expectedIntent = new Intent(mainActivity, MyPageActivity.class);
        assertEquals(expectedIntent.getComponent(), application.getNextStartedActivity().getComponent());
    }

}
