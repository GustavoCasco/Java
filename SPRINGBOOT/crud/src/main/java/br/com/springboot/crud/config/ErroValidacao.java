package br.com.springboot.crud.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.springboot.crud.DTO.ErroValid;

@RestControllerAdvice
public class ErroValidacao {
	
	@Autowired
	private MessageSource message;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroValid> handle(MethodArgumentNotValidException valid) {
		
		List<ErroValid> dto = new ArrayList<ErroValid>();
		List<FieldError> fieldErrors = valid.getBindingResult().getFieldErrors();
		
		fieldErrors.stream().forEach(e -> {
			String mensagem = message.getMessage(e, LocaleContextHolder.getLocale());
			ErroValid erro = new ErroValid(e.getField(), mensagem);
			dto.add(erro);
		});
		
		return dto;
	}
	
	
	
	
}
