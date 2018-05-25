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

    @Autowired
    public ArtistaServico(ArtistaRepositorio artistaRepositorio) {
        this.artistaRepositorio = artistaRepositorio;
    }

    Optional<Artista> buscaPor(String nome) {
        return Optional.ofNullable(artistaRepositorio.findByNome(nome ) );
    }

    public Artista buscaPor(Integer id) {
        Optional<Artista> optionalArtista = artistaRepositorio.findById(id );
        return optionalArtista.orElse(null);
    }

    @Transactional
    public Artista salva(Artista artista ) {
        return this.artistaRepositorio.save(artista );
    }

    @Transactional(readOnly = true)
    public List<Artista> obterTodasArtistas() {
        return artistaRepositorio.findAll();
        //return new ArrayList<>();
    }

    @Transactional
    public void excluir(Integer id) {
        artistaRepositorio.deleteById(id );
    }

    @Transactional
    public Artista atualiza(Integer id, Artista artista) {
        Artista categoriaManager = this.buscaPor(id );

        if (categoriaManager == null) {

            throw new EmptyResultDataAccessException(1 );
        }

        BeanUtils.copyProperties(artista, categoriaManager, "id" );

        this.salva(categoriaManager );

        return categoriaManager;
    }
}

