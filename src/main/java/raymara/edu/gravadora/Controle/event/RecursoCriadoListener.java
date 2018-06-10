package raymara.edu.gravadora.Controle.event;


import org.springframework.context.ApplicationListener;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

    @Override
    public void onApplicationEvent(RecursoCriadoEvent eventoRecursoCriado) {
        this.adcionaHeaderLocation( eventoRecursoCriado.getResponse(), eventoRecursoCriado.getCodigo() );
    }


    private void adcionaHeaderLocation(HttpServletResponse response, Integer codigo) {

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{codigo}")
                .buildAndExpand(codigo)
                .toUri();

        response.setHeader("Location", uri.toString() );
    }
}
