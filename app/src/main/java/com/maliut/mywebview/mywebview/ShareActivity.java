package com.maliut.mywebview.mywebview;

import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {

    private ClipboardManager clipboard;
    private TextView textView;
    private EditText titleEdit, descriptionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        textView = (TextView) findViewById(R.id.textUrl);
        titleEdit = (EditText) findViewById(R.id.editTitle);
        descriptionEdit = (EditText) findViewById(R.id.editDescription);
        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        String urlToShare = clipboard.getPrimaryClip().getItemAt(0).getText().toString();
        if (urlToShare.startsWith("http")) textView.setText(urlToShare);
    }

    public void onClickShare(View view) {
        Intent intent = new Intent(ShareActivity.this, MainActivity.class);
        intent.putExtra("url", textView.getText());
        intent.putExtra("title", titleEdit.getText());
        intent.putExtra("description", descriptionEdit.getText());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ShareActivity.this, MainActivity.class);
            setResult(RESULT_CANCELED, intent);
            finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
