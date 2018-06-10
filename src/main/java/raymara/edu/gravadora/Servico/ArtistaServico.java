package raymara.edu.gravadora.Servico;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raymara.edu.gravadora.Modelo.Artista;
import raymara.edu.gravadora.Repositorio.ArtistaRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistaServico {
    private final ArtistaRepositorio artistaRepositorio;
    private final GenericoServico<Artista> genericoServico;

    @Autowired
    public ArtistaServico(ArtistaRepositorio artistaRepositorio) {
        this.artistaRepositorio = artistaRepositorio;

        this.genericoServico = new GenericoServico<Artista>(artistaRepositorio );
    }

    Optional<Artista> buscaPor(String nome) {
        return Optional.ofNullable(artistaRepositorio.findByNome(nome ) );
    }

    public Artista buscaPor(Integer id) {
        return this.genericoServico.buscaPor(id );
    }

    @Transactional
    public Artista salva(Artista artista ) {
        return this.genericoServico.salva(artista );
    }

    @Transactional(readOnly = true)
    public List<Artista> obterTodasArtistas() {
        return genericoServico.buscaTodasAsEntities();
    }

    @Transactional
    public void excluir(Integer id) {
        this.genericoServico.excluir(id );
    }

    @Transactional
    public Artista atualiza(Integer id, Artista artista) {

        return this.genericoServico.atualiza(artista, id );
    }

    /*@Transactional
    public Artista atualiza(Integer id, Artista artista) {
        Artista categoriaManager = this.buscaPor(id );

        if (categoriaManager == null) {
            throw new EmptyResultDataAccessException(1 );
        }
        BeanUtils.copyProperties(artista, categoriaManager, "id" );
        this.salva(categoriaManager );
        return categoriaManager;
    }*/
}

