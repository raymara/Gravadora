CREATE TABLE IF NOT EXISTS `gravadora`.`musica` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL,
  `duracao` INT(11) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;