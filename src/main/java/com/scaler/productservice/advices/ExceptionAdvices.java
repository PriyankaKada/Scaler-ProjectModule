package com.scaler.productservice.advices;

import com.scaler.productservice.dtos.ErrorResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*It sits between Controller and dispatcher Servlet
If you want to change anything before sending it to DS
you can change it using Adviser
*
* */
@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(Exception.class)
    public String handleException(){
        return "Something Went Wrong";
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto handleRunTimeException(RuntimeException e){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage("Runtime Exception inside Product Controller"+e.getLocalizedMessage());
        errorResponseDto.setStatusCode(234);
        return  errorResponseDto;
    }
}
