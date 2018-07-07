CREATE TABLE IF NOT EXISTS `gravadora`.`artista_album` (
  `artista` INT NOT NULL,
  `album` INT NOT NULL,
  PRIMARY KEY (`artista`, `album`),
  INDEX `fk_artista_album_album1_idx` (`album` ASC),
  CONSTRAINT `fk_artista_album_artista`
    FOREIGN KEY (`artista`)
    REFERENCES `gravadora`.`artista` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_artista_album_album1`
    FOREIGN KEY (`album`)
    REFERENCES `gravadora`.`album` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
