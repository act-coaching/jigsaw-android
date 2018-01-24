package act.coaching.jigsawandroid;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    @Test
    public void 앱_시작시_앱_타이틀을_확인한다() throws Exception {
        //Given
        MainActivity subject = Robolectric.setupActivity(MainActivity.class);

        //When
        //subject.findViewById(R.id.exitApp).performClick();

        //Then
        //assertThat(shadowOf(subject).isFinishing()).isEqualTo(true);
        TextView tvAppTitle = (TextView)subject.findViewById(R.id.tvAppTitle);
        Assert.assertEquals(tvAppTitle.getText(), "Jigsaw");
    }

    @Test
    public void 회원가입하러가기_버튼을_클릭하여_회원가입화면으로_이동한다() throws Exception{
        //Given
        MainActivity subject = Robolectric.setupActivity(MainActivity.class);

        //When
        subject.findViewById(R.id.tvRegister).performClick();

        //Then
        assertThat(shadowOf(subject).getNextStartedActivity().getComponent().getClassName()).isEqualTo(RegisterActivity.class.getName());
    }
}
