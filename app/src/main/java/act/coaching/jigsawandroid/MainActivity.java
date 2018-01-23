package act.coaching.jigsawandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import act.coaching.jigsawandroid.service.LoginService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText empNo = (EditText) findViewById(R.id.editEmpNo);
        final EditText editTelNo = (EditText) findViewById(R.id.editTelNo);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "login call response : get called");

                LoginService login = BaseApplication.retrofit.create(LoginService.class);
                login.login(empNo.getText().toString(), editTelNo.getText().toString()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("MainActivity", "login call response : " + response.body());
                        startActivity(new Intent(MainActivity.this, MyPageActivity.class));
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("MainActivity", "login call response : " + t.getMessage());
                        startActivity(new Intent(MainActivity.this, MyPageActivity.class));
                    }
                });
            }
        });

    }
}
