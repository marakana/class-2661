package com.motorola.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.marakana.android.yamba.clientlib.YambaClient;

public class StatusActivity extends Activity implements OnClickListener {
	Button buttonPost;
	EditText editStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        
        // Workaround Motorola proxy
        System.setProperty("http.proxyHost", "wwwgate0.mot.com");
        System.setProperty("http.proxyPort", "1080");
        
        editStatus = (EditText) findViewById(R.id.edit_status);
        buttonPost = (Button) findViewById(R.id.button_post);
        buttonPost.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		String status = editStatus.getText().toString();
		
		YambaClient yambaClient = new YambaClient("student","password");
		yambaClient.updateStatus(status);
		
		Log.d("Yamba", "onClick'd status: "+status);
	}

}
