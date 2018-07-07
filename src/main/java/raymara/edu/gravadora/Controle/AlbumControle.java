package raymara.edu.gravadora.Controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import raymara.edu.gravadora.Controle.event.RecursoCriadoEvent;
import raymara.edu.gravadora.Modelo.Album;
import raymara.edu.gravadora.Modelo.Artista;
import raymara.edu.gravadora.Servico.AlbumServico;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/album")
public class AlbumControle {
    private final AlbumServico albumServico;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    public AlbumControle(AlbumServico albumServico) {
        this.albumServico = albumServico;
    }


    @PostMapping
    public ResponseEntity<?> cria(@Validated @RequestBody Album album, HttpServletResponse response) {
        Album albumSalvo = albumServico.salva(album );
        publisher.publishEvent(new RecursoCriadoEvent(this, response, albumSalvo.getCodigo() ));

        return ResponseEntity.status(HttpStatus.CREATED).body(albumSalvo );
    }

    @GetMapping
    public ResponseEntity<?> listaAlbuns() {

        List<Album> albuns = albumServico.obterTodasAlbuns();

        if (albuns.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(albuns);

        }
    }

    @GetMapping("/{id}")
    public Album buscaPor(@PathVariable Integer id) {
        return albumServico.buscaPor(id );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void excluir(@PathVariable Integer id) {
        albumServico.excluir(id );
    }


    @PutMapping("/{id}")
    public ResponseEntity<Album> atualizar(@PathVariable Integer id,
                                             @Validated @RequestBody Album album ) {

        Album categoriaManager = albumServico.atualiza(id, album );
        return ResponseEntity.ok(categoriaManager );
    }

}
