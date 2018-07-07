package raymara.edu.gravadora.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raymara.edu.gravadora.Modelo.Musica;

@Repository
public interface MusicaRepositorio extends JpaRepository<Musica, Integer> {
    Musica findByNome(String nome);
}
