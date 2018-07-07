package raymara.edu.gravadora.Modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Musica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotNull
    @Size(min = 3, max = 30)
    private String nome;
    private Integer duracao;

    @ManyToMany
    @JoinTable(name = "musica_album",
            joinColumns = @JoinColumn(name = "musica"),
            inverseJoinColumns = @JoinColumn(name = "album"))
    private List<Album> albuns = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "artista_musica",
            joinColumns = @JoinColumn(name = "artista"),
            inverseJoinColumns = @JoinColumn(name = "musica"))
    private List<Artista> artistas = new ArrayList<>();

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public void adicionaAlbum(Album album) {
        albuns.add(album);
    }

    public void adicionaArtista(Artista artista) {
        artistas.add(artista);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Musica)) return false;
        Musica musica = (Musica) o;
        return Objects.equals(getCodigo(), musica.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }
}