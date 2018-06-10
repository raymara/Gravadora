package raymara.edu.gravadora.Controle.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {
    private final HttpServletResponse response;
    private final Integer codigo;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Integer codigo) {
        super(source);

        this.response = response;
        this.codigo = codigo;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
