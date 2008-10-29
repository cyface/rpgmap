package com.cyface.rpg.map.client;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Projection;
import com.google.gwt.maps.client.geom.TileIndex;

public class RPGMapProjection extends Projection {

	public static boolean IMAGE_WRAPS = true;
	public static boolean IMAGE_DOES_NOT_WRAP = false;

	long imageDimension = 65536;
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
			this.pixelsPerLonDegree[zoomLevel] = currentTileSize / 360.0;
			this.pixelOrigin[zoomLevel] = Point.newInstance(tileCenter, tileCenter);
			this.tileBounds[zoomLevel] = currentTileBounds;
			currentTileSize *= 2;
			currentTileBounds *= 2;
		}
	}

	@Override
	public Point fromLatLngToPixel(LatLng latLng, int zoomLevel) {
		Point pixelOrigin = this.pixelOrigin[zoomLevel];
		double pixelsPerLonDegreeForCurrentZoom = this.pixelsPerLonDegree[zoomLevel];
		long newX = Math.round(latLng.getLongitude() * pixelsPerLonDegreeForCurrentZoom + pixelOrigin.getX());
		long newY = Math.round(((-2 * latLng.getLatitude()) * pixelsPerLonDegreeForCurrentZoom) + pixelOrigin.getY());
		Point newPoint = Point.newInstance(new Long(newX).intValue(), new Long(newY).intValue());
		return newPoint;
	}

	@Override
	public LatLng fromPixelToLatLng(Point point, int zoomLevel, boolean unbounded) {
		Point pixelOrigin = this.pixelOrigin[zoomLevel];
		double pixelsPerLonDegreeForCurrentZoom = this.pixelsPerLonDegree[zoomLevel];
		double d = (point.getX() - pixelOrigin.getX()) / pixelsPerLonDegreeForCurrentZoom;
		double e = -0.5 * (point.getY() - pixelOrigin.getY()) / pixelsPerLonDegreeForCurrentZoom;
		return LatLng.newInstance(e, d, unbounded);

	}

	@Override
	public double getWrapWidth(int zoomLevel) {
		return this.tileBounds[zoomLevel] * this.tileSize;
	}

	@Override
	public boolean tileCheckRange(TileIndex index, int zoomLevel, int tileSize) {
		double tileBounds = this.tileBounds[zoomLevel];
		double newX = index.getX();
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

	public long getImageDimension() {
		return imageDimension;
	}

	public void setImageDimension(long imageDimension) {
		this.imageDimension = imageDimension;
	}

	public boolean isWrapped() {
		return isWrapped;
	}

	public void setWrapped(boolean isWrapped) {
		this.isWrapped = isWrapped;
	}
}
