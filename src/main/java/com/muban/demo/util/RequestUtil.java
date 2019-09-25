package com.muban.demo.util;


import org.apache.commons.lang.StringUtils;


import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 和请求相关的工具类，方便提取一些参数值
 *
 * @author huxin@163.org
 */
public class RequestUtil {


    // 获得request的参数部分
//    @SuppressWarnings("unchecked")
//    public static <T> T getPara(HttpServletRequest request, String para, T defaultValue) {
//        T value = defaultValue;
//        String strValue = request.getParameter(para);
//        if (strValue != null) {
//            try {
//                if (defaultValue instanceof Long){
//                    return (T) Long.valueOf(strValue);
//                }
//
//                value = ;
//            } catch (NumberFormatException ignore) {
//            }
//        }
//        return value;
//    }

    public static String getStringPara(HttpServletRequest request, String para) {
        String str = request.getParameter(para);
        return StringUtils.trimToNull(str);
    }

    public static long getLongPara(HttpServletRequest request, String para, long defaultValue) {
        long value = defaultValue;
        String strValue = request.getParameter(para);
        if (strValue != null) {
            try {
                value = Long.parseLong(strValue);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static boolean getBooleanPara(HttpServletRequest request, String para, boolean defaultValue) {
        boolean value = defaultValue;
        String strValue = request.getParameter(para);
        if (strValue != null) {
            try {
                value = Boolean.parseBoolean(strValue);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static int getIntPara(HttpServletRequest request, String para, int defaultValue) {
        int value = defaultValue;
        String strValue = request.getParameter(para);
        if (strValue != null) {
            try {
                value = Integer.parseInt(strValue);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static List<String> getStringListPara(HttpServletRequest request, String para, String split) {
        String param = request.getParameter(para);
        if (StringUtils.isBlank(param)) {
            return new ArrayList<String>();
        }
        String[] strs = param.split(split);
        if (strs.length <= 0) {
            return new ArrayList<String>();
        }
        List<String> list = new ArrayList<String>();
        for (String str : strs) {
            list.add(str);
        }
        return list;
    }

    public static List<Long> getLongListPara(HttpServletRequest request, String para, String split) {
        List<String> list = getStringListPara(request, para, split);
        if (list == null || list.size() <= 0) {
            return new ArrayList<Long>();
        }
        List<Long> result = new ArrayList<Long>();
        for (String str : list) {
            result.add(Long.parseLong(str));
        }
        return result;
    }

    public static List<Integer> getIntegerListPara(HttpServletRequest request, String para, String split) {
        List<String> list = getStringListPara(request, para, split);
        if (list == null || list.size() <= 0) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>();
        for (String str : list) {
            result.add(Integer.parseInt(str));
        }
        return result;
    }

    // 获得request的参数部分end

    // 获得request的attribute部分start

    public static long getLongAttr(HttpServletRequest request, String attr, long defaultValue) {
        long value = defaultValue;
        Object l = request.getAttribute(attr);
        if (l != null) {
            try {
                value = Long.parseLong(l.toString());
            } catch (Exception e) {
            }
        }
        return value;
    }

    public static int getIntAttr(HttpServletRequest request, String attr, int defaultValue) {
        int value = defaultValue;
        Object i = request.getAttribute(attr);
        if (i != null) {
            try {
                value = Integer.parseInt(i.toString());
            } catch (Exception e) {
            }
        }
        return value;
    }

    public static String getStringAttr(HttpServletRequest request, String attr, String defaultValue) {
        String value = defaultValue;
        Object s = (Object) request.getAttribute(attr);
        if (s != null) {
            value = s.toString();
        }
        return value;
    }

    // 获得request的attribute部分end

    // 获得request的header部分start

    public static long getLongHeader(HttpServletRequest request, String head, long defaultValue) {
        long value = defaultValue;
        String strValue = request.getHeader(head);
        if (strValue != null) {
            try {
                value = Long.parseLong(strValue);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static int getIntHeader(HttpServletRequest request, String head, int defaultValue) {
        int value = defaultValue;
        String strValue = request.getHeader(head);
        if (strValue != null) {
            try {
                value = Integer.parseInt(strValue);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }



    public static String getStringHeaderIgnoreCase(HttpServletRequest request, String head, String defaultValue) {
        @SuppressWarnings("unchecked")
        Enumeration<String> e = request.getHeaderNames();
        String key;
        while (e.hasMoreElements()) {
            key = e.nextElement();
            if (key.equalsIgnoreCase(head)) {
                return request.getHeader(key);
            }
        }
        return defaultValue;
    }

    public static long getLongHeaderIgnoreCase(HttpServletRequest request, String head, long defaultValue) {
        long value = defaultValue;
        String strValue = getStringHeaderIgnoreCase(request, head, null);
        if (strValue != null) {
            try {
                value = Long.parseLong(strValue);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    public static int getIntHeaderIgnoreCase(HttpServletRequest request, String head, int defaultValue) {
        int value = defaultValue;
        String strValue = getStringHeaderIgnoreCase(request, head, null);
        if (strValue != null) {
            try {
                value = Integer.parseInt(strValue);
            } catch (NumberFormatException ignore) {
            }
        }
        return value;
    }

    // 获得request的header部分start

    public static int parseRequestInt(HttpServletRequest request, String paramName, int defaultValue) {
        String sParamValue = request.getParameter(paramName);
        int paramValue = defaultValue;
        if (null != sParamValue) {
            try {
                paramValue = Integer.valueOf(sParamValue);
            } catch (Exception e) {
//                logger.error("invalid request para found:[name=" + paramName + ", value=" + sParamValue + "]");
            }
        }
        return paramValue;
    }

    public static boolean parseRequestBool(HttpServletRequest request, String paramName, boolean defaultValue) {
        String sParamValue = request.getParameter(paramName);
        boolean paramValue = defaultValue;
        if (null != sParamValue) {
            try {
                paramValue = Boolean.valueOf(sParamValue);
            } catch (Exception e) {
//                logger.error("invalid request para found:[name=" + paramName + ", value=" + sParamValue + "]");
            }
        }
        return paramValue;
    }

    public static long parseRequestLong(HttpServletRequest request, String paramName, long defaultValue) {
        String sParamValue = request.getParameter(paramName);
        long paramValue = defaultValue;
        if (null != sParamValue) {
            try {
                paramValue = Long.valueOf(sParamValue);
            } catch (Exception e) {
//                logger.error("invalid request para found:[name=" + paramName + ", value=" + sParamValue + "]");
            }
        }
        return paramValue;
    }

    public static String parseRequestString(HttpServletRequest request, String paramName, String defaultValue) {
        String sParamValue = request.getParameter(paramName);
        String paramValue = defaultValue;
        if (null != sParamValue) {
            paramValue = sParamValue;
        }
        return paramValue;
    }

    public static boolean isPC(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null) {
            userAgent = userAgent.toLowerCase();
            if (userAgent.contains("android") || userAgent.contains("iphone")) {
                return false;
            }
            Pattern p = Pattern.compile("Windows\\sPhone");
            Matcher m = p.matcher(userAgent);
            if (m.find()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解析userAgent，获取来源，0为PC(默认)，1为android，2为iphone，3为winPhone
     */
    public static int fromByUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null) {
            userAgent = userAgent.toLowerCase();
            if (userAgent.contains("android")) {
                return 1;
            }
            if (userAgent.contains("iphone")) {
                return 2;
            }
            Pattern p = Pattern.compile("Windows\\sPhone");
            Matcher m = p.matcher(userAgent);
            if (m.find()) {
                return 3;
            }
        }
        return 0;
    }

    public static boolean isFromWeixinOrWeiboOrQq(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null) {
            userAgent = userAgent.toLowerCase();
            if ((userAgent.contains("iphone"))
                    && (userAgent.contains("micromessenger") || userAgent.contains("weibo") || userAgent.contains("qq"))) {
                return true;
            }
        }
        return false;
    }

//    public static boolean isInnerMobile(HttpServletRequest request) {
//        String lofterInnerBrowser = CookieUtil.getCookieValue(request, "LofterInnerBrowser");
//        if (StringUtils.isNotBlank(lofterInnerBrowser)) {
//            return true;
//        }
//        return false;
//    }

    public static String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encodeUrlGBK(String url) {
        try {
            return URLEncoder.encode(url, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decodeUrl(String url) {
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTargetParameter(HttpServletRequest request) {
        String target = request.getParameter("target");
        if (target != null && !"".equals(target)) {
            try {
                target = new String(target.getBytes("ISO-8859-1"), "UTF-8");
            } catch (Exception e) {
            }
        }
        return target;
    }

    public static long getCurrentGMTTime() {
        long lastTime = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(lastTime);
        cal.add(Calendar.HOUR, -8);
        return cal.getTimeInMillis();
    }

    public static String decodeUrlGBK(String url) {
        try {
            return URLDecoder.decode(url, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decodeRequestParam(String name) {
        try {
            name = new String(name.getBytes("ISO8859_1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static String decodeRequestParamGBK(String name) {
        try {
            name = new String(name.getBytes("ISO8859_1"), "gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static boolean isFromSearchEngine(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null) {
            return false;
        }
        boolean fromSearchEngine = userAgent.startsWith("Googlebot") || userAgent.startsWith("Baiduspider");
        return fromSearchEngine;
    }

    public static String getRealPath(HttpServletRequest request) {
        String realPath = request.getRequestURI() + "?" + getQueryString(request);
        return realPath;
    }

    public static String getQueryString(HttpServletRequest request) {
        List<String> params = new ArrayList<String>();
        try {
            Enumeration<?> e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String name = (String) e.nextElement();
                String value = request.getParameter(name);
                value = StringUtils.trim(value);
                // int valueLen = value.length();
                // if (valueLen > 20)
                // value = StringUtils.substring(value, 20) + ",len:"
                // + valueLen;
                params.add(name + "=" + value);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return StringUtils.join(params.iterator(), "&");
    }

    /**
     * 检查请求参数中是否包含某个参数
     *
     * @param request     请求
     * @param queryString 参数名称
     * @return boolean true,if contains.
     */
    public static boolean containsQueryString(HttpServletRequest request, String queryString) {
        Enumeration<?> e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            if (StringUtils.equalsIgnoreCase(queryString, name)) {
                return true;
            }
        }
        return false;
    }


    public static Map<String, String> getHeaderMap(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<String, String>();
        Enumeration it = request.getHeaderNames();
        while (it.hasMoreElements()) {
            String name = (String) it.nextElement();
            headers.put(name, request.getHeader(name));
        }
        return headers;
    }

    public static boolean isFromWeixinApp(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        ua = StringUtils.lowerCase(ua);
        return StringUtils.contains(ua, "micromessenger");
    }

//    public static Map<String, String> getParameterMap(HttpServletRequest request) {
//        Map<String, String> result = new HashMap<String, String>();
//        @SuppressWarnings("unchecked")
//        Map<String, ?> map = request.getParameterMap();
//        for (Entry<String, ?> entry : map.entrySet()) {
//            String name = getString(request, entry.getKey());
//            Object obj = entry.getValue();
//            String value = null;
//            if (obj == null) {
//                value = null;
//            } else if (obj instanceof String[]) {
//                String[] arr = (String[]) obj;
//                if (arr.length > 0) {
//                    value = getString(request, arr[0]);
//                }
//            } else if (obj instanceof String) {
//                value = getString(request, (String) obj);
//            } else {
//                value = getString(request, obj.toString());
//            }
//            result.put(name, value);
//        }
//        return result;
//    }

//    private static String getString(HttpServletRequest request, String value) {
//        if (LoftConstants.HTTP_METHOD_POST.equalsIgnoreCase(request.getMethod())) {
//            return value;
//        }
//        if (value != null) {
//            try {
//                value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return value;
//    }
//
//    public static String parseDeviceType(HttpServletRequest request) {
//        if (request == null)
//            return "";
//
//        String product = RequestUtil.parseRequestString(request, LoftConstants.PARA_PRODUCT, null);
//        if (product != null) {
//            product = product.toLowerCase();
//            if (product.startsWith("lofter-android")) {
//                return "android";
//            } else if (product.startsWith("lofter-iphone") || product.startsWith("lofter-ipad")) {
//                return "ios";
//            }
//        }
//
//        String ua = RequestUtil.getStringHeaderIgnoreCase(request, "User-Agent", "").toLowerCase();
//        if (ua.contains("lofter-iphone") || ua.contains("lofter-ipad")) {
//            return "ios";
//        } else if (ua.contains("lofter-android")) {
//            return "android";
//        }
//
//        String uri = request.getRequestURI();
//        // if(StringUtils.isNotBlank(uri)&&uri.indexOf("/mobile/")==0) {
//        if (StringUtils.isNotBlank(uri) && uri.contains("/mobile/")) {
//            return "wap";
//        }
//        return "web";
//    }
//
//    public static AccessSource parseAccessSource(HttpServletRequest request) {
//        if (request == null)
//            return AccessSource.UNKOWN;
//
//        String product = RequestUtil.parseRequestString(request, LoftConstants.PARA_PRODUCT, null);
//        if (product != null) {
//            product = product.toLowerCase();
//            if (product.startsWith("lofter-android")) {
//                return AccessSource.ANDROID;
//            } else if (product.startsWith("lofter-iphone") ) {
//                return AccessSource.IPHONE;
//            } else if (product.startsWith("lofter-ipad")) {
//                return AccessSource.IPAD;
//            }
//        }
//
//        String ua = RequestUtil.getStringHeaderIgnoreCase(request, "User-Agent", "").toLowerCase();
//        if (ua.contains("lofter-android")) {
//            return AccessSource.ANDROID;
//        } else if (ua.contains("lofter-iphone") ) {
//            return AccessSource.IPHONE;
//        } else if (ua.contains("lofter-ipad")) {
//            return AccessSource.IPAD;
//        }
//
//        String uri = request.getRequestURI();
//        // if(StringUtils.isNotBlank(uri)&&uri.indexOf("/mobile/")==0) {
//        if (StringUtils.isNotBlank(uri) && uri.contains("/mobile/")) {
//            return AccessSource.MOBILE;
//        }
//        return AccessSource.PC;
//    }
//
//    public static String parseDeviceId(HttpServletRequest request) {
//        if (request == null)
//            return "";
//        return CookieUtil.getCookieValue(request, LoftConstants.FINGERPRINT_COOKIE_NAME);
//    }
//
//    public static boolean needRedirectMhost(HttpServletRequest request) {
//        return isFromWeixinOrWeiboOrQq(request) && StringUtils.isEmpty(request.getParameter("mhost"));
//    }


}
