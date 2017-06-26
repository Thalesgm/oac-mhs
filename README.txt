Universidade Federal do Rio Grande do Norte - UFRN
Istituto Metropole Digital - IMD
DIM0431 - Organiza��o e Arquitetura de Computadores - T01 2017.1
Projeto MHS - Memory Hierarchy Simulator
Autor: Thales G Moreira
Docente: Monica Magalh�es Pereira

Descri��o:
Este � o projeto final da disciplina, para compor a nota da 3� unidade.

O Projeto consiste de um simulador de hierarquia de memoria.
Para execut�-lo, s�o necessarios os arquivos abaixo listados.

	- Block.java
	- Core.java
	- Disc.java
	- Drive.java
	- L2Cache.java
	- MainMem.java
	- Page.java
	- Word.java

Todos os arquivos acima se encontram na pasta src.
Para compila��o o usuario pode usar uma IDE com compilador java (ex: Eclipse)
ou executar a partir da linha de comando em terminal seguindo os passos:
	1- Acessar a pasta src
	2- Executar javac para drive.java
	3- Executar 'drive arg0'
	4- Arg0 � um arquivo txt no formato de Setup listado abaixo.
	
	Obs: H� a op��o de usar o DriveOpt.java para compila��o usando Javac DriveOpt.java 
	e executando DriveOpt arg0 arg1

Argumentos:
	Arquivo de comandos:

	O arquivo de comandos para o drive consiste em uma lista dos comandos abaixo:
	
	LOAD A B: Carrega o endere�o B para o core A
	SAVE A B C: Escreve em B o valor de C por meio do core A.
	PRINTD: Imprime o conteudo do Disco, organizado por paginas.
	PRINTM: Imprime o conteudo da memoria principal organizada por paginas.
	PRINTC2: Imprime o conteudo das Caches L2 de todos os cores.
	PRINTC1: Imprime o conteudo das Caches L1 de todos os cores.
	
	A primeira linha deve conter um numero inteiro par 
	que ira representar o numero de cores.
	
	No caso de o drive usado ser o 'DriveOpt', O arg0 como um arquivo 
	contendo o numero de cores na primeira linha seguido de valores para 
	carregar no disco.
	O arg1 deve conter as instru��es para o simulador.
	
	Exemplos de arquivos podem ser encontrados nas pastas drive1files e driveOptfiles.

Em caso de duvida ler o relatorio do projeto ou os comentarios no c�digo.