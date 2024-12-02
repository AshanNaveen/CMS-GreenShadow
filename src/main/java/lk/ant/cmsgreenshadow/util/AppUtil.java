package lk.ant.cmsgreenshadow.util;

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
}
