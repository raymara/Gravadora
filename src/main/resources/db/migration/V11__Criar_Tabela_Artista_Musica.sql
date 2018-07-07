CREATE TABLE IF NOT EXISTS `gravadora`.`artista_musica` (
  `artista` INT NOT NULL,
  `musica` INT NOT NULL,
  PRIMARY KEY (`artista`, `musica`),
  INDEX `fk_table1_musica2_idx` (`musica` ASC),
  CONSTRAINT `fk_table1_artista1`
    FOREIGN KEY (`artista`)
    REFERENCES `gravadora`.`artista` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_musica2`
    FOREIGN KEY (`musica`)
    REFERENCES `gravadora`.`musica` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;