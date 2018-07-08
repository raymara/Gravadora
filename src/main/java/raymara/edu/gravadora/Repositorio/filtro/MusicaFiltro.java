package raymara.edu.gravadora.Repositorio.filtro;

public class MusicaFiltro {
    private String nome;
    private Integer duracaoDe;
    private Integer duracaoAte;
    private Integer artistaCodigo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDuracaoDe() {
        return duracaoDe;
    }

    public void setDuracaoDe(Integer duracaoDe) {
        this.duracaoDe = duracaoDe;
    }

    public Integer getDuracaoAte() {
        return duracaoAte;
    }

    public void setDuracaoAte(Integer duracaoAte) {
        this.duracaoAte = duracaoAte;
    }

    public Integer getArtistaCodigo() {
        return artistaCodigo;
    }

    public void setArtistaCodigo(Integer artistaCodigo) {
        this.artistaCodigo = artistaCodigo;
    }
}
