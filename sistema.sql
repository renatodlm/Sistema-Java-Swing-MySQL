-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 12-Out-2019 às 10:36
-- Versão do servidor: 5.7.26
-- versão do PHP: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistema`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `adiantamentos`
--

DROP TABLE IF EXISTS `adiantamentos`;
CREATE TABLE IF NOT EXISTS `adiantamentos` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cod_despesa` int(11) DEFAULT NULL,
  `descricao` text,
  `valor` text,
  `data` text,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `adiantamentos`
--

INSERT INTO `adiantamentos` (`codigo`, `cod_despesa`, `descricao`, `valor`, `data`) VALUES
(1, 5, 'SERVIÇO', '350.00', '2019-10-10');

-- --------------------------------------------------------

--
-- Estrutura da tabela `alicota`
--

DROP TABLE IF EXISTS `alicota`;
CREATE TABLE IF NOT EXISTS `alicota` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `ali_iss` double(6,2) DEFAULT NULL,
  `ali_data` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `alicota`
--

INSERT INTO `alicota` (`codigo`, `ali_iss`, `ali_data`) VALUES
(1, 3.50, 'Terça-feira, 8 de Janeiro de 2019'),
(3, 3.50, 'Terça-feira, 8 de Janeiro de 2019'),
(4, 3.60, 'Terça-feira, 15 de Janeiro de 2019'),
(5, 3.55, 'Terça-feira, 15 de Janeiro de 2019'),
(6, 3.31, 'Terça-feira, 26 de Março de 2019'),
(7, 3.30, 'Sexta-feira, 29 de Março de 2019'),
(8, 3.50, 'Sexta-feira, 29 de Março de 2019');

-- --------------------------------------------------------

--
-- Estrutura da tabela `bancos`
--

DROP TABLE IF EXISTS `bancos`;
CREATE TABLE IF NOT EXISTS `bancos` (
  `codigo` int(11) NOT NULL,
  `banco_nome` varchar(255) DEFAULT NULL,
  `banco_num` varchar(255) DEFAULT NULL,
  `data` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `bancos`
--

INSERT INTO `bancos` (`codigo`, `banco_nome`, `banco_num`, `data`) VALUES
(1, 'Banco do Brasil S.A', '001', ''),
(2, 'Banco Bradesco S.A', '237', ''),
(3, 'Banco Santander S.A.', '033', ''),
(4, 'Itaú Unibanco S.A', '341', ''),
(5, 'Caixa Econômica Federal', '104', ''),
(6, 'HSBC Bank Brasil S.A.', '339', ''),
(7, 'Banco Safra', '422', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cadastro_ferias`
--

DROP TABLE IF EXISTS `cadastro_ferias`;
CREATE TABLE IF NOT EXISTS `cadastro_ferias` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cod_func` int(11) DEFAULT NULL,
  `data_admissao` varchar(20) DEFAULT NULL,
  `data_uferias` varchar(20) DEFAULT NULL,
  `data_vferias` varchar(20) DEFAULT NULL,
  `obs` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cidade`
--

DROP TABLE IF EXISTS `cidade`;
CREATE TABLE IF NOT EXISTS `cidade` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `fk_cod_estado` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_cidade_estado_idx` (`fk_cod_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cidade`
--

INSERT INTO `cidade` (`codigo`, `nome`, `fk_cod_estado`) VALUES
(1, 'BELO HORIZONTE', 1),
(2, 'SÃO PAULO', 25),
(3, 'GUARULHOS', 25),
(4, 'CUIABÁ', 12),
(5, 'RIO DE JANEIRO', 19),
(6, 'RIO BRANCO', 2),
(7, 'MACAPÁ', 4),
(8, 'MANAUS', 5),
(9, 'MACEIÓ', 3),
(10, 'BELÉM', 14),
(11, 'PORTO VELHO', 22),
(12, 'BOA VISTA', 23),
(13, 'PALMAS', 27),
(14, 'SALVADOR', 28),
(15, 'FORTALEZA', 7),
(16, 'SÃO LUÍS', 11),
(17, 'RECIFE', 17),
(18, 'TERESINA', 18),
(19, 'NATAL', 20),
(20, 'ARACAJU', 26),
(21, 'GOIÂNIA', 10),
(22, 'CAMPO GRANDE', 13),
(23, 'BRASÍLIA', 8),
(24, 'VITÓRIA', 6),
(25, 'CURITIBA', 16),
(26, 'PORTO ALEGRE', 21),
(27, 'FLORIANÓPOLIS', 24),
(28, 'JI-PARANÁ', 22),
(29, 'VILHENA', 22),
(30, 'SANTOS', 25);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cobertura`
--

DROP TABLE IF EXISTS `cobertura`;
CREATE TABLE IF NOT EXISTS `cobertura` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cober_nome` varchar(255) DEFAULT NULL,
  `cober_valor` double(6,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cobertura`
--

INSERT INTO `cobertura` (`codigo`, `cober_nome`, `cober_valor`) VALUES
(1, 'Empréstimos', 10.00),
(2, 'Avulso', 2.00),
(3, 'Aluguel', 10.00),
(4, 'Conta de Luz', NULL),
(5, 'Conta de Agua', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `contas_bancos`
--

DROP TABLE IF EXISTS `contas_bancos`;
CREATE TABLE IF NOT EXISTS `contas_bancos` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cod_func` int(11) DEFAULT NULL,
  `cod_banco` int(11) DEFAULT NULL,
  `banco_conta` varchar(255) DEFAULT NULL,
  `banco_tipo` varchar(255) DEFAULT NULL,
  `banco_agencia` varchar(255) DEFAULT NULL,
  `banco_op` int(11) DEFAULT NULL,
  `banco_obs` text,
  PRIMARY KEY (`codigo`),
  KEY `fk_banco_funcionario` (`cod_func`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `contas_pagar`
--

DROP TABLE IF EXISTS `contas_pagar`;
CREATE TABLE IF NOT EXISTS `contas_pagar` (
  `pk_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `fk_codigo_pessoa` int(10) DEFAULT NULL,
  `descricao` text,
  `data` text NOT NULL,
  `vencimento` text NOT NULL,
  `pagamento` text NOT NULL,
  `fk_tipo_pagamento` text,
  `observacao` text,
  `situacao` smallint(1) DEFAULT NULL,
  `valor` double(6,2) DEFAULT NULL,
  `Processos` int(11) DEFAULT NULL,
  `Repasse` text,
  `banco` int(11) DEFAULT NULL,
  `FuncionarioCheck` text,
  PRIMARY KEY (`pk_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `contas_receber`
--

DROP TABLE IF EXISTS `contas_receber`;
CREATE TABLE IF NOT EXISTS `contas_receber` (
  `pk_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `fk_codigo_pessoa` int(10) DEFAULT NULL,
  `descricao` text,
  `data` text NOT NULL,
  `vencimento` text NOT NULL,
  `pagamento` text NOT NULL,
  `fk_tipo_pagamento` text,
  `observacao` text,
  `situacao` smallint(1) DEFAULT NULL,
  `valor` double(6,2) DEFAULT NULL,
  `Processos` int(11) DEFAULT NULL,
  `Repasse` text,
  `banco` int(11) DEFAULT NULL,
  `FuncionarioCheck` text,
  PRIMARY KEY (`pk_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `contatos_seguradora`
--

DROP TABLE IF EXISTS `contatos_seguradora`;
CREATE TABLE IF NOT EXISTS `contatos_seguradora` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cod_seguradora` int(11) DEFAULT NULL,
  `cttseg_nome` varchar(100) DEFAULT NULL,
  `cttseg_telefone` varchar(255) DEFAULT NULL,
  `cttseg_email` varchar(100) DEFAULT NULL,
  `cttseg_obs` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `conta_agentes`
--

DROP TABLE IF EXISTS `conta_agentes`;
CREATE TABLE IF NOT EXISTS `conta_agentes` (
  `pk_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `fk_codigo_pessoa` int(10) DEFAULT NULL,
  `descricao` text,
  `data` text,
  `vencimento` text,
  `pagamento` text,
  `fk_tipo_pagamento` int(11) DEFAULT NULL,
  `observacao` text,
  `situacao` smallint(1) DEFAULT NULL,
  `valor` double(6,2) DEFAULT NULL,
  `Processos` int(11) DEFAULT NULL,
  `AgenteHonorario` double(6,2) DEFAULT NULL,
  `AgenteKmPercorrido` double(6,2) DEFAULT NULL,
  `AgentePtgoPorKMSeguradoraProcessos` double(6,2) DEFAULT NULL,
  `PagamentoTotalKM` double(6,2) DEFAULT NULL,
  `AgenteTotalRepasse` double(6,2) DEFAULT NULL,
  `TotalGeral` double(6,2) DEFAULT NULL,
  `Repasse` text,
  `banco` int(11) DEFAULT NULL,
  `DataNeg` text,
  `ValorNeg` double(6,2) DEFAULT NULL,
  PRIMARY KEY (`pk_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `conta_agentes`
--

INSERT INTO `conta_agentes` (`pk_codigo`, `fk_codigo_pessoa`, `descricao`, `data`, `vencimento`, `pagamento`, `fk_tipo_pagamento`, `observacao`, `situacao`, `valor`, `Processos`, `AgenteHonorario`, `AgenteKmPercorrido`, `AgentePtgoPorKMSeguradoraProcessos`, `PagamentoTotalKM`, `AgenteTotalRepasse`, `TotalGeral`, `Repasse`, `banco`, `DataNeg`, `ValorNeg`) VALUES
(1, 5, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 25, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(2, 7, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 38, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(3, 3, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 27, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(4, 15, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 28, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(5, 2, 'Adiantamentos =  \n SERVIÇO Pago: R$ 350,00', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 350.00, 6, 400.00, 10.00, 0.90, 9.00, 409.00, 59.00, 'false', 1, 'null', 0.00),
(6, 2, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 7, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(7, 7, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 15, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(8, 7, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 16, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(9, 16, 'Adiantamentos = ', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 18, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(10, 19, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 19, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(11, 17, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 20, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(12, 19, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 21, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(13, 15, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 22, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(14, 17, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 23, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(15, 9, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 24, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(16, 9, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 26, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(17, 16, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 29, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00),
(18, 2, '', 'null', '2019-10-12', 'null', 1, 'Nenhuma observação.', 0, 0.00, 29, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'false', 1, 'null', 0.00);

-- --------------------------------------------------------

--
-- Estrutura da tabela `estado`
--

DROP TABLE IF EXISTS `estado`;
CREATE TABLE IF NOT EXISTS `estado` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `uf` varchar(2) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `estado`
--

INSERT INTO `estado` (`codigo`, `nome`, `uf`) VALUES
(1, 'Minas Gerais', 'MG'),
(2, 'Acre', 'AC'),
(3, 'Alagoas', 'AL'),
(4, 'Amapá', 'AP'),
(5, 'Amazonas', 'AM'),
(6, 'Espírito Santo', 'ES'),
(7, 'Ceará', 'CE'),
(8, 'Distrito Federal', 'DF'),
(10, 'Goiás', 'GO'),
(11, 'Maranhão', 'MA'),
(12, 'Mato Grosso', 'MT'),
(13, 'Mato Grosso Do Sul', 'MS'),
(14, 'Pará', 'PA'),
(15, 'Paraíba', 'PB'),
(16, 'Paraná', 'PR'),
(17, 'Pernambuco', 'PE'),
(18, 'Piauí', 'PI'),
(19, 'Rio De Janeiro', 'RJ'),
(20, 'Rio Grande Do Norte', 'RN'),
(21, 'Rio Grande Do Sul', 'RS'),
(22, 'Rondonia', 'RO'),
(23, 'Roraima', 'RR'),
(24, 'Santa Catarina', 'SC'),
(25, 'São Paulo', 'SP'),
(26, 'Sergipe', 'SE'),
(27, 'Tocantins', 'TO'),
(28, 'Bahia', 'BA');

-- --------------------------------------------------------

--
-- Estrutura da tabela `forma_pagamento`
--

DROP TABLE IF EXISTS `forma_pagamento`;
CREATE TABLE IF NOT EXISTS `forma_pagamento` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  `desconto` double DEFAULT NULL,
  `quantidade_parcelas` int(11) DEFAULT NULL,
  `observacao` varchar(300) DEFAULT NULL,
  `situacao` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `forma_pagamento`
--

INSERT INTO `forma_pagamento` (`codigo`, `descricao`, `desconto`, `quantidade_parcelas`, `observacao`, `situacao`) VALUES
(1, 'Em Aberto', 0, 0, 'Nenhuma observação.', 1),
(2, 'Negado', 0, 0, 'Nenhuma observação.', 2),
(3, 'Concluído', 0, 0, 'Nenhuma observação.', 3),
(4, 'Em Andamento', 0, 0, 'Nenhuma observação.', 4),
(5, 'Aguardando Pagamento', 0, 0, 'Nenhuma observação.', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionarios`
--

DROP TABLE IF EXISTS `funcionarios`;
CREATE TABLE IF NOT EXISTS `funcionarios` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `cod_cidade` int(11) DEFAULT NULL,
  `cod_estado` int(11) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `telefone` varchar(13) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `obs` text,
  `numero` int(11) DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `rg` varchar(255) DEFAULT NULL,
  `cnh` varchar(255) DEFAULT NULL,
  `filiacao` varchar(255) DEFAULT NULL,
  `vinculo` varchar(255) DEFAULT NULL,
  `ocupacao` int(11) DEFAULT NULL,
  `op_cts` varchar(255) DEFAULT NULL,
  `nascimento` varchar(255) DEFAULT NULL,
  `data_admissao` varchar(255) DEFAULT NULL,
  `data_uferias` varchar(255) DEFAULT NULL,
  `data_vferias` varchar(255) DEFAULT NULL,
  `cod_ferias` int(11) DEFAULT NULL,
  `cod_bancos` int(11) DEFAULT NULL,
  `ob_ocupacao` text,
  `obs_func` text,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `funcionarios`
--

INSERT INTO `funcionarios` (`codigo`, `nome`, `endereco`, `bairro`, `cod_cidade`, `cod_estado`, `cep`, `telefone`, `celular`, `email`, `cpf`, `obs`, `numero`, `complemento`, `rg`, `cnh`, `filiacao`, `vinculo`, `ocupacao`, `op_cts`, `nascimento`, `data_admissao`, `data_uferias`, `data_vferias`, `cod_ferias`, `cod_bancos`, `ob_ocupacao`, `obs_func`) VALUES
(1, 'RENATO MARQUES', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 1, 1, '31535-496', '(  )    -    ', '(99) 99999-9999', 'EMAIL@EMAIL.COM', '581.321.556-53', 'Nenhuma observação.', 40, '', 'M4003445', '00699511238', 'AFILIAÇÃO DO COLABORADOR', 'Sócio', 4, '99', '1965-06-07', '2004-11-01', '2019-01-25', '2019-01-25', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(2, 'JACOB KUUHAKU', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 1, 1, '31550-400', '(  )    -    ', '(99) 99999-9999', 'EMAIL@EMAIL.COM', '731.900.806-82', 'NENHUMA OBSERVAÇÃO.', 370, '', 'MG-5.705.238-SSP/MG', '04273527662', 'AFILIAÇÃO DO COLABORADOR', 'Sócio', 1, '99', '1967-12-09', '2016-10-12', '2017-10-14', '2018-10-12', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(3, 'JOÃO PAULO', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 1, 1, '31812-020', '(  )    -    ', '(99) 99999-9999', 'EMAIL@EMAIL.COM', '441.158.576-68', 'NENHUMA OBSERVAÇÃO.', 437, '', 'M.965.002 SSP/MG', '00625834628', 'AFILIAÇÃO DO COLABORADOR', 'Colaborador', 1, '99', '1961-08-11', '2016-10-12', '2017-10-14', '2018-10-12', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(5, 'GUSTAVO HENRIQUE', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 35, 1, '39390-000', '(  )    -    ', '(99) 99999-9999', 'EMAIL@EMAIL.COM', '297.876.296-91', 'NENHUMA OBSERVAÇÃO.', 42, '', 'M-1.081.929-SSP/MG', '00664499600', 'AFILIAÇÃO DO COLABORADOR', 'Colaborador', 1, '99', '1958-09-06', '2016-10-12', '2017-10-14', '2018-10-12', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(7, 'ANNE CAROLINE', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 1, 1, '31155-680', '(  )    -    ', '(  )     -    ', 'EMAIL@EMAIL.COM', '032.121.566-43', 'NENHUMA OBSERVAÇÃO.', 450, 'APTO 201', 'MG767.119-SSP/MG', '02067769198', 'AFILIAÇÃO DO COLABORADOR', 'Colaborador', 1, '99', '1972-12-12', '1900-01-01', '2017-10-14', '2018-10-12', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(8, 'JESSICA MARQUES', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 1, 1, '31585-250', '(  )    -    ', '(99) 99999-9999', 'EMAIL@EMAIL.COM', '080.889.786-10', 'NENHUMA OBSERVAÇÃO.', 30, '', 'MG15.155.631 SSP/MG', '04104880829', 'AFILIAÇÃO DO COLABORADOR', 'Colaborador', 1, '99', '1989-03-03', '2010-03-01', '2017-10-14', '2018-10-12', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(9, 'ROBERTO NASCIMENTO', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 1, 1, '30280-230', '(  )    -    ', '(99) 99999-9999', 'EMAIL@EMAIL.COM', '002.310.426-04', 'NENHUMA OBSERVAÇÃO.', 602, '', 'M-6.009.090 SSP/MG', '00428531894', 'AFILIAÇÃO DO COLABORADOR', 'Colaborador', 1, '99', '1974-06-08', '2016-10-12', '2017-10-14', '2018-10-12', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(11, 'NEUSA MARIA', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 34, 1, '33935-520', '(  )    -    ', '(99) 99999-9999', 'EMAIL@EMAIL.COM', '103.465.746-17', 'Nenhuma observação.', 367, '', 'MG15.266.068', '', 'AFILIAÇÃO DO COLABORADOR', 'Funcionário', 3, '99', '1990-05-12', '2015-05-01', '2018-12-07', '2019-04-30', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(12, 'ROSANGELA GONÇALVES', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 1, 1, '31660-070', '(31)3459-7077', '(99) 99999-9999', 'EMAIL@EMAIL.COM', '130.214.496-03', 'NENHUMA OBSERVAÇÃO.', 127, 'APTO 301', 'MG 16.261.887-PC MG', '06505196525', 'AFILIAÇÃO DO COLABORADOR', 'Funcionário', 2, '99', '1996-11-08', '2015-02-23', '2019-01-24', '2020-02-22', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(13, 'AGENILSON SILVA', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 34, 1, '33930-590', '(  )    -    ', '(99) 99999-9999', 'EMAIL@EMAIL.COM', '100.674.436-30', 'NENHUMA OBSERVAÇÃO.', 288, 'BLOCO 17, APTO 402', 'MG15277101', '04932702458', 'AFILIAÇÃO DO COLABORADOR', 'Funcionário', 2, '99', '1991-11-14', '2017-02-01', '2018-06-18', '2019-02-01', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(14, 'EMERSON VIEIRA', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 1, 1, '33936-210', '(  )    -    ', '(  )     -    ', 'EMAIL@EMAIL.COM', '079.364.116-07', 'NENHUMA OBSERVAÇÃO.', 117, 'CHÁCARA', 'MG-15.056.989 SSP/MG', '04531161480', 'AFILIAÇÃO DO COLABORADOR', 'Funcionário', 2, '99', '1987-10-28', '2017-02-01', '2018-08-04', '2019-02-01', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(15, 'JÚLIO BRAGA', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 1, 1, '31580-000', '(31)8202-1443', '(99) 99999-9999', 'EMAIL@EMAIL.COM', '093.788.086-89', 'NENHUMA OBSERVAÇÃO.', 176, 'APTO 301', 'MG-15.877.062-SSP/MG', '404326503226', 'AFILIAÇÃO DO COLABORADOR', 'Colaborador', 1, '99', '1989-11-09', '2016-12-01', '2017-10-14', '2018-10-12', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(16, 'JESSICA MARQUES', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 1, 1, '31550-100', '(  )    -    ', '(  )     -    ', 'EMAIL@EMAIL.COM', '006.523.306-90', 'NENHUMA OBSERVAÇÃO.', 541, '', 'M-5.086.963', '01705786696', 'AFILIAÇÃO DO COLABORADOR', 'Colaborador', 1, '99', '1973-02-12', '2016-10-12', '2019-01-02', '2019-01-31', NULL, NULL, 'Nenhuma Observação', 'Nenhuma Observação'),
(17, 'GABRIEL LIMA', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 33, 1, '33400-000', '(  )    -    ', '(99) 99999-9999', 'EMAIL@EMAIL.COM', '071.225.056-50', 'NENHUMA OBSERVAÇÃO.1', 217, '', '11.814.596 SSP/MG', '03885942187', 'AFILIAÇÃO DO COLABORADOR', 'Colaborador', 1, '99', '1986-01-22', '2016-06-01', '2019-10-14', '2019-10-12', NULL, NULL, 'Nenhuma Observação1', 'Nenhuma Observação1'),
(19, 'KAUAN LIMA', 'RUA DO COLABORADOR', 'BAIRRO DO COLABORADOR', 1, 1, '31050-700', '(  )    -    ', '(  )     -    ', 'EMAIL@EMAIL.COM', '044.896.436-83', '', 23, '', 'MG- 7.383.784 - SSP/MG', '00851007174', 'AFILIAÇÃO DO COLABORADOR', 'Colaborador', 1, 'null', '1981-06-19', '2018-11-12', '1900-01-02', '1901-01-01', NULL, NULL, '', 'Nehuma obeservação.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ocupacao`
--

DROP TABLE IF EXISTS `ocupacao`;
CREATE TABLE IF NOT EXISTS `ocupacao` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cod_permissao` int(11) NOT NULL,
  `ocup_descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `ocupacao`
--

INSERT INTO `ocupacao` (`codigo`, `cod_permissao`, `ocup_descricao`) VALUES
(1, 1, 'Agente'),
(2, 2, 'Redator'),
(3, 3, 'Financeiro'),
(4, 4, 'Administração'),
(5, 5, 'Diretoria');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ordem_processo`
--

DROP TABLE IF EXISTS `ordem_processo`;
CREATE TABLE IF NOT EXISTS `ordem_processo` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Seguradoras` int(150) NOT NULL,
  `Cod_servico` int(150) NOT NULL,
  `HonorarioSemRetencao` double(6,2) DEFAULT NULL,
  `ObsSegurado` text,
  `Tipo` text,
  `DataProcesso` text,
  `Cts` int(8) DEFAULT NULL,
  `DataEntrada` text,
  `DataSaida` text,
  `NomeSegurado` text,
  `TipoPagamento` int(11) NOT NULL,
  `CodCidade` int(11) NOT NULL,
  `CodEstado` int(11) NOT NULL,
  `ObsSinistro` text,
  `HoraSinistro` text,
  `SisnistroData` text,
  `AlicotaPercentualHonorarios` double(6,2) DEFAULT NULL,
  `PercentuallRetidoReal` double(6,2) DEFAULT NULL,
  `DataEmissaoNFhonorarios` text,
  `SinistroBairro` text,
  `PrevisãoPgtoNFhonorarios` text,
  `SituacaoNotaFiscal` text,
  `HonorarioDefinido` double(6,2) DEFAULT NULL,
  `NumeroNFProcesso` text,
  `DataPgtoNF` text,
  `ValorNotaRealIndevido` double(6,2) DEFAULT NULL,
  `ValorNotaRealNegIndevido` double(6,2) DEFAULT NULL,
  `ValorTotalHonorariosComRetencao` double(6,2) DEFAULT NULL,
  `ValorHonorariosRetido` double(6,2) DEFAULT NULL,
  `ValorHonorarioProcesso` double(6,2) DEFAULT NULL,
  `RetidoReal` double(6,2) DEFAULT NULL,
  `AlicotaNeg` double(6,2) DEFAULT NULL,
  `ValorRetidoRetencaoNeg` double(6,2) DEFAULT NULL,
  `DataEmissaoNegativa` text,
  `DataPgtoNegativa` text,
  `PrevisaoPgtoNegativa` text,
  `NfNegPrejuizo` text,
  `ValorNegativaTotal` double(6,2) DEFAULT NULL,
  `ValorRetencaoTotalNeg` double(6,2) DEFAULT NULL,
  `PercentuallRetidoRealNeg` double(6,2) DEFAULT NULL,
  `RetidoRealNegativa` double(6,2) DEFAULT NULL,
  `NumeroSinistro` text,
  `Prejuizo` double(6,2) DEFAULT NULL,
  `Analista` text,
  `SeguradoPlaca` text,
  `ObsHonorariosProcesso` text,
  `ObsRetencaoAlicotaIndevido` text,
  `ObsRetencaoISSNeg` text,
  `IndevidoISS` text,
  `IndevidoISSNegativa` text,
  `SituacaoPgtoNegativa` text,
  `SituacaoPgtoProcesso` text,
  `cod_agente` int(11) DEFAULT NULL,
  `ValorDespesasTotalRegistro` double(6,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `ordem_processo`
--

INSERT INTO `ordem_processo` (`codigo`, `Seguradoras`, `Cod_servico`, `HonorarioSemRetencao`, `ObsSegurado`, `Tipo`, `DataProcesso`, `Cts`, `DataEntrada`, `DataSaida`, `NomeSegurado`, `TipoPagamento`, `CodCidade`, `CodEstado`, `ObsSinistro`, `HoraSinistro`, `SisnistroData`, `AlicotaPercentualHonorarios`, `PercentuallRetidoReal`, `DataEmissaoNFhonorarios`, `SinistroBairro`, `PrevisãoPgtoNFhonorarios`, `SituacaoNotaFiscal`, `HonorarioDefinido`, `NumeroNFProcesso`, `DataPgtoNF`, `ValorNotaRealIndevido`, `ValorNotaRealNegIndevido`, `ValorTotalHonorariosComRetencao`, `ValorHonorariosRetido`, `ValorHonorarioProcesso`, `RetidoReal`, `AlicotaNeg`, `ValorRetidoRetencaoNeg`, `DataEmissaoNegativa`, `DataPgtoNegativa`, `PrevisaoPgtoNegativa`, `NfNegPrejuizo`, `ValorNegativaTotal`, `ValorRetencaoTotalNeg`, `PercentuallRetidoRealNeg`, `RetidoRealNegativa`, `NumeroSinistro`, `Prejuizo`, `Analista`, `SeguradoPlaca`, `ObsHonorariosProcesso`, `ObsRetencaoAlicotaIndevido`, `ObsRetencaoISSNeg`, `IndevidoISS`, `IndevidoISSNegativa`, `SituacaoPgtoNegativa`, `SituacaoPgtoProcesso`, `cod_agente`, `ValorDespesasTotalRegistro`) VALUES
(1, 11, 3, 446.84, 'Nenhuma Observação', '5', '2019-03-01', 174, '2019-03-01', '2019-03-13', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 5, 1, 1, 'Nenhuma Observação', '00:00', '2019-01-20', 3.50, 0.00, '2019-03-13', 'null', '2019-04-02', 'Selecionar', 446.84, '2019/170', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '962432097', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'ARQUIVO', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 8, 2079.00),
(2, 13, 3, 0.00, 'Nenhuma Observação', '1', '2019-03-01', 175, '2019-03-01', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '00:00', '2019-01-27', 3.50, 0.00, '', 'Avenida Nossa Senhora de Fátima, 1416, Ciro dos Anjos - Montes Claros/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2019143855', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(3, 3, 2, 0.00, 'Nenhuma Observação', '3', '2019-03-01', 176, '2019-03-01', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 3, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-24', 3.50, 0.00, 'null', 'null', 'null', 'Selecionar', 0.00, '', 'null', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, '2871945', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', '', '', 'false', 'false', 'Selecionar', '0', 7, 0.00),
(4, 2, 4, 560.00, 'Nenhuma Observação', '5', '2019-03-01', 177, '2019-03-01', '2019-03-12', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 5, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-17', 3.50, 0.00, '2019-03-12', 'null', '2019-03-22', 'Selecionar', 560.00, '2019/171', 'null', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, '238067964', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'ARQUIVO', '', '', 'false', 'false', 'Selecionar', '0', 3, 259.65),
(5, 13, 1, 400.00, 'Nenhuma Observação', '3', '2019-03-01', 178, '2019-03-01', '2019-03-19', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 3, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-15', 3.50, 0.00, '2019-03-19', 'null', '2019-04-15', 'Selecionar', 400.00, '2019/176', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2019147406', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'ARQUIVO', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 8, 161.10),
(6, 3, 5, 0.00, 'Nenhuma Observação', '5', '2019-03-01', 179, '2019-03-01', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 5, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-19', 3.50, 0.00, '', 'Rodovia MG 458, Córrego Areces, Centro - Inhapim/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2873455', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 2, 0.00),
(7, 3, 2, 0.00, 'Nenhuma Observação', '4', '2019-03-01', 180, '2019-03-01', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-16', 3.50, 0.00, '', 'Rodovia JK, Km 99, Ribeirão das Mortes - Aeroporto Jatobá - Pouso Alegre/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2868681', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 2, 0.00),
(8, 3, 2, 0.00, 'Nenhuma Observação', '4', '2019-03-01', 181, '2019-03-01', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-21', 3.50, 0.00, '', 'Rodovia Fernão Dias, Km 557, Centro - Itaguara/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2872317', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 5, 0.00),
(9, 5, 15, 364.00, 'Nenhuma Observação', '3', '2019-03-07', 182, '2019-03-07', '2019-03-13', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 3, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-27', 3.50, 0.00, '2019-03-13', 'null', '2019-03-28', 'Selecionar', 364.00, '2019/154', '2019-03-19', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '10400000000000', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'ARQUIVO', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 15, 50.00),
(10, 5, 2, 590.00, 'Nenhuma Observação', '5', '2019-03-08', 183, '2019-03-08', '2019-03-19', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 5, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-27', 3.50, 0.00, '2019-03-19', 'null', '2019-04-04', 'Selecionar', 590.00, '2019/178', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '10400000000000', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'ARQUIVO', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 16, 1132.80),
(11, 5, 5, 590.00, 'Nenhuma Observação', '5', '2019-03-08', 184, '2019-03-08', '2019-03-19', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 5, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-27', 3.50, 0.00, '2019-03-19', 'null', '2019-04-03', 'Selecionar', 590.00, '2019/177', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '10400000000000', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'ARQUIVO', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 2, 116.00),
(12, 8, 8, 100.00, 'Nenhuma Observação', '5', '2019-03-08', 185, '2019-03-08', '2019-03-12', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 5, 1, 1, 'Nenhuma Observação', '00:00', '2019-01-26', 3.50, 0.00, '2019-03-12', 'null', '2019-03-27', 'Selecionar', 100.00, '2019/152', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '251000000000000', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'ARQUIVO', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 5, 668.46),
(13, 11, 4, 446.84, 'Nenhuma Observação', '3', '2019-03-08', 186, '2019-03-08', '2019-03-18', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 3, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-22', 3.50, 0.00, '2019-03-18', 'null', '2019-04-03', 'Selecionar', 446.84, '2019/174', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '962502690', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'ARQUIVO', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 5, 1783.35),
(14, 3, 2, 400.00, 'Nenhuma Observação', '3', '2019-03-08', 187, '2019-03-08', '2019-03-14', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 3, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-24', 3.50, 0.00, '2019-03-14', 'null', '2019-03-29', 'Selecionar', 400.00, '2019/166', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2872461', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'ARQUIVO', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 5, 391.36),
(15, 13, 3, 0.00, 'Nenhuma Observação', '4', '2019-03-08', 188, '2019-03-08', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-26', 3.50, 0.00, '', 'Rodovia MG 187, Km 5 - Patrocínio/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '201914053', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 7, 2079.00),
(16, 7, 2, 0.00, 'Nenhuma Observação', '4', '2019-03-11', 189, '2019-03-11', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-06', 3.50, 0.00, '', 'Rodovia BR 040, Califórnia - Contagem/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '6909259', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 7, 0.00),
(17, 3, 2, 0.00, 'Nenhuma Observação', '3', '2019-03-12', 190, '2019-03-12', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 3, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-23', 3.50, 0.00, '', 'null', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2874202', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 8, 0.00),
(18, 8, 8, 100.00, 'Nenhuma Observação', '4', '2019-03-13', 191, '2019-03-13', '2019-03-15', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-01', 3.50, 0.00, '2019-03-15', 'null', '2019-04-01', 'Selecionar', 100.00, '2019/160', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '609000000000000', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'ARQUIVO', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 16, 259.65),
(19, 3, 2, 0.00, 'Nenhuma Observação', '4', '2019-03-14', 192, '2019-03-14', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-01', 3.50, 0.00, '', 'Rodovia BR 251, Centro - Francisco Sá/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2874634', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 19, 161.10),
(20, 12, 5, 0.00, 'Nenhuma Observação', '4', '2019-03-14', 193, '2019-03-14', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-24', 3.50, 0.00, '', 'Avenida Cardoso de Menezes, 346, Alvorada - Sabará/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '201958721', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 17, 0.00),
(21, 12, 5, 0.00, 'Nenhuma Observação', '4', '2019-03-14', 194, '2019-03-14', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-22', 3.50, 0.00, '', 'Avenida Vilarinho, 2311, Venda Nova - Belo Horizonte/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.70, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '201958603', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 19, 0.00),
(22, 13, 1, 0.00, 'Nenhuma Observação', '4', '2019-03-14', 195, '2019-03-14', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-23', 3.50, 0.00, '', 'Rua Piedade do Paraopeba - Brumadinho/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2019148447', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 15, 0.00),
(23, 13, 3, 0.00, 'Nenhuma Observação', '4', '2019-03-14', 196, '2019-03-14', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-05', 3.50, 0.00, '', 'Rodovia BR 135, Km 525 - Buenópolis/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2019150056', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 17, 50.00),
(24, 13, 1, 0.00, 'Nenhuma Observação', '4', '2019-03-14', 197, '2019-03-14', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-17', 3.50, 0.00, '', 'Rodovia Anel Rodoviário, s/nº, Nazaré - Belo Horizonte/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2019146841', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 9, 1132.80),
(25, 13, 1, 0.00, 'Nenhuma observação', '4', '2019-24-03', 197, '2019-03-25', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma observação', '00:00', '2019-02-17', 0.00, 0.00, 'null', 'RODOVIA ANEL RODOVIÁRIO, S/Nº, NAZARÉ - BELO HORIZONTE/MG ', 'null', 'Selecionar', 0.00, '', 'null', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, '2019146841 ', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', '', '', 'false', 'false', 'Selecionar', '0', 5, 0.00),
(26, 13, 2, 0.00, 'Nenhuma Observação', '4', '2019-03-14', 198, '2019-03-14', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2018-11-15', 3.50, 0.00, '', 'Rua Dois, 150, Quintas Colonial - Contagem/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2019146386', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 9, 116.00),
(27, 5, 5, 0.00, 'Nenhuma Observação', '4', '2019-03-15', 199, '2019-03-15', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-02-05', 3.50, 0.00, '', 'Rua Conde Afonso Celso, 01, Jardim Industrial - Contagem/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '10600000000000', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'true', 'false', 'Selecionar', '0', 3, 668.46),
(28, 16, 5, 0.00, 'Nenhuma Observação', '4', '2019-03-15', 200, '2019-03-15', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-06', 3.50, 0.00, '', 'Rua Cassiano Dornas, Inconfidentes - Contagem/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '20193817', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 15, 1783.35),
(29, 3, 2, 0.00, 'Nenhuma Observação', '4', '2019-03-15', 201, '2019-03-15', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-05', 3.50, 0.00, '', 'Rodovia LMG 626, Centro - Ataleia/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2877158', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 16, 391.36),
(30, 11, 3, 0.00, 'Nenhuma Observação', '1', '2019-03-15', 202, '2019-03-15', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '000:00', '2019-03-04', 3.50, 0.00, '', 'Rua Coronel João Cândido de Aguiar, Centro - Patrocínio/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '962514986', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(31, 15, 2, 0.00, 'Nenhuma Observação', '1', '2019-03-15', 203, '2019-03-15', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-02', 3.50, 0.00, '', 'Rodovia BR 265 - São João Del Rei/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '962509893', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(32, 5, 1, 0.00, 'Nenhuma Observação', '1', '2019-03-15', 204, '2019-03-15', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-09', 3.50, 0.00, '', 'Ponte Ribeirão da Onça, Km 08, Centro - Jaguaraçu/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '10400000000000', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(33, 3, 2, 0.00, 'Nenhuma Observação', '1', '2019-03-18', 205, '2019-03-18', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-04', 3.50, 0.00, '', 'Avenida Tancredo Neves, 1596, Castelo - Belo Horizonte/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2875086', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(34, 3, 2, 0.00, 'Nenhuma Observação', '1', '2019-03-19', 206, '2019-03-19', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-03', 3.50, 0.00, '', 'Rua Marechal Deodoro, s/nº, Centro - Indianópolis/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2878152', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(35, 3, 13, 0.00, 'Nenhuma Observação', '1', '2019-03-19', 207, '2019-03-19', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-10', 3.50, 0.00, '', 'null', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2878369', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(36, 3, 2, 4110.00, 'Nenhuma Observação', '3', '2019-03-19', 208, '2019-03-19', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 3, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-11', 3.50, 3.53, '2019-03-20', 'null', '2019-03-29', 'Selecionar', 4110.00, '9999999999', '2019-03-29', 3965.00, 0.00, 3966.15, 143.85, 0.00, 145.00, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 145.00, '2878515', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'Nenhuma observação', 'Retido a mais', '', 'true', 'false', 'Selecionar', '0', 5, 1500.00),
(37, 12, 6, 0.00, 'Nenhuma Observação', '1', '2019-03-19', 209, '2019-03-19', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-03', 3.50, 0.00, '', 'Rua Sirius, 429, Cidade Verde - Betim/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '201959248', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(38, 5, 1, 0.00, 'Nenhuma Observação', '4', '2019-03-19', 210, '2019-03-19', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 4, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-03', 3.50, 0.00, '', 'Rodovia MG 514, Zona Rural - Rio Paranaíba/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '10600000000000', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 7, 0.00),
(39, 11, 2, 0.00, 'Nenhuma Observação', '1', '2019-03-20', 211, '2019-03-20', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-07', 3.50, 0.00, '', 'Estrada de São Lourenço - Raul Soares/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '962518160', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(40, 5, 3, 0.00, 'Nenhuma Observação', '1', '2019-03-20', 212, '2019-03-20', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-14', 3.50, 0.00, 'null', 'Avenida Francisco Sá, 75, Centro - Teófilo Otoni/MG', 'null', 'Selecionar', 0.00, '', 'null', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, '10700000000000', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', '', '', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(41, 5, 3, 0.00, 'Nenhuma Observação', '1', '2019-03-20', 213, '2019-03-20', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '00:00', '2019-01-24', 3.50, 0.00, '', 'Rua P. Remaclo Foxius x Rua Rosemar Pereira, s/nº, Santo Antônio - Formiga/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '10400000000000', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(42, 7, 2, 0.00, 'Nenhuma Observação', '1', '2019-03-20', 214, '2019-03-20', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-18', 3.50, 0.00, 'null', 'Rua Plinio Pinheiro, 07, Bom Pastor - Manhuaçu/MG', 'null', 'Selecionar', 0.00, '', 'null', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, '6966638', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', '', '', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(43, 7, 11, 0.00, 'Nenhuma Observação', '1', '2019-03-20', 215, '2019-03-20', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-10', 3.50, 0.00, '', 'Rua Bento Hinoto, 48, Martelos - Juiz de Fora/MG', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '6950899', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(44, 13, 3, 0.00, 'Nenhuma Observação', '1', '2019-03-20', 216, '2019-03-20', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 0, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-04', 3.50, 0.00, '', 'Rodovia MG 16, 448 - Iuna/ES', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2019149792', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(45, 12, 3, 0.00, 'Nenhuma Observação', '1', '2019-03-20', 217, '2019-03-20', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 0, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-10', 3.50, 0.00, 'null', 'KKKKKKKKKKKKKK', 'null', 'Selecionar', 0.00, '', 'null', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, '201959402', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', '', '', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(46, 13, 1, 0.00, 'Nenhuma Observação', '1', '2019-03-21', 218, '2019-03-21', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 0, 8, 5, 'Nenhuma Observação', '00:00', '2019-03-06', 3.50, 0.00, 'null', 'SDASDSADASDASD', 'null', 'Selecionar', 0.00, '', 'null', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, '2019150116', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', '', '', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(47, 13, 1, 0.00, 'Nenhuma Observação', '1', '2019-03-21', 219, '2019-03-21', '', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 0, 1, 1, 'Nenhuma Observação', '00:00', '2019-03-06', 3.50, 0.00, '', 'null', '', 'Selecionar', 0.00, '', '', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, '', '', '', '', 0.00, 0.00, 0.00, 0.00, '2019150809', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', 'Nenhuma Observação', 'Nenhuma Observação', 'false', 'false', 'Selecionar', '0', 0, 0.00),
(48, 4, 1, 1133.00, 'asdasdsadsad', '1', '2019-03-29', 0, '2019-03-29', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 2, 25, 'Nenhuma observação', '00:00', '2019-03-29', 3.50, 0.00, '2019-03-29', 'SADOI HHDSAHKJDHASKJH KHKJAHJKDHSA', '2019-03-29', 'A Deliberação', 1133.00, '123123', '2019-03-29', 0.00, 0.00, 1433.03, 51.98, 0.00, 0.00, 3.31, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, '11111111', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'Nenhuma observação', '', '', 'false', 'false', 'Selecionar', '0', 0, 352.00),
(49, 1, 6, 100.00, '', '1', '2019-04-10', 0, '2019-04-10', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 0, 1, 1, '', '00:00', 'null', 3.50, 3.60, '2314-07-12', '', '2314-07-12', 'A Deliberação', 100.00, 'ASDASDSAD', '2314-07-12', 96.40, 0.00, 96.50, 3.50, 0.00, 3.60, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 3.60, '123', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', 'Nenhuma observação', 'Nenhuma observação.', '', 'true', 'false', 'Selecionar', '0', NULL, 150.00),
(50, 1, 4, 0.00, '', '1', '2019-04-11', 0, '2019-04-11', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, '', '00:00', 'null', 3.50, 0.00, 'null', '', 'null', 'Selecionar', 0.00, '', 'null', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, '123', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', '', '', 'false', 'false', 'Selecionar', '1', NULL, 0.00),
(51, 1, 15, 0.00, '', '1', '2019-04-11', 0, '2019-04-11', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, '', '00:00', 'null', 3.50, 0.00, 'null', '', 'null', 'Selecionar', 0.00, '', 'null', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, 'SADSADASD', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', '', '', 'false', 'false', 'Selecionar', '1', NULL, 0.00),
(52, 1, 14, 0.00, '', '1', '2019-04-12', 0, '2019-04-12', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 0, 1, 1, '', '00:00', 'null', 3.50, 0.00, 'null', '', 'null', 'Selecionar', 0.00, '', 'null', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, '123', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', '', '', 'false', 'false', 'Selecionar', '0', NULL, 0.00),
(53, 3, 1, 0.00, '', '1', '2019-04-15', 0, '2019-04-15', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, '', '00:00', 'null', 3.50, 0.00, 'null', '', 'null', 'Selecionar', 0.00, '', 'null', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, 'ASDASD', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', '', '', 'false', 'false', 'Selecionar', '1', NULL, 0.00),
(54, 1, 8, 0.00, '', '1', '2019-04-15', 0, '2019-04-15', 'null', 'NOME DO SEGURADO (CLIENTE ATENDIDO)', 1, 1, 1, '', '00:00', 'null', 3.50, 0.00, 'null', '', 'null', 'Selecionar', 0.00, '', 'null', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 3.50, 0.00, 'null', 'null', 'null', '', 0.00, 0.00, 0.00, 0.00, 'ASDAS', 0.00, 'ANALISTA RESPONSÁVEL (QUE ENVIOU A SOLICITAÇÃO DO PROCESSO)', 'ZZZ-9999', '', '', '', 'false', 'false', 'Selecionar', '1', NULL, 0.00);

-- --------------------------------------------------------

--
-- Estrutura da tabela `seguradoras`
--

DROP TABLE IF EXISTS `seguradoras`;
CREATE TABLE IF NOT EXISTS `seguradoras` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `cnpj` varchar(45) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `cod_cidade` int(11) DEFAULT NULL,
  `cod_estado` int(11) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `obs` text,
  `cep` varchar(20) DEFAULT NULL,
  `seg_km` double(6,2) DEFAULT NULL,
  `seg_honorarios` double(6,2) DEFAULT NULL,
  `seg_negativa` varchar(255) DEFAULT NULL,
  `seg_iss` varchar(45) DEFAULT NULL,
  `fk_cod_contatos` int(11) DEFAULT NULL,
  `seg_hmeio` double(6,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `seguradoras`
--

INSERT INTO `seguradoras` (`codigo`, `nome`, `cnpj`, `endereco`, `bairro`, `numero`, `complemento`, `cod_cidade`, `cod_estado`, `telefone`, `celular`, `email`, `obs`, `cep`, `seg_km`, `seg_honorarios`, `seg_negativa`, `seg_iss`, `fk_cod_contatos`, `seg_hmeio`) VALUES
(1, 'STUDIO MICRO WEB', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, 'EMPRESA', 1, 1, '(99)9999-9999', '(99)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', 'NENHUMA OBSERVAÇÃO.', '99999-999', 0.90, 420.00, 'Não', 'Sim', NULL, 80.00),
(2, 'AEIFI FOZ', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, '', 1, 1, '(11)3171-5309', '(99)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', 'MEIA SINDICÂNCIA R$300,00, DOCUMENTOS/BO R$ 100,00', '99999-999', 0.81, 560.00, 'Não', 'Sim', NULL, 300.00),
(3, 'ABEMMEX', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, '3ª ANDAR', 1, 1, '(99)9999-9999', '(99)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', 'NENHUMA OBSERVAÇÃO.', '99999-999', 0.72, 400.00, 'Sim', 'Sim', NULL, 0.00),
(4, 'ESTUDIO86', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, 'SALAS 713 A 717', 1, 1, '(99)9999-9999', '(99)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', 'HONORÁRIOS DE TRANSPORTES R$ 600,00', '99999-999', 1.00, 450.00, 'Não', 'Sim', NULL, 200.00),
(5, 'GESTOR LINE', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, '', 1, 1, '(31)3003-5390', '(  )     -    ', 'EMAIL@FORNECEDOR.COM.BR', 'NENHUMA OBSERVAÇÃO.', '99999-999', 1.00, 590.00, 'Não', 'Sim', NULL, 364.00),
(6, 'PONTO DA CÓPIA', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, '6ª ANDAR', 2, 25, '(99)9999-9999', '(99)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', 'HONORÁRIOS PROPERTY 550,000, HONORÁRIOS PERFIL 250,00', '99999-999', 0.77, 500.00, 'Não', 'Não', NULL, 250.00),
(7, 'DOCITO DA KAROL', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, 'EMPRESA', 1, 1, '(99)9999-9999', '(99)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', 'NENHUMA OBSERVAÇÃO.', '99999-999', 0.77, 500.00, 'Não', 'Não', NULL, 120.00),
(8, 'FAITEC TECNOLOGIA', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, '256', 1, 1, '(99)9999-9999', '(99)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', '180,00 - PERFIL / 100,00 BO/LOCAL/DOCUMENTOS', '99999-999', 0.78, 370.00, 'Não', 'Sim', NULL, 180.00),
(9, 'ASSURANCE IT', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, '29ª, 30ª, 31 ANDARES', 5, 19, '(99)9999-9999', '(99)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', 'HONORÁRIOS DE PERFIL R$ 180,00. HONORÁRIOS DE BO E DOCUMENTOS R$ 100,00', '99999-999', 0.78, 370.00, 'Não', 'Não', NULL, 180.00),
(10, 'ACCENTURE IT', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, '1º AO 5º ANDAR', 2, 25, '(99)9999-9999', '(99)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', '150,00 - BO E PERFIL', '99999-999', 0.90, 300.00, 'Não', 'Não', NULL, 150.00),
(11, 'MAHLE BEHR BRASIL', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, 'EMPRESA', 1, 1, '(99)9999-9999', '(99)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', 'NENHUMA OBSERVAÇÃO.', '99999-999', 1.00, 446.84, 'Não', 'Sim', NULL, 0.00),
(12, 'IKESAKI BRASIL', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, 'QUADRA 65, LOTE 16', 31, 1, '(99)9999-9999', '(99)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', 'NENHUMA OBSERVAÇÃO.', '99999-999', 0.70, 400.00, 'Sim', 'Não', NULL, 120.00),
(13, 'FM RODRIGUES CIA', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, 'SALA 101', 31, 1, '(31)3328-3600', '(99)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', 'NENHUMA OBSERVAÇÃO.', '99999-999', 0.70, 400.00, 'Sim', 'Não', NULL, 120.00),
(14, 'CASA DOS TRANSFORMADORES', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, '', 1, 1, '(31)2532-3902', '(  )     -    ', 'EMAIL@FORNECEDOR.COM.BR', '', '99999-999', 0.70, 400.00, 'Sim', 'Não', NULL, 0.00),
(15, 'RODOGREEN IMPLEMENTOS PARA O TRANSPORTE', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, 'ANDAR 8', 1, 1, '(  )    -    ', '(  )     -    ', 'EMAIL@FORNECEDOR.COM.BR', '', '99999-999', 1.00, 446.84, 'Não', 'Sim', NULL, 0.00),
(16, 'SERMEI BRASIL', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, '', 1, 1, '(  )    -    ', '(  )     -    ', 'EMAIL@FORNECEDOR.COM.BR', '', '99999-999', 0.70, 400.00, 'Não', 'Não', NULL, 0.00),
(17, 'EVANDRO ADVOGADOS', '99.999.999/0001-99', 'RUA DO FORNECEDOR', 'BAIRRO DO FORNECEDOR', 999, 'TESTE TESTE', 3, 25, '(11)9999-9999', '(11)99999-9999', 'EMAIL@FORNECEDOR.COM.BR', '', '99999-999', 0.90, 500.00, 'Sim', 'Sim', NULL, 1250.00);

-- --------------------------------------------------------

--
-- Estrutura da tabela `servicos`
--

DROP TABLE IF EXISTS `servicos`;
CREATE TABLE IF NOT EXISTS `servicos` (
  `codigoServico` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigoServico`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `servicos`
--

INSERT INTO `servicos` (`codigoServico`, `nome`) VALUES
(1, 'Auto/Colisão'),
(2, 'Auto/Colisão-Perfil'),
(3, 'Auto/RCF'),
(4, 'Auto/RCF-Perfil\r'),
(5, 'Auto/Furto-Perfil\r'),
(6, 'Auto/Roubo\r'),
(7, 'Auto/Roubo-Perfil'),
(8, 'Levantamentos de local'),
(9, 'Auto/Perfil'),
(10, 'Busca de Documentos'),
(11, 'Ramos Elementares'),
(12, 'RC Garagista'),
(13, 'Danos Elétricos'),
(14, 'Vida'),
(15, 'Documentação');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
CREATE TABLE IF NOT EXISTS `tbl_usuario` (
  `pk_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `permissao` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  PRIMARY KEY (`pk_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tbl_usuario`
--

INSERT INTO `tbl_usuario` (`pk_codigo`, `permissao`, `nome`, `login`, `senha`) VALUES
(2, 5, 'RENATO MARQUES', 'admin', 'admin'),
(3, 3, 'ANNE CAROLINE', 'anne', '123'),
(4, 4, 'JACOB KUUHAKU', 'jacob', '123');

-- --------------------------------------------------------

--
-- Estrutura da tabela `terceiros_processos`
--

DROP TABLE IF EXISTS `terceiros_processos`;
CREATE TABLE IF NOT EXISTS `terceiros_processos` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_servico` int(11) DEFAULT NULL,
  `cod_ordem_servico` int(11) DEFAULT NULL,
  `nome` varchar(150) DEFAULT NULL,
  `placa` varchar(10) DEFAULT NULL,
  `obs` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `terceiros_processos`
--

INSERT INTO `terceiros_processos` (`codigo`, `codigo_servico`, `cod_ordem_servico`, `nome`, `placa`, `obs`) VALUES
(1, 1, 48, 'NOME TERCEIRO', 'ZZZ-9999', 'Nenhuma observação.'),
(2, 1, 48, 'NOME TERCEIRO', 'ZZZ-9999', 'Nenhuma observação.'),
(3, 0, 51, 'NOME TERCEIRO', 'ZZZ-9999', 'Nenhuma observação.'),
(4, 15, 51, 'NOME TERCEIRO', 'ZZZ-9999', 'Nenhuma observação.'),
(5, 14, 52, 'NOME TERCEIRO', 'ZZZ-9999', 'Nenhuma observação.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipodespesas`
--

DROP TABLE IF EXISTS `tipodespesas`;
CREATE TABLE IF NOT EXISTS `tipodespesas` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cober_nome` varchar(255) DEFAULT NULL,
  `cober_valor` double(6,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tipodespesas`
--

INSERT INTO `tipodespesas` (`codigo`, `cober_nome`, `cober_valor`) VALUES
(1, 'Processos', 10.00),
(2, 'Complementares', 10.00),
(3, 'Negativa', 10.00),
(4, 'Outros', 10.00);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vinculo`
--

DROP TABLE IF EXISTS `vinculo`;
CREATE TABLE IF NOT EXISTS `vinculo` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `vinc_descricao` varchar(255) DEFAULT NULL,
  `vinc_departamento` varchar(50) DEFAULT NULL,
  `vinc_obs` text,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `vinculo`
--

INSERT INTO `vinculo` (`codigo`, `vinc_descricao`, `vinc_departamento`, `vinc_obs`) VALUES
(1, 'Empregado', 'Operacional', ''),
(2, 'Colaborador', 'Operacional', ''),
(3, 'Sócio', 'Administração', ''),
(4, 'Investidor', 'Contrato', ''),
(5, 'RH', 'Administração', NULL),
(6, 'Financeiro', 'Administração', NULL),
(7, 'Diretoria', 'Administração', NULL);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `cidade`
--
ALTER TABLE `cidade`
  ADD CONSTRAINT `fk_cidade_estado_idx` FOREIGN KEY (`fk_cod_estado`) REFERENCES `estado` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
