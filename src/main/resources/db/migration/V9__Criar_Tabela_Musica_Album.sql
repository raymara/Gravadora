CREATE TABLE IF NOT EXISTS `gravadora`.`musica_album` (
  `musica` INT NOT NULL,
  `album` INT NOT NULL,
  PRIMARY KEY (`musica`, `album`),
  INDEX `fk_table1_album1_idx` (`album` ASC),
  CONSTRAINT `fk_table1_musica1`
    FOREIGN KEY (`musica`)
    REFERENCES `gravadora`.`musica` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_album1`
    FOREIGN KEY (`album`)
    REFERENCES `gravadora`.`album` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;