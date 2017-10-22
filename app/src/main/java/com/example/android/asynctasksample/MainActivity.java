package com.example.android.asynctasksample;

import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.asynctasksample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        CounterAsyncTask task = new CounterAsyncTask();
        task.execute(1, 100, 3200);
    }

    private void DisplayInteger(int i) {
            binding.textView.setText(i + "");

    }

    class CounterAsyncTask extends AsyncTask<Integer, Integer, String> {


        @Override
        protected String doInBackground(Integer... numbers) {
            for (int i = numbers[0]; i <= numbers[1]; i++) {
                publishProgress(i);
                try {
                    Thread.sleep(numbers[2]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            DisplayInteger(values[0]);


        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, "Finished Counting!", Toast.LENGTH_SHORT).show();
        }
    }


}
