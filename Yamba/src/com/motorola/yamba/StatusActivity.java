package com.motorola.yamba;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;

public class StatusActivity extends Activity implements OnClickListener {
	Button buttonPost;
	EditText editStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_status);
        
        // Workaround Motorola proxy
//        System.setProperty("http.proxyHost", "wwwgate0.mot.com");
//        System.setProperty("http.proxyPort", "1080");
        
        editStatus = (EditText) findViewById(R.id.edit_status);
        buttonPost = (Button) findViewById(R.id.button_post);
        buttonPost.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		String status = editStatus.getText().toString();
		
		new StatusUpdateTask().execute(status);
		
		Log.d("Yamba", "onClick'd status: "+status);
	}
	
	class StatusUpdateTask extends AsyncTask<String, Void, String> {
		YambaClient yambaClient;

		@Override
		protected void onPreExecute() {
			yambaClient = new YambaClient("student","password");
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				yambaClient.updateStatus(params[0]);
				return "Successfully posted";
			} catch (Exception e) {
				Log.e("Yamba", "Failed to post", e);
				e.printStackTrace();
				return "Failed to posted";
			}				
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();
		}
	}

}
