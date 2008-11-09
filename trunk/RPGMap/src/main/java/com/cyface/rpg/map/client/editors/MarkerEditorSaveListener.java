package com.cyface.rpg.map.client.editors;

import java.util.HashMap;

import com.cyface.rpg.map.client.mapmanager.RPGMapManager;
import com.cyface.rpg.map.domain.entities.RPGMapOverlay;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MarkerEditorSaveListener implements ClickListener {

	HashMap<String, Widget> markerDataWidgets;
	Marker markerToEdit;
	RPGMapOverlay rpgMapOverlayToSave;
	PopupPanel editorPanel;
	
	public MarkerEditorSaveListener(Marker markerToEdit, HashMap<String, Widget> markerDataWidgets, RPGMapOverlay rpgMapOverlayToSave, PopupPanel editorPanel) {
		this.markerDataWidgets = markerDataWidgets;
		this.markerToEdit = markerToEdit;
		this.rpgMapOverlayToSave = rpgMapOverlayToSave;
		this.editorPanel = editorPanel;
	}
	
	public void onClick(Widget arg0) {
		TextBox nameTextBox = (TextBox) markerDataWidgets.get("name");
		rpgMapOverlayToSave.setName(nameTextBox.getText());
		RPGMapManager.saveOverlay(rpgMapOverlayToSave);
		editorPanel.hide();
	}

}
