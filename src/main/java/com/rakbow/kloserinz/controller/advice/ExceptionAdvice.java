package com.rakbow.kloserinz.controller.advice;

import com.rakbow.kloserinz.data.common.ApiInfo;
import com.rakbow.kloserinz.data.common.ApiResult;
import com.rakbow.kloserinz.util.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Rakbow
 * @since 2022-09-12 17:31 记录异常到日志中
 */
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler({Exception.class})
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error(ApiInfo.COMMON_EXCEPTION + e.getMessage());
        for (StackTraceElement element : e.getStackTrace()) {
            log.error(element.toString());
        }

        String xRequestedWith = request.getHeader("x-requested-with");
        //为异步请求
        if ("XMLHttpRequest".equals(xRequestedWith)) {
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtil.toJson(new ApiResult(0, e.getMessage())));
        //为普通请求
        } else {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

}
