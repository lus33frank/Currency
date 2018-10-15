package com.home.currency;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // 畫面元件
    private EditText etNTD;
    private TextView tvUSShow;
    private TextView tvJPShow;
    // 變數
    private static float rateUSD = 30.9f;   // 美金匯率


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initData();
    }

    private void findViews() {
        etNTD = findViewById(R.id.etNTD);
        tvUSShow = findViewById(R.id.tvUSShow);
        tvJPShow = findViewById(R.id.tvJPShow);
    }

    private void initData() {
        // 欄位資料初始化
        etNTD.setText("");
        tvUSShow.setText(R.string.none);
        tvJPShow.setText(R.string.none);
    }

    public void calculationOnClick(View view) {
        // 防呆
        String strNTD = etNTD.getText().toString();
        if (strNTD.equals("")) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.problem)
                    .setMessage(R.string.please_enter_ntd)
                    .setPositiveButton(R.string.ok, null)
                    .show();

        } else {
            // 計算美金
            float floatNTD = Float.parseFloat(strNTD);
            final float floatUSD = floatNTD / rateUSD;

            new AlertDialog.Builder(this)
                    .setTitle(R.string.result)
                    .setMessage(getString(R.string.usd_is) + floatUSD)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvUSShow.setText(String.valueOf(floatUSD));
                            etNTD.setText("");
                        }
                    })
                    .show();
        }
    }
}
