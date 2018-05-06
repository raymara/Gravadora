package raymara.edu.gravadora.Servico;


import org.springframework.beans.factory.annotation.Autowired;
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
}

