package org.robobinding.presentationmodel;

import java.util.List;

import org.robobinding.DependsOnStateOf;
import org.robobinding.ItemPresentationModel;
import org.robobinding.presentationmodelaspects.PresentationModel;
import org.robobinding.viewattribute.adapterview.ItemClickEvent;

import android.text.TextUtils;

@PresentationModel
public class GreetingPresentationModel {

	private Sex sex = Sex.Undecided;
	private Salutation salutation = Salutation.Undecided;
	private String firstName;
	private String lastName;
	
	public void maleSelected() {
		setSex(Sex.Male);
	}

	public void femaleSelected() {
		setSex(Sex.Female);
	}
	
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	@DependsOnStateOf("sex")
	public boolean isSalutationsSpinnerEnabled() {
		return sex != Sex.Undecided;
	}
	
	@DependsOnStateOf("sex")
	@ItemPresentationModel(SalutationItemPresentationModel.class)
	public List<Salutation> getSalutations() {
		return sex == Sex.Female ? Salutation.forFemales() : Salutation.forMales();
	}
	
	public void salutationSelected(ItemClickEvent event) {
		setSalutation(getSalutations().get(event.getPosition()));
	}
	
	public void setSalutation(Salutation salutation) {
		this.salutation = salutation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@DependsOnStateOf("sex")
	public boolean isFirstNameInputEnabled() {
		return sex != Sex.Undecided;
	}
	
	@DependsOnStateOf("firstName")
	public boolean isLastNameInputEnabled() {
		return !TextUtils.isEmpty(firstName);
	}
	
	@DependsOnStateOf({"salutation", "firstName", "lastName"})
	public String getGreeting() {
		if (TextUtils.isEmpty(lastName))
			return "Please enter all details above";
		
		return "Hello " + salutation + " " + firstName + " " + lastName + "!";
	}
}
