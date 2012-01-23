package org.robobinding;

import org.robobinding.binder.Binder;
import org.robobinding.presentationmodel.GreetingPresentationModel;

import android.app.Activity;
import android.os.Bundle;

public class GreetingsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        GreetingPresentationModel greetingPresentationModel = new GreetingPresentationModel();
        Binder.bind(this, R.layout.greetings_activity, greetingPresentationModel);
    }
}