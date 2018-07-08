package raymara.edu.gravadora.Repositorio.musica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raymara.edu.gravadora.Modelo.Musica;
import raymara.edu.gravadora.Repositorio.filtro.MusicaFiltro;

import java.util.List;

public interface MusicaRepositorioQuery {
    List<Musica> filtrar(MusicaFiltro filtro);

    Page<Musica> filtrar(MusicaFiltro filtro, Pageable pageable);
}
