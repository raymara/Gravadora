package raymara.edu.gravadora.Servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raymara.edu.gravadora.Modelo.Album;
import raymara.edu.gravadora.Repositorio.AlbumRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServico {
    private final AlbumRepositorio albumRepositorio;
    private final GenericoServico<Album> genericoServico;

    @Autowired
    public AlbumServico(AlbumRepositorio albumRepositorio) {
        this.albumRepositorio = albumRepositorio;

        this.genericoServico = new GenericoServico<Album>(albumRepositorio );
    }

    Optional<Album> buscaPor(String nome) {
        return Optional.ofNullable(albumRepositorio.findByNome(nome ) );
    }

    public Album buscaPor(Integer id) {
        return this.genericoServico.buscaPor(id );
    }

    @Transactional
    public Album salva(Album album ) {
        return this.genericoServico.salva(album );
    }

    @Transactional(readOnly = true)
    public List<Album> obterTodasAlbuns() {
        return genericoServico.buscaTodasAsEntities();
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoServico.excluir(id );
    }

    @Transactional
    public Album atualiza(Integer id, Album album) {
        return this.genericoServico.atualiza(album, id );
    }

}
