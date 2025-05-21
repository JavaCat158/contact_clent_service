package ru.ibragimov.clientcontactservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Глобальный обработчик исключений для REST-контроллеров.
 *
 * Позволяет централизованно обрабатывать ошибки и возвращать
 * читаемые и стандартизированные ответы клиенту в формате JSON.
 *
 * Обрабатываются:
 * - ошибки валидации (`@Valid`)
 * - IllegalArgumentException
 * - RuntimeException
 * - все прочие исключения
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обработка ошибок IllegalArgumentException.
     *
     * @param e исключение
     * @return JSON-ответ с кодом 400 Bad Request
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        return buildResponse(HttpStatus.BAD_REQUEST, "400");
    }

    /**
     * Обработка всех RuntimeException.
     *
     * @param e исключение
     * @return JSON-ответ с кодом 404 Not Found
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        return buildResponse(HttpStatus.NOT_FOUND, "404");
    }

    /**
     * Обработка всех прочих исключений.
     *
     * @param e исключение
     * @return JSON-ответ с кодом 500 Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception e) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "500");
    }

    /**
     * Обработка ошибок валидации полей (аннотация @Valid).
     *
     * @param e исключение MethodArgumentNotValidException
     * @return JSON-ответ с детальным списком ошибок и кодом 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("timestamp", LocalDateTime.now());
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("message", "Ошибка валидации!");

        List<String> errorsList = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        errors.put("errors", errorsList);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Универсальный метод построения ответа.
     *
     * @param status  HTTP-статус
     * @param message сообщение для клиента
     * @return готовый JSON-ответ
     */
    private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
