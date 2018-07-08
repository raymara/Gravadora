package raymara.edu.gravadora.Repositorio.musica;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import raymara.edu.gravadora.Modelo.Musica;
import raymara.edu.gravadora.Repositorio.filtro.MusicaFiltro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaRepositorioImpl implements MusicaRepositorioQuery {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Musica> filtrar(MusicaFiltro filtro) {

        // Select p From Musica p
        // 1 - Usamos o CriteriaBuilder(cb) para criar a CriteriaQuery (cq)
        // com a tipagem do tipo a ser selecionado (Musica)
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Musica> cq = cb.createQuery(Musica.class);

        // 2 - clausula from e joins
        Root<Musica> musicaRoot = cq.from(Musica.class);

        // 3 - adiciona as restrições (os predicados) que serão passadas na clausula where
        Predicate[] restricoes = this.criaRestricoes(filtro, cb, musicaRoot);

        // 4 - monta a consulta com as restrições
        cq.select(musicaRoot).where(restricoes).orderBy(cb.asc(musicaRoot.get("nome")));

        // 5 - cria e executa a consula
        return manager.createQuery(cq).getResultList();
    }

    private Predicate[] criaRestricoes(MusicaFiltro filtro, CriteriaBuilder cb, Root<Musica> musicaRoot) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(filtro.getNome())) {
            predicates.add(cb.like(musicaRoot.get("nome"), "%" + filtro.getNome() + "%" ));
        }

        if (filtro.getDuracaoDe() != null) {
            predicates.add(cb.ge(musicaRoot.get("duracao"), filtro.getDuracaoDe()));
        }

        if( filtro.getDuracaoAte() != null) {
            predicates.add(cb.le(musicaRoot.get("duracao"), filtro.getDuracaoAte()));
        }

        if (filtro.getArtistaCodigo() != null) {
            // antes faz o join com categorias
            Path<Integer> artistaPath = musicaRoot.join("artistas").<Integer>get("codigo");

            // semelhante a clausula "on" com o critério de junção
            predicates.add (cb.equal(artistaPath, filtro.getArtistaCodigo()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    @Override
    public Page<Musica> filtrar(MusicaFiltro filtro, Pageable pageable) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Musica> cq = cb.createQuery(Musica.class);
        Root<Musica> musicaRoot = cq.from(Musica.class);
        Predicate[] restricoes = this.criaRestricoes(filtro, cb, musicaRoot  );

        // Monta a consulta com as restrições de paginação
        TypedQuery<Musica> query = manager.createQuery(cq);
        adicionaRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(filtro) );
    }

    private void adicionaRestricoesDePaginacao(TypedQuery<Musica> query, Pageable pageable) {
        Integer paginaAtual = pageable.getPageNumber();
        Integer totalObjetosPorPagina = pageable.getPageSize();
        Integer primeiroObjetoDaPagina = paginaAtual * totalObjetosPorPagina;

        // 0 a n-1, n - (2n -1), ...
        query.setFirstResult(primeiroObjetoDaPagina);
        query.setMaxResults(totalObjetosPorPagina);
    }

    private Long total(MusicaFiltro filtro) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Musica> musicaRoot = cq.from(Musica.class);
        Predicate[] restricoes = criaRestricoes(filtro, cb, musicaRoot);
        cq.where(restricoes);
        cq.select(cb.count(musicaRoot));

        return manager.createQuery(cq).getSingleResult();
    }
}
