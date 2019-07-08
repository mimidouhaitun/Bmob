package com.example.tarena.bmobdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tarena.bmobdemo.R;
import com.example.tarena.bmobdemo.bean.MyUser;


import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.listener.SaveListener;

public class RegistActivity extends Activity {

    @BindView(R.id.et_regist_username)
    EditText etUsername;
    @BindView(R.id.et_regist_password)
    EditText etPassword;
    @BindView(R.id.rg_regist_gender)
    RadioGroup rgGender;

    String avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_regist_regist)
    public void regist(View view){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "请输入完整", Toast.LENGTH_SHORT).show();
            return;
        }

        MyUser user = new MyUser();
        user.setUsername(username);

        String md5 = new String(Hex.encodeHex(DigestUtils.sha(password)));

        user.setPassword(md5);

        boolean gender = true;
        if(rgGender.getCheckedRadioButtonId()==R.id.rb_regist_girl){
            gender = false;
        }
        user.setGender(gender);
        user.setAvatar(avatar);

        //将user对象中的信息保存到Bmob服务器
        user.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(RegistActivity.this, "注册失败，失败原因"+i+":"+s, Toast.LENGTH_SHORT).show();
            }
        });






    }
}
