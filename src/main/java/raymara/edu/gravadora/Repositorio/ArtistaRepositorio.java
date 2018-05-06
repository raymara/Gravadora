package raymara.edu.gravadora.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raymara.edu.gravadora.Modelo.Artista;

@Repository
public interface ArtistaRepositorio extends JpaRepository<Artista, Integer> {
    Artista findByNome(String nome);
}
