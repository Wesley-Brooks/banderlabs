package bander.fileman.activity;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import bander.fileman.R;

/** Preferences activity for FileMan. */
public class Preferences extends PreferenceActivity implements Preference.OnPreferenceChangeListener {
	private static final String		KEY_SORTORDER		= "sortOrder";
	private static final String		KEY_TEXTSIZE		= "textSize";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preferences);
		
		ListPreference textSizePreference = (ListPreference) findPreference(KEY_TEXTSIZE);
		textSizePreference.setOnPreferenceChangeListener(this);
		setTextSizeSummary(textSizePreference);

		ListPreference sortOrderPreference = (ListPreference) findPreference(KEY_SORTORDER);
		sortOrderPreference.setOnPreferenceChangeListener(this);
		setSortOrderSummary(sortOrderPreference);
	}
	
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		final String key = preference.getKey();

		if (KEY_SORTORDER.equals(key)) {
			ListPreference sortOrderPreference = (ListPreference) preference;
			sortOrderPreference.setValue((String) newValue);
			setSortOrderSummary(sortOrderPreference);
			return false;
		}
		if (KEY_TEXTSIZE.equals(key)) {
			ListPreference textSizePreference = (ListPreference) preference;
			textSizePreference.setValue((String) newValue);
			setTextSizeSummary(textSizePreference);
			return false;
		}

		return true;
	}
	
	private void setSortOrderSummary(ListPreference preference) {
		preference.setSummary(getString(R.string.pref_sortOrderSummary, preference.getEntry()));	
	}

	private void setTextSizeSummary(ListPreference preference) {
		preference.setSummary(getString(R.string.pref_textSizeSummary, preference.getEntry()));	
	}

	
}
