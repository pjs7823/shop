//package com.apple.shop;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//@ControllerAdvice
//public class MyExceptionHandler {
//    //모든 에러에 대한 핸들러
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handler(){
//        return ResponseEntity.status(400).body("에러남");
//    }
//
//    //Type 에러에 대한 핸들러
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<String> handler1(){
//        return ResponseEntity.status(400).body("에러남");
//    }
//}
//
// //모든 에러 처리를 한번에 설정해둘 수 있는 파일 RestApi 를 설계할 때 각 에러처리를 모아두는 파일
// //RestAPI 는 try/catch 또는 @ExceptionHandler 사용 해서 에러 검출
// //타임리프로 프론트 파일 만들어서 쓸때는 사용안함-> 이때는 error.html만 사용
