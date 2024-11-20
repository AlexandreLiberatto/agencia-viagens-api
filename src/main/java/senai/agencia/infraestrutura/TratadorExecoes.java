package senai.agencia.infraestrutura;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorExecoes {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarEcecao404(){
        return ResponseEntity.notFound().build();
    }
}
