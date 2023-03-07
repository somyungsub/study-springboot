package study.springboot.security.oauth.jwttutorial.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import study.springboot.security.oauth.jwttutorial.dto.ErrorDto;
import study.springboot.security.oauth.jwttutorial.execption.DuplicateMemberException;
import study.springboot.security.oauth.jwttutorial.execption.NotFoundMemberException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;

public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
  @ResponseStatus(CONFLICT)
  @ExceptionHandler(value = { DuplicateMemberException.class })
  @ResponseBody
  protected ErrorDto conflict(RuntimeException ex, WebRequest request) {
    return new ErrorDto(CONFLICT.value(), ex.getMessage());
  }

  @ResponseStatus(FORBIDDEN)
  @ExceptionHandler(value = { NotFoundMemberException.class, AccessDeniedException.class })
  @ResponseBody
  protected ErrorDto forbidden(RuntimeException ex, WebRequest request) {
    return new ErrorDto(FORBIDDEN.value(), ex.getMessage());
  }
}
