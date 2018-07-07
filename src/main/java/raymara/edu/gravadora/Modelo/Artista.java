package raymara.edu.gravadora.Modelo;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Artista implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotNull
    @Size(min = 3, max = 30)
    private String nome;
    private String nacionalidade;

    @ManyToMany
    @JoinTable(name = "artista_musica",
            joinColumns = @JoinColumn(name = "artista"),
            inverseJoinColumns = @JoinColumn(name = "musica"))
    private List<Musica> musicas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "artista_album",
            joinColumns = @JoinColumn(name = "artista"),
            inverseJoinColumns = @JoinColumn(name = "album"))
    private List<Album> albuns = new ArrayList<>();

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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }


    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }

    public void adicionaMusica(Musica musica) {
        musicas.add(musica);
    }

    public void adicionaAlbum(Musica musica) {
        musicas.add(musica);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artista)) return false;
        Artista artista = (Artista) o;
        return Objects.equals(getCodigo(), artista.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }
}
