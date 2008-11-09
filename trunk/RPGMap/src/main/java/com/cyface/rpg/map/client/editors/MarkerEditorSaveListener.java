package com.cyface.rpg.map.client.editors;

import java.util.HashMap;

import com.cyface.rpg.map.domain.entities.RPGMapOverlay;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MarkerEditorSaveListener implements ClickListener {

	HashMap<String, Widget> markerDataWidgets = null;
	Marker markerToEdit = null;
	RPGMapOverlay rpgMapOverlayToSave = null;
	
	public MarkerEditorSaveListener(Marker markerToEdit, HashMap<String, Widget> markerDataWidgets, RPGMapOverlay rpgMapOverlayToSave) {
		this.markerDataWidgets = markerDataWidgets;
		this.markerToEdit = markerToEdit;
	}
	
	public void onClick(Widget arg0) {
		TextBox nameTextBox = (TextBox) markerDataWidgets.get("name");
		rpgMapOverlayToSave.setName(nameTextBox.getText());
		Window.alert("Save Clicked, new name is " + rpgMapOverlayToSave.getName());
	}

}
