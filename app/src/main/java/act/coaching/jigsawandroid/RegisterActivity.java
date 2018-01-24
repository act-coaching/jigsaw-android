package act.coaching.jigsawandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import act.coaching.jigsawandroid.service.RegisterService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by actmember on 2018. 1. 23..
 */

public class RegisterActivity extends AppCompatActivity {

    RegisterService registerService;

    public RegisterService getRegisterService() {
        if(registerService == null){
            return BaseApplication.retrofit.create(RegisterService.class);
        }
        return registerService;
    }

    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init() {
        final EditText empNo = (EditText) findViewById(R.id.empNo);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText nickName = (EditText) findViewById(R.id.nickName);
        final EditText telNo = (EditText) findViewById(R.id.telNo);
        final EditText email = (EditText) findViewById(R.id.email);
        final RadioButton radioMale = (RadioButton) findViewById(R.id.radioMale);
        //RadioButton radioFemale = (RadioButton) findViewById(R.id.radioFemale);
        final EditText teamName = (EditText) findViewById(R.id.teamName);
        final EditText depName = (EditText) findViewById(R.id.depName);
        final EditText grade = (EditText) findViewById(R.id.grade);
        final EditText jobName = (EditText) findViewById(R.id.jobName);

        Button btnJoin = (Button) findViewById(R.id.btnJoin);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String gender;

                if(radioMale.isChecked()){
                    gender = "M";
                }else{
                    gender = "F";
                }

                getRegisterService().register(empNo.getText().toString(),
                        name.getText().toString(),
                        nickName.getText().toString(),
                        email.getText().toString(),
                        gender,
                        telNo.getText().toString(),
                        teamName.getText().toString(),
                        depName.getText().toString(),
                        grade.getText().toString(),
                        jobName.getText().toString()
                        ).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("RegisterActivity", "RegisterService call response : " + response.body());

                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("RegisterActivity", "RegisterService call response : " + t.getMessage());

                    }
                });
            }
        });

    }
}
