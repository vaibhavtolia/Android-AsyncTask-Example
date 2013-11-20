package com.tolia.async;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AsyncActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async);
		
		startAsyncTask();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.async, menu);
		return true;
	}
	
	private class AyncAddView extends AsyncTask<String, Void, String[]> {
		
		@Override
	    protected String[] doInBackground(String... urls) {
			String[] response = new String[5];
			for(int i =0; i<5;i++){
				response[i] = i+" count";
				try {
                    Thread.sleep(100);
                    
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
			}
			return response;
	    }

	    @Override
	    protected void onPostExecute(String[] result) {
	    	RelativeLayout wrapper = (RelativeLayout) findViewById(R.id.wrapper);
	    	for(int i=0; i< result.length; i++){
	    		Log.v("called", result[i]);
	    		View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.test_layout,null);
		    	TextView t = (TextView) view.findViewById(R.id.textView1);
		    	t.setText(result[i]);
		    	wrapper.addView(view);
	    	}
	    	Integer j = wrapper.getChildCount();
	    	Log.v("child count",j.toString());
	    }
		
	}
	
	public void startAsyncTask(){
		AyncAddView add_view = new AyncAddView();
		add_view.execute("");
	}

}
