CREATE TABLE IF NOT EXISTS `gravadora`.`artista` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL,
  `nacionalidade` VARCHAR(100) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;
