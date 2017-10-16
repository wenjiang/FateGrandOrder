package com.wenjiang.wenbiao.fategrandorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.domain.AbstractInteractor;
import com.wenjiang.wenbiao.domain.Executor;
import com.wenjiang.wenbiao.domain.HelloWorldInteractor;
import com.wenjiang.wenbiao.domain.HelloWorldInteractorImpl;
import com.wenjiang.wenbiao.domain.MainPresenter;
import com.wenjiang.wenbiao.domain.MainPresenterImpl;
import com.wenjiang.wenbiao.domain.MainThread;

public class MainActivity extends AppCompatActivity implements MainPresenter.View{
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        MainPresenterImpl mainPresenter = new MainPresenterImpl(null, null, this);
        mainPresenter.showHello();
    }

    @Override
    public void showHelloWorld() {
        textView.setText("Hello World");
    }
}
