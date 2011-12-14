package org.robobinding.presentationmodel;

import org.robobinding.itempresentationmodel.ItemPresentationModel;

public class SalutationItemPresentationModel implements ItemPresentationModel<Salutation> {

	private Salutation salutation;

	public String getSalutation(){
		return salutation.toString();
	}
	
	@Override
	public void updateData(int index, Salutation salutation) {
		this.salutation = salutation;
	}

}
