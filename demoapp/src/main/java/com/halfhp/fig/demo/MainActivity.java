package com.halfhp.fig.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.halfhp.fig.Fig;

public class MainActivity extends AppCompatActivity {

    private Foo foo = new Foo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fig.configure(this, foo, R.xml.config1);

        final TextView someInt = findViewById(R.id.someInt);
        someInt.setText(String.valueOf(foo.getSomeInt()));
        assert(foo.getSomeInt() == 00);

        final TextView someDp = findViewById(R.id.someDp);
        someDp.setText(String.valueOf(foo.getSomeDp()));
    }
}
