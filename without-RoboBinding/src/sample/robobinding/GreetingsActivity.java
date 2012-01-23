package sample.robobinding;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class GreetingsActivity extends Activity {

	private RadioButton maleButton, femaleButton;
	private Spinner salutationsSpinner;
	private EditText firstnameInput, lastnameInput;
	private TextView greetingTextView;
	
	private Sex sex = Sex.Undecided;
	private Salutation salutation = Salutation.Undecided;
	private String firstname;
	private String lastname;
	private ArrayAdapter<Salutation> salutationsAdapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greetings_activity);
    
        inflateViews();
        
        registerViewListeners();
        
        updateSalutationsSpinner();
        
        updateGreetingMessage();
	}

	private void inflateViews() {
		maleButton = (RadioButton)findViewById(R.id.male_button);
		femaleButton = (RadioButton)findViewById(R.id.female_button);
		salutationsSpinner = (Spinner)findViewById(R.id.salutations_spinner);
		firstnameInput = (EditText)findViewById(R.id.firstname_input);
		lastnameInput = (EditText)findViewById(R.id.lastname_input);
		greetingTextView = (TextView)findViewById(R.id.greeting_label);
	}
	
	private void registerViewListeners() {
		maleButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				maleSelected();
			}

        });
        
        femaleButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				femaleSelected();
			}
		});
        
        salutationsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				setSalutationIndex(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
			
        });
        
        firstnameInput.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				setFirstname(s);
			}
        	
        });
        
        lastnameInput.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				setLastname(s);
			}
        	
        });
	}
	
	private void maleSelected() {
		sex = Sex.Male;
		sexUpdated();
	}

	private void femaleSelected() {
		sex = Sex.Female;
		sexUpdated();
	}
	
	private void sexUpdated() {
		salutationsSpinner.setEnabled(true);
		firstnameInput.setEnabled(true);
		updateSalutationsSpinner();
	}
	
	private void setSalutationIndex(int position) {
		salutation = getSalutations().get(position);
	}
	
	private List<Salutation> getSalutations() {
		return sex == Sex.Female ? Salutation.getFemaleSalutations() : Salutation.getMaleSalutations();
	}
	
	private void setFirstname(CharSequence s) {
		firstname = s.toString();
		lastnameInput.setEnabled(!TextUtils.isEmpty(firstname));
		updateGreetingMessage();
	}
	
	private void setLastname(CharSequence s) {
		lastname = s.toString();
		updateGreetingMessage();
	}
	
	private void updateSalutationsSpinner() {
		salutationsAdapter = new ArrayAdapter<Salutation>(this, R.layout.spinner_item, getSalutations());
        salutationsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        salutationsSpinner.setAdapter(salutationsAdapter);
	}
	
	private void updateGreetingMessage() {
		String greetingMessage;
		
		if (TextUtils.isEmpty(lastname))
			greetingMessage = "Please enter all details above";
		else
			greetingMessage = "Hello " + salutation + " " + firstname + " " + lastname + "!";
		
		greetingTextView.setText(greetingMessage);
	}
}