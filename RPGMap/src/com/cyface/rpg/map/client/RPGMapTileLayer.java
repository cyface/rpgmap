package com.cyface.rpg.map.client;

import com.google.gwt.maps.client.CopyrightCollection;
import com.google.gwt.maps.client.TileLayer;
import com.google.gwt.maps.client.geom.Point;

public class RPGMapTileLayer extends TileLayer {

	String folderName = "RPGMap-tiles/";
	String fileExtension = ".jpg";
	boolean isPng = false;

	public RPGMapTileLayer(CopyrightCollection copyrights, int minResolution, int maxResolution) {
		super(copyrights, minResolution, maxResolution);
	}

	@Override
	public double getOpacity() {
		return 100;
	}

	@Override
	public String getTileURL(Point tile, int zoomLevel) {
		double c = Math.pow(2, zoomLevel);

		long d = tile.getX();
		long e = tile.getY();
		String f = "t";
		for (long g = 0; g < zoomLevel; g++) {
			c = c / 2;
			if (e < c) {
				if (d < c) {
					f += "q";
				} else {
					f += "r";
					d -= c;
				}
			} else {
				if (d < c) {
					f += "t";
					e -= c;
				} else {
					f += "s";
					d -= c;
					e -= c;
				}
			}
		}
		String tileUrl = folderName + f + fileExtension;
		return tileUrl;
	}

	@Override
	public boolean isPng() {
		return this.isPng;
	}
	
	public void setPng(boolean isPng) {
		this.isPng = isPng;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

}
