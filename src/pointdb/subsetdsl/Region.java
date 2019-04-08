package pointdb.subsetdsl;

import java.util.Arrays;

import pointdb.base.Point2d;
import pointdb.base.PolygonUtil;
import pointdb.base.Rect;

public class Region {	
	public final Rect bbox;
	public final Point2d[] polygonPoints; //nullable if no polygon
	
	private Region(Rect bbox, Point2d[] polygonPoints) {
		this.bbox = bbox;
		this.polygonPoints = polygonPoints;
	}
	
	public static Region ofRect(Rect rect) {
		return new Region(rect, null);
	}
	
	public static Region ofPolygon(Point2d[] polygonPoints) {
		return new Region(Rect.of_polygon(polygonPoints), polygonPoints);
	}
	
	public boolean isBbox() {
		return polygonPoints == null;
	}
	
	public double getArea() {
		return polygonPoints == null ? bbox.getArea() : PolygonUtil.area_of_polygon(polygonPoints);
	}

	@Override
	public String toString() {
		return "Region [bbox=" + bbox + ", polygonPoints=" + Arrays.toString(polygonPoints) + "]";
	}
	
	public Region toBboxRegion() {
		return polygonPoints==null ? this : Region.ofRect(bbox);
	}
}