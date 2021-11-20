package study.websocket.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import study.websocket.dto.CMRespDTO;
import study.websocket.handler.exception.CustomException;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    //에러 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CMRespDTO<?>> apiException(CustomException e){

        return new ResponseEntity<>(new CMRespDTO<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST);
    }
}
