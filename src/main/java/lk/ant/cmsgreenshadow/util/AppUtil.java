package lk.ant.cmsgreenshadow.util;

import org.springframework.data.geo.Point;

import java.util.Base64;

/**
 * @author Naveen Theekshana
 * @date 11/23/2024
 * @project CMSGreenShadow
 */
public class AppUtil {
    public static String toBase64Pic(byte[] pic) {
        return Base64.getEncoder().encodeToString(pic);
    }

    public static Point parseLocation(String location) {
        String[] coordinates = location.split(",");
        if (coordinates.length == 2) {
            double latitude = Double.parseDouble(coordinates[0]);
            double longitude = Double.parseDouble(coordinates[1]);
            return new Point((int) latitude, (int) longitude);
        }
        throw new IllegalArgumentException("Invalid location format");
    }
}
