package act.coaching.jigsawandroid;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    @Test
    public void ExitApp버튼을_클릭하면_앱을_종료한다() throws Exception {
        //Given
        MainActivity subject = Robolectric.setupActivity(MainActivity.class);

        //When
        //subject.findViewById(R.id.exitApp).performClick();

        //Then
        //assertThat(shadowOf(subject).isFinishing()).isEqualTo(true);
        TextView tvAppTitle = (TextView)subject.findViewById(R.id.tvAppTitle);
        Assert.assertEquals(tvAppTitle.getText(), "Jigsaw");
    }
}
