package com.cyface.rpg.map.client.editors;

import java.util.HashMap;

import com.cyface.rpg.map.domain.entities.RPGMapOverlay;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MarkerEditor {

	private MapWidget parentMapWidget;
	private Marker markerToEdit;
	RPGMapOverlay rpgMapOverlayToEdit;
	HashMap<String, Widget> markerEditFields;

	public MarkerEditor(MapWidget parentMapWidget, Marker markerToEdit, RPGMapOverlay rpgMapOverlayToEdit) {
		this.parentMapWidget = parentMapWidget;
		this.markerToEdit = markerToEdit;
		this.rpgMapOverlayToEdit = rpgMapOverlayToEdit;
	}

	public void editMarker() {
		PopupPanel popupPanel = new PopupPanel();
		FlowPanel editorPanel = new FlowPanel();

		Label nameLabel = new Label("Name:");
		TextBox nameTextBox = new TextBox();
		nameTextBox.setName("name");
		nameTextBox.setMaxLength(60);
		markerEditFields.put("name", nameTextBox);

		Button saveButton = new Button("Save");
		saveButton.addClickListener(new MarkerEditorSaveListener(markerToEdit, markerEditFields, rpgMapOverlayToEdit));

		popupPanel.add(editorPanel);

		editorPanel.add(nameLabel);
		editorPanel.add(nameTextBox);
		editorPanel.add(saveButton);

		popupPanel.setPopupPosition(Window.getClientHeight() / 2, Window.getClientWidth() / 2);
		popupPanel.show();

	}

	public MapWidget getParentMapWidget() {
		return parentMapWidget;
	}

	public void setParentMapWidget(MapWidget parentMapWidget) {
		this.parentMapWidget = parentMapWidget;
	}

	public Marker getMarkerToEdit() {
		return markerToEdit;
	}

	public void setMarkerToEdit(Marker markerToEdit) {
		this.markerToEdit = markerToEdit;
	}

}
