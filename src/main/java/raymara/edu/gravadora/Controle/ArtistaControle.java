package raymara.edu.gravadora.Controle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import raymara.edu.gravadora.Modelo.Artista;
import raymara.edu.gravadora.Servico.ArtistaServico;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaControle {

    private  final ArtistaServico artistaServico;

    @Autowired
    public ArtistaControle(ArtistaServico artistaServico) {
        this.artistaServico = artistaServico;
    }

    @PostMapping
    public ResponseEntity<Artista> cria(@RequestBody Artista artista, HttpServletResponse response) {

        Artista artistaSalva = artistaServico.salva(artista);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(artistaSalva.getCodigo())
                .toUri();

        //response.setHeader("Location", uri.toString() );

        return  ResponseEntity.created(uri).body(artistaSalva );
    }

    @GetMapping
    public ResponseEntity<?> listaArtistas() {

        List<Artista> artistas = artistaServico.obterTodasArtistas();

        if (artistas.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(artistas);

        }
    }

    @GetMapping("/{id}")
    public Artista buscaPor(@PathVariable Integer id) {
        return artistaServico.buscaPor(id );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void excluir(@PathVariable Integer id) {
        artistaServico.excluir(id );
    }


    @PutMapping("/{id}")
    public ResponseEntity<Artista> atualizar(@PathVariable Integer id,
                                               @Validated @RequestBody Artista artista ) {

        Artista categoriaManager = artistaServico.atualiza(id, artista );
        return ResponseEntity.ok(categoriaManager );
    }
}


