package com.tony.qrcode.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tony.qrcode.R;
import com.tony.qrcode.scan.CaptureActivity;
import com.tony.qrcode.scan.EncodingHandler;

public class MainActivity extends Activity {

    private Button mScan;
    private TextView mText;
    private EditText mInput;
    private Button mGenerate;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initWindow();
    }

    private void initViews() {
        mScan = (Button) findViewById(R.id.scan);
        mText = (TextView) findViewById(R.id.text);
        mInput = (EditText) findViewById(R.id.input);
        mGenerate = (Button) findViewById(R.id.generate);
        mImage = (ImageView) findViewById(R.id.image);
    }

    private void initWindow() {
        mScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(MainActivity.this, "你现在可以扫描条形码或二维码", Toast.LENGTH_LONG).show();
                Intent startScan = new Intent(MainActivity.this,CaptureActivity.class);
                startActivityForResult(startScan, 0);
            }
        });

        mGenerate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String input = mInput.getText().toString();
                if (input.equals("")) {
                    Toast.makeText(MainActivity.this, "请输入文本", Toast.LENGTH_LONG).show();
                }else {
                    try {
                        Bitmap qrcode = EncodingHandler.createQRCode(input, 400);
                        mImage.setImageBitmap(qrcode);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            mText.setText(result);
            if (data.getParcelableExtra("bitmap") != null){
                mImage.setImageBitmap((Bitmap)data.getParcelableExtra("bitmap"));
            }
        }
    }
}
