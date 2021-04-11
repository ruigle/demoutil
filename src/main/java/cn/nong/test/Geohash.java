package cn.nong.test;

import org.junit.Test;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.io.GeohashUtils;

/**
 *@author lirui
 *@version 创建时间：2019-7-16 下午6:08:15
 *
 */
public class Geohash {
	public static void main(String[] args) {
		double lat=39.3898710000;
		double lon=117.0506000000;
		String geo_code = GeohashUtils.encodeLatLon(lat, lon);
		System.out.println(geo_code);
		
	}
	
	@Test
	public void demo01(){
		// 移动设备经纬度
		double lon1 = 116.3125333347639, lat1 = 39.98355521792821;
		// 商户经纬度
		double lon2 = 116.312528, lat2 = 39.983733;
		SpatialContext geo = SpatialContext.GEO;
		double distance = geo.calcDistance(geo.makePoint(lon1, lat1), geo.makePoint(lon2, lat2)) * DistanceUtils.DEG_TO_KM;
		System.out.println(distance);// KM


		
		
	}

}
