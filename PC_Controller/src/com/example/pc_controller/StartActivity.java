

package com.example.pc_controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StartActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		getSupportActionBar().hide();

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_start, container, false);
			return rootView;
		}
	}

	public void btn1KeyboardClicked(View view) {
		startActivity(new Intent(this, KeyboardActivity.class));
	}

	public void btn2TouchpadClicked(View view) {
		startActivity(new Intent(this, KeyboardActivity.class));
	}

	public void btn3GamingClicked(View view) {
		startActivity(new Intent(this, KeyboardActivity.class));
	}

}
