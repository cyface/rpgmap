package com.cyface.rpg.map.client;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Projection;
import com.google.gwt.maps.client.geom.TileIndex;

public class RPGMapProjection extends Projection {

	public static boolean IMAGE_WRAPS = true;
	public static boolean IMAGE_DOES_NOT_WRAP = false;
	
	int imageDimension = 65536;
	int tileSize = 256;
	Point[] pixelOrigin = null;
	double[] pixelsPerLonDegree = null;
	int[] tileBounds = null;
	boolean isWrapped = true;

	public RPGMapProjection(int maxZoom, boolean wrapAround) {
		this.pixelOrigin = new Point[maxZoom];
		this.pixelsPerLonDegree = new double[maxZoom];
		this.tileBounds = new int[maxZoom];
		this.setWrapped(wrapAround);
		int currentTileSize = this.tileSize;
		int currentTileBounds = 1;
		
		for (int zoomLevel = 0; zoomLevel < maxZoom; zoomLevel++) {
			int tileCenter = currentTileSize / 2;
			this.pixelsPerLonDegree[zoomLevel] = currentTileSize / 360;
			this.pixelOrigin[zoomLevel] = Point.newInstance(tileCenter, tileCenter);
			this.tileBounds[zoomLevel] = currentTileBounds;
			currentTileSize *= 2;
			currentTileBounds *= 2;
		}
	}

	@Override
	public Point fromLatLngToPixel(LatLng latLng, int zoomLevel) {
		Point pixelOrigin = this.pixelOrigin[zoomLevel];
		int pixelOriginX = pixelOrigin.getX();
		int pixelOriginY = pixelOrigin.getY();
		double pixelsPerLonDegreeForCurrentZoom = this.pixelsPerLonDegree[zoomLevel];
		double currentLongitude = latLng.getLongitude();
		double currentLatitude = latLng.getLatitude();
		long newX = Math.round(pixelOriginX + currentLongitude * pixelsPerLonDegreeForCurrentZoom);
		long newY = Math.round(pixelOriginY + (-2 * currentLatitude) * pixelsPerLonDegreeForCurrentZoom);
		int newXint = new Long(newX).intValue();
		int newYint = new Long(newY).intValue();
		return Point.newInstance(newXint, newYint);
	}

	@Override
	public LatLng fromPixelToLatLng(Point point, int zoomLevel, boolean unbounded) {
		Point pixelOrigin = this.pixelOrigin[zoomLevel];
		int pixelOriginX = pixelOrigin.getX();
		int pixelOriginY = pixelOrigin.getY();
		double pixelsPerLonDegreeForCurrentZoom = this.pixelsPerLonDegree[zoomLevel];
		int currentX = point.getX();
		int currentY = point.getY();
		double d = currentX - pixelOriginX / pixelsPerLonDegreeForCurrentZoom;
		double e = -0.5 * (currentY - pixelOriginY) / pixelsPerLonDegreeForCurrentZoom;
		return LatLng.newInstance(e, d, unbounded);
	
	}

	@Override
	public double getWrapWidth(int zoomLevel) {
		return this.tileBounds[zoomLevel] * this.tileSize;
	}

	@Override
	public boolean tileCheckRange(TileIndex index, int zoomLevel, int tileSize) {
		int tileBounds = this.tileBounds[zoomLevel];
		int newX = index.getX();
		if (index.getY() < 0 || index.getY() >= tileBounds) {
			return false;
		}
		if (this.isWrapped) {
			if (index.getX() < 0 || index.getX() >= tileBounds) {
				newX = index.getX() % tileBounds;
				if (index.getX() < 0) {
					newX += tileBounds;
				}
			}
		} else {
			if (index.getX() < 0 || index.getX() >= tileBounds) {
				return false;
			}
		}
		return true;
	}

	public int getImageDimension() {
		return imageDimension;
	}

	public void setImageDimension(int imageDimension) {
		this.imageDimension = imageDimension;
	}

	public boolean isWrapped() {
		return isWrapped;
	}

	public void setWrapped(boolean isWrapped) {
		this.isWrapped = isWrapped;
	}
}
