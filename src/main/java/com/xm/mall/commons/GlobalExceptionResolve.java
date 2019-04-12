package com.xm.mall.commons;

import com.xm.mall.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huyan
 * @date 2019-04-09 15:53
 */
//控制器增强处理异常
//@ControllerAdvice
public class GlobalExceptionResolve implements HandlerExceptionResolver, Ordered {

    //设置这个全局异常控制器权限为最高
    private  int order = Ordered.HIGHEST_PRECEDENCE;
    //开启异常日志
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionResolve.class);

    //设置全局异常的跳转到哪个视图(jsp)
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = doResolveException(ex, request);

        if (null == modelAndView){
            modelAndView = handlerError(ex.getMessage(),request);
        }
        return modelAndView;
    }

    //返回异常
    private ModelAndView handlerError(String msg,HttpServletRequest request){
        //如果日志开启
        if (logger.isWarnEnabled()){
            logger.warn("请求处理失败，请求URL=[{}],失败原因：{}",request.getRequestURI(),msg);
        }
        if (isAjax(request)){
            return jsonHandle(ResultCode.ERROR);
        }else {
            return  normalHandler(msg);
        }
    }

    //ajax请求头
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * 返回错误数据
     * @param resultCode 错误码
     * @return 模型视图对象
     */
    private ModelAndView jsonHandle(ResultCode resultCode) {
        ModelAndView modelAndView = new ModelAndView();
        //使用spring 内置的json视图
        modelAndView.setView(new MappingJackson2JsonView());
        modelAndView.addObject("code",resultCode.getCode());
        modelAndView.addObject("msg",resultCode.getMsg());
        return modelAndView;
    }


    /**
     * 返回错误页面
     * @param msg 错误信息
     * @return 模型视图对象
     */
    private ModelAndView normalHandler(String msg) {
        ModelAndView modelAndView = new ModelAndView("/error");
        modelAndView.addObject("errorMsg",msg);
        return modelAndView;
    }

    /**
     * 返回错误页面
     * @param resultCode 错误码
     * @param request 请求对象
     * @return 模型视图
     */
    private  ModelAndView handlerExcetion(ResultCode resultCode,HttpServletRequest request){
        //如果日志开启
        if (logger.isWarnEnabled()){
            logger.warn("请求处理失败，请求URL=[{}],失败原因：{}",request.getRequestURI(),resultCode.getMsg());
        }
        if (isAjax(request)){
            return jsonHandle(resultCode);
        }else {
            return  normalHandler(resultCode.getMsg());
        }
    }


    //解决异常

    /**
     *  实现对400,405,406,415,500,503的处理
     * @param ex 异常信息
     * @param request 请求对象
     * @return 模型视图
     */
    private  ModelAndView doResolveException(Exception ex,HttpServletRequest request){

        try {
            if (ex instanceof HttpRequestMethodNotSupportedException) {
                //405
                return handlerExcetion(ResultCode.METHOD_ERROR,request);
            }
            else if (ex instanceof NoHandlerFoundException) {
                //404
                return handlerExcetion(ResultCode.REQUEST_ERROR,request);
            }
            else if (ex instanceof BindException) {
                //400
                return handlerExcetion(ResultCode.PARAM_ERROR,request);
            }
            else if (ex instanceof HttpMessageNotWritableException || ex instanceof ConversionNotSupportedException) {
                //500
                return handlerExcetion(ResultCode.SERVICE_ERROR,request);
            }else {
                //201
                return handlerExcetion(ResultCode.ERROR,request);
            }
        }
        catch (Exception handlerEx) {
            if (logger.isWarnEnabled()) {
                logger.warn("Failure while trying to resolve exception [" + ex.getClass().getName() + "]", handlerEx);
            }
        }
        return null;

    }


    public void  setOrder(int order){
        this.order = order;
    }

    @Override
    public int getOrder() {
        return this.order;
    }

//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public Result handleException(Exception ex){
//
//        if (ex instanceof NoHandlerFoundException){
//            //404
//            return Result.of(ResultCode.REQUEST_ERROR);
//        }else if (ex instanceof MissingServletRequestParameterException){
//            //400
//            return  Result.of(ResultCode.PARAM_ERROR);
//        }else if (ex instanceof HttpRequestMethodNotSupportedException){
//            //405
//            return  Result.of(ResultCode.METHOD_ERROR);
//        }else{
//            //500
//            return Result.of(ResultCode.SERVICE_ERROR);
//        }


//    }



}
