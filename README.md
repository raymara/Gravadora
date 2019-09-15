# Gravadora
Frameworks e API: Spring Boot, Spring Data JPA, API REST, Spring REST, Angular e Intellij.
Foi feita a integração contínua com o Travis CI e o uso do Flywaydb. Foi implementado filtro utilizando a API Criteria do JPA, busca paginada usando o Spring Data JPA. Integração com o swagger para geração automática da documentação. Validação de dados (Bean Validation).
O sistema controla o registro de artistas, musicais e seus álbuns. Cada álbum possui várias músicas, as quais poderão ser consultadas pelo sistema. O sistema exibir um relatório dos álbuns de um artista, o qual pode ser ordenado por nome, ano, ou duração total do álbum. Um álbum pode ter a participação de vários artistas, sem distinção. Já a música pode possuir um ou mais autores e intérpretes (todos considerados artistas).
[![Build Status](https://travis-ci.org/raymara/Gravadora.svg?branch=master)](https://travis-ci.org/raymara/Gravadora)
