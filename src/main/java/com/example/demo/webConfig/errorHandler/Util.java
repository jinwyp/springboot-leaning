package com.example.demo.webConfig.errorHandler;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Util {



    /**
     * 只返回指定包中的异常堆栈信息
     * https://github.com/0opslab/utils/blob/master/src/main/java/com/opslab/util/ExceptionUtil.java
     * 可以通过使用我开源的工具包获取
     *
     * @param exceptionOriginal 异常信息
     * @param packageName 只转换某个包下的信息
     * @param showLines 只显示几行
     * @return string
     */

    public static String stackTraceToString(Throwable exceptionOriginal, int showLines, String packageName) {
        StringWriter sw = new StringWriter();
        exceptionOriginal.printStackTrace(new PrintWriter(sw, true));

        String tempString = sw.toString();

        if (packageName == null) {
            return tempString;
        }

        String[] arrs = tempString.split("\n");
        StringBuffer sbuf = new StringBuffer();
        sbuf.append(arrs[0] + "\n");

        if (showLines > 0 ) {

            if (showLines > arrs.length) {
                showLines = arrs.length;
            }

            for (int i = 1; i < showLines; i++) {
                String temp = arrs[i];
                sbuf.append(temp + "\n");
            }
        } else {

            if (packageName.isEmpty()) {
                return tempString;
            }

            for (int i = 1; i < arrs.length; i++) {
                String temp = arrs[i];
                if (temp != null && temp.indexOf(packageName) > 0) {
                    sbuf.append(temp + "\n");
                }
            }
        }

        return sbuf.toString();
    }




    /**
     * https://stackoverflow.com/questions/1490821/whats-the-best-way-to-get-the-current-url-in-spring-mvc
     *
     * @param request
     * @return
     */
    public static String makeUrl(HttpServletRequest request) {

        if (request.getQueryString() == null) {
            return request.getRequestURL().toString();
        }

        return request.getRequestURL().toString() + "?" + request.getQueryString();
    }




    /**
     * https://stackoverflow.com/questions/30461823/spring-mvc-detect-ajax-request
     * @param request
     * @return
     */
    public static  boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWithHeader = request.getHeader("X-Requested-With");
        String contentType = request.getHeader("Content-Type");

        if (contentType.contains("application/json")) {
            return true;
        }

        if (requestedWithHeader != null && "XMLHttpRequest".equals(requestedWithHeader)) {
            return true;
        }

        return false;

    }



}
