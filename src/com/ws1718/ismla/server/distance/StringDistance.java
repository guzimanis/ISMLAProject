package com.ws1718.ismla.server.distance;

/**
 * should be implemented by all the distance measure algorithms
 * @author johannes
 */
public interface StringDistance {
	float getDistance(String s1, String s2);
}