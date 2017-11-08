package minhal.tomerbu.edu.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //declare your views here: ...
    private Button btnMinus, btnPlus;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //find view by id here...
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        tvResult = (TextView) findViewById(R.id.tvResult);

        //...setOnClickListener(...)
        btnMinus.setOnClickListener(minusListener);
        btnPlus.setOnClickListener(plusListener);


        stepperValue = read();
        updateUI();
    }

    //TODO: Get this value from disk
    int stepperValue; // = 0.
    //Integer x ;// = null wrapper classes (extends Object) //immutable
    /**
     * Minus clicked
     */
    //assign a variable to the anonymous listener.
    View.OnClickListener minusListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setStepperValue(stepperValue - 1);
            updateUI();
        }
    };

    /**
     * Plus clicked
     */
    //assign a variable to the anonymous listener.
    View.OnClickListener plusListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setStepperValue(stepperValue + 1);
            updateUI();
        }
    };

    //alt + insert (setter)
    public void setStepperValue(int stepperValue) {
        this.stepperValue = stepperValue;
        updateUI();
        write(stepperValue);
    }

    private void updateUI() {
        tvResult.setText(String.valueOf(stepperValue));
    }

    //Singleton - Shared Object.
    //Shared  Preferences

    private void write(int val){
        //1) reference to the shared object (sharedPreferences)

        //singleton...? No new...?
        //allows us to Read data:
        SharedPreferences stepperValues = getSharedPreferences("StepperValues"/*xml file name...*/, MODE_PRIVATE);

        //2) reference to the editor of the sharedPreferences
        //Writer
        SharedPreferences.Editor editor = stepperValues.edit();

        //3) editor.put...(key, value).
        editor.putInt("value", val);
        editor.putString("firstName", "moshe");
        //4) commit / apply
        //Don't forget to save!
        //Why doesn't step 3 does not save automatically?


//        editor.commit();// Main Thread. -> save();
//
//
//
//
        editor.apply();//new Thread -> save();
        //
    }


    private int read(){
        //1) get a reference to the shared preferences object. (singleton...Not new... getShared)
        SharedPreferences stepperValues = getSharedPreferences("StepperValues"/*xml file name...*/, MODE_PRIVATE);

        //int val = obj.getIntValue()
        int val = stepperValues.getInt("value", 0/*defaultValue*/);
        //3) return val
        return val;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
