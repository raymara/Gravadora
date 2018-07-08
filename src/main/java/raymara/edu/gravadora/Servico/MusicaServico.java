package raymara.edu.gravadora.Servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raymara.edu.gravadora.Modelo.Musica;
import raymara.edu.gravadora.Repositorio.MusicaRepositorio;
import raymara.edu.gravadora.Repositorio.filtro.MusicaFiltro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class MusicaServico {
    private final MusicaRepositorio musicaRepositorio;
    private final GenericoServico<Musica> genericoServico;

    @Autowired
    public MusicaServico(MusicaRepositorio musicaRepositorio) {
        this.musicaRepositorio = musicaRepositorio;
        this.genericoServico = new GenericoServico<Musica>(musicaRepositorio );
    }
/*
    Optional<Musica> buscaPor(String nome) {
        return Optional.ofNullable(musicaRepositorio.findByNome(nome ) );
    }
*/
    public List<Musica> buscaPor(String nome) {
        return musicaRepositorio.findByNomeContaining(nome).orElse(new ArrayList<>());
    }

    public Musica buscaPor(Integer id) {
        return this.genericoServico.buscaPor(id );
    }

    @Transactional
    public Musica salva(Musica musica ) {
        return this.genericoServico.salva(musica );
    }

    @Transactional(readOnly = true)
    public List<Musica> obterTodasMusicas() {
        return genericoServico.buscaTodasAsEntities();
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoServico.excluir(id );
    }

    @Transactional
    public Musica atualiza(Integer id, Musica musica) {
        return this.genericoServico.atualiza(musica, id );
    }

    public Page<Musica> buscaPaginada(Pageable pageable) {
        return musicaRepositorio.findAll(pageable);
    }

    public List<Musica> pesquisa(MusicaFiltro filtro) {
        return musicaRepositorio.filtrar(filtro);
    }

    public Page<Musica> pesquisa(MusicaFiltro filtro, Pageable pageable) {
        return musicaRepositorio.filtrar(filtro, pageable);
    }
}

