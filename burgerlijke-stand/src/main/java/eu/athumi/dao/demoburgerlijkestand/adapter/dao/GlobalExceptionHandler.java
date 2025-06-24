package eu.athumi.dao.demoburgerlijkestand.adapter.dao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RestClientResponseException.class)
    public ModelAndView handleException(RestClientResponseException e, HttpServletRequest req, HttpServletResponse resp) {
        logger.warn(e.getMessage(), e);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("status", e.getStatusCode());
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("client-error");
        return mav;
    }
}
