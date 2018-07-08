package raymara.edu.gravadora.Controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import raymara.edu.gravadora.Controle.event.RecursoCriadoEvent;
import raymara.edu.gravadora.Modelo.Musica;
import raymara.edu.gravadora.Repositorio.filtro.MusicaFiltro;
import raymara.edu.gravadora.Servico.MusicaServico;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/musica")
public class MusicaControle {
    private  final MusicaServico musicaServico;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    public MusicaControle(MusicaServico musicaServico) {
        this.musicaServico = musicaServico;
    }

    @GetMapping("/todos")
    public Page<Musica> todosMusicas(Pageable pageable) {
        return musicaServico.buscaPaginada(pageable);
    }

    @GetMapping
    public List<Musica> pesquisar(MusicaFiltro filtro) {
        return musicaServico.pesquisa(filtro);
    }

    @GetMapping("/paginacao")
    public Page<Musica> pesquisarComPaginacao(MusicaFiltro filtro, Pageable pageable) {
        return musicaServico.pesquisa(filtro, pageable);
    }

    @GetMapping("/buscapornome")
    public List<Musica> buscaPeloNome(@RequestParam String nome) {
        return musicaServico.buscaPor(nome);
    }

    @PostMapping
    public ResponseEntity<?> cria(@Validated @RequestBody Musica musica, HttpServletResponse response) {
        Musica musicaSalvo = musicaServico.salva(musica );
        publisher.publishEvent(new RecursoCriadoEvent(this, response, musicaSalvo.getCodigo() ));

        return ResponseEntity.status(HttpStatus.CREATED).body(musicaSalvo );
    }
/*
    @GetMapping
    public ResponseEntity<?> listaMusicas() {

        List<Musica> musicas = musicaServico.obterTodasMusicas();

        if (musicas.isEmpty()) {
            //return ResponseEntity.notFound().build();
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(musicas);

        }
    }
*/
    @GetMapping("/{id}")
    public Musica buscaPor(@PathVariable Integer id) {
        return musicaServico.buscaPor(id );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void excluir(@PathVariable Integer id) {
        musicaServico.excluir(id );
    }


    @PutMapping("/{id}")
    public ResponseEntity<Musica> atualizar(@PathVariable Integer id,
                                             @Validated @RequestBody Musica musica ) {

        Musica categoriaManager = musicaServico.atualiza(id, musica );
        return ResponseEntity.ok(categoriaManager );
    }
}
