/**
 * 
 */
package com.iamVip.mail2.rs.util;

/**
 * @author Colin
 */
public class LocationUtil {

	private static final double EARTH_RADIUS = 6378.137;

	/**
	 * 
	 */
	public LocationUtil() {
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 返回两座标点之间的公里数
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double distance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		return s;
	}


	public static void main(String[] args) {
		System.out.println(distance(39.845709, 116.370836, 39.846486, 116.370729) * 1000);
	}

}
