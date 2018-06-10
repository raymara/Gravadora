package raymara.edu.gravadora.Servico;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class GenericoServico <T>{
    private final JpaRepository<T, Integer> repository;

    GenericoServico(JpaRepository<T, Integer> repository ) {
        this.repository = repository;
    }


    T salva(T entity) {
        return repository.save(entity);
    }


    List<T> buscaTodasAsEntities() {
        return repository.findAll();
    }


    T atualiza(T entity, Integer codigo) {
        T entityDoBanco = this.buscaPor(codigo );
        BeanUtils.copyProperties(entity, entityDoBanco, "codigo" );
        this.salva(entityDoBanco );

        return entityDoBanco;
    }


    T buscaPor(Integer codigo) {
        return repository.findById(codigo )
                .orElseThrow(
                        () ->new EmptyResultDataAccessException(1) );
    }

    public void excluir(Integer codigo) {
        this.repository.deleteById(codigo );
    }
}
