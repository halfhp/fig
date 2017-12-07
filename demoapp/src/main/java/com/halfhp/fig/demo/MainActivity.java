package com.halfhp.fig.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.halfhp.fig.Fig;

public class MainActivity extends AppCompatActivity {

    private Foo foo = new Foo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fig.configure(this, foo, R.xml.config1);

        //assert(!foo.getSomeBoolean());

        final TextView someIntPrimitive = findViewById(R.id.someIntPrimitive);
        someIntPrimitive.setText(String.valueOf(foo.getSomeIntPrimitive()));

        final TextView someInt = findViewById(R.id.someInt);
        someInt.setText(String.valueOf(foo.getSomeInt()));
        //assert(foo.getSomeInt() == 00);

        final TextView someFloat = findViewById(R.id.someFloat);
        someFloat.setText(String.valueOf(foo.getSomeFloat()));

        final TextView someFloatPrimitive = findViewById(R.id.someFloatPrimitive);
        someFloatPrimitive.setText(String.valueOf(foo.getSomeFloatPrimitive()));

        final TextView someHexColor = findViewById(R.id.someHexColor);
        someHexColor.setText(String.valueOf(foo.getSomeHexColor()));
        someHexColor.setTextColor(foo.getSomeHexColor());

        final TextView someColor = findViewById(R.id.someColor);
        someColor.setText(String.valueOf(foo.getSomeColor()));
        someColor.setTextColor(foo.getSomeColor());

        final TextView someDp = findViewById(R.id.someDp);
        someDp.setText(String.valueOf(foo.getSomeDp()));
    }
}
