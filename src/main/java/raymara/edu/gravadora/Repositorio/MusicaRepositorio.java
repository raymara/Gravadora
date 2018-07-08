package raymara.edu.gravadora.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raymara.edu.gravadora.Modelo.Musica;
import raymara.edu.gravadora.Repositorio.musica.MusicaRepositorioQuery;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicaRepositorio extends JpaRepository<Musica, Integer>, MusicaRepositorioQuery {
    Musica findByNome(String nome);
    Optional<List<Musica>> findByNomeContaining(String nome);
}
