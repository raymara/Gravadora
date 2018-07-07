package raymara.edu.gravadora.Modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotNull
    @Size(min = 3, max = 30)
    private String nome;
    private Integer ano;

    @ManyToMany
    @JoinTable(name = "artista_album",
            joinColumns = @JoinColumn(name = "artista"),
            inverseJoinColumns = @JoinColumn(name = "album"))
    private List<Artista> artistas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "musica_album",
            joinColumns = @JoinColumn(name = "musica"),
            inverseJoinColumns = @JoinColumn(name = "album"))
    private List<Musica> musicas = new ArrayList<>();

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

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public void adicionaMusica(Musica musica) {
        musicas.add(musica);
    }

    public void adicionaArtista(Artista artista) {
        artistas.add(artista);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return Objects.equals(getCodigo(), album.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }
}
