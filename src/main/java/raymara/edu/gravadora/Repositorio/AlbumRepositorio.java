package raymara.edu.gravadora.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raymara.edu.gravadora.Modelo.Album;

@Repository
public interface AlbumRepositorio extends JpaRepository<Album, Integer> {
    Album findByNome(String nome);
}