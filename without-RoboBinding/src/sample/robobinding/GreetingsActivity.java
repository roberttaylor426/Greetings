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
	private EditText firstNameInput, lastNameInput;
	private TextView greetingTextView;
	
	private Sex sex = Sex.Undecided;
	private Salutation salutation = Salutation.Undecided;
	private String firstName;
	private String lastName;
	private ArrayAdapter<Salutation> salutationsAdapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greetings_activity);
    
        inflateViews();
        
        registerViewListeners();
        
        updateSalutationsSpinner();
	}

	private void inflateViews() {
		maleButton = (RadioButton)findViewById(R.id.male_button);
		femaleButton = (RadioButton)findViewById(R.id.female_button);
		salutationsSpinner = (Spinner)findViewById(R.id.salutations_spinner);
		firstNameInput = (EditText)findViewById(R.id.first_name_input);
		lastNameInput = (EditText)findViewById(R.id.last_name_input);
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
        
        firstNameInput.addTextChangedListener(new TextWatcher(){

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
				setFirstName(s);
			}
        	
        });
        
        lastNameInput.addTextChangedListener(new TextWatcher(){

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
				setLastName(s);
			}
        	
        });
	}
	
	private void maleSelected() {
		sex = Sex.Male;
		salutationsSpinner.setEnabled(true);
		firstNameInput.setEnabled(true);
		updateSalutationsSpinner();
	}
	
	private void femaleSelected() {
		sex = Sex.Female;
		salutationsSpinner.setEnabled(true);
		firstNameInput.setEnabled(true);
		updateSalutationsSpinner();
	}
	
	private void setSalutationIndex(int position) {
		salutation = getSalutations().get(position);
	}
	
	private List<Salutation> getSalutations() {
		return sex == Sex.Female ? Salutation.getFemaleSalutations() : Salutation.getMaleSalutations();
	}
	
	private void setFirstName(CharSequence s) {
		firstName = s.toString();
		lastNameInput.setEnabled(!TextUtils.isEmpty(firstName));
		updateGreetingMessage();
	}
	
	private void setLastName(CharSequence s) {
		lastName = s.toString();
		updateGreetingMessage();
	}
	
	private void updateSalutationsSpinner() {
		salutationsAdapter = new ArrayAdapter<Salutation>(this, R.layout.spinner_item, getSalutations());
        salutationsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        salutationsSpinner.setAdapter(salutationsAdapter);
        
      //final LayoutInflater layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        
//		{
//
//	@Override
//	public View getDropDownView(int position, View convertView,	ViewGroup parent) {
//		
//		TextView dropdownView = (TextView)layoutInflater.inflate(R.layout.spinner_dropdown_item, null);
//		dropdownView.setText(getItem(position).toString());
//		this.set
//		return dropdownView;
//	}
//	
//};
	}
	
	private void updateGreetingMessage() {
		String greetingMessage;
		
		if (TextUtils.isEmpty(lastName))
			greetingMessage = "Please enter all details above";
		else
			greetingMessage = "Hello " + salutation + " " + firstName + " " + lastName + "!";
		
		greetingTextView.setText(greetingMessage);
	}
}