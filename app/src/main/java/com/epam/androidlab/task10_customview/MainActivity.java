package com.epam.androidlab.task10_customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);

        final SmileView smile = findViewById(R.id.smileView);

        smile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (smile.getState()){
                    case SmileView.SAD:
                        smile.setState(SmileView.HAPPY);
                        break;
                    case SmileView.HAPPY:
                        smile.setState(SmileView.SAD);
                        break;
                }
            }
        });
    }
}
