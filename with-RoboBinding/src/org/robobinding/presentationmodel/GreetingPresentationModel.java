package org.robobinding.presentationmodel;

import java.util.List;

import org.robobinding.DependsOnStateOf;
import org.robobinding.ItemPresentationModel;
import org.robobinding.binding.viewattribute.ItemClickEvent;
import org.robobinding.presentationmodelaspects.PresentationModel;

import android.text.TextUtils;

@PresentationModel
public class GreetingPresentationModel {

	private Sex sex = Sex.Undecided;
	private Salutation salutation = Salutation.Undecided;
	private String firstname;
	private String lastname;
	
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@DependsOnStateOf("sex")
	public boolean isFirstnameInputEnabled() {
		return isSalutationsSpinnerEnabled();
	}
	
	@DependsOnStateOf("firstName")
	public boolean isLastnameInputEnabled() {
		return !TextUtils.isEmpty(firstname);
	}
	
	@DependsOnStateOf({"salutation", "firstName", "lastName"})
	public String getGreeting() {
		if (TextUtils.isEmpty(lastname))
			return "Please enter all details above";
		
		return "Hello " + salutation + " " + firstname + " " + lastname + "!";
	}
}
