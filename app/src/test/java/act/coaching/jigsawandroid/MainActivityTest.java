package act.coaching.jigsawandroid;

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
    public void ExitApp버튼을_클릭하면_앱을_종료한다() throws Exception {
        MainActivity subject = Robolectric.setupActivity(MainActivity.class);
        subject.findViewById(R.id.exitApp).performClick();
        assertThat(shadowOf(subject).isFinishing()).isEqualTo(true);
    }
}
