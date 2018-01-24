package act.coaching.jigsawandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import act.coaching.jigsawandroid.service.LoginService;
import act.coaching.jigsawandroid.util.JigsawPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LoginService loginService;

    void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    LoginService getLoginService() {
        if(loginService == null) {
            return BaseApplication.retrofit.create(LoginService.class);
        }
        return loginService;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }

    private void initLayout() {
        TextView tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        final EditText empNo = (EditText) findViewById(R.id.editEmpNo);
        final EditText editTelNo = (EditText) findViewById(R.id.editTelNo);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "loginService call response : get called");

                getLoginService().login(empNo.getText().toString(), editTelNo.getText().toString()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("MainActivity", "loginService call response : " + response.body());
                        JigsawPreference.getInstance(MainActivity.this).putString(JigsawPreference.TOKEN, response.body());
                        startActivity(new Intent(MainActivity.this, MyPageActivity.class));
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("MainActivity", "loginService call response : " + t.getMessage());
                        startActivity(new Intent(MainActivity.this, MyPageActivity.class));
                    }
                });
            }
        });
    }
}
