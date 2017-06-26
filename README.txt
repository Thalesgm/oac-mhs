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
	- Drive.java ou Drive2.java
	- L2Cache.java
	- MainMem.java
	- Page.java
	- Word.java

Todos os arquivos acima se encontram na pasta src.
Para compila��o o usuario pode usar uma IDE com compilador java (ex: Eclipse)
ou executar a partir da linha de comando em terminal seguindo os passos:
	1- Acessar a pasta src
	2- Executar javac para drive.java ou drive2.java
	3- Executar 'drive arg0 arg1' ou 'drive2 arg1'
	4- Arg0 � um arquivo txt no formato de Setup listado abaixo.
	5- Arg1 � um arquivo de comandos, que pode apresentar varia��es para drive e drive2

Argumentos:
	Arquivo de comandos:

	O arquivo de comandos para os drives consistem em uma lista dos comandos abaixo:
	
	LOAD A B: Carrega o endere�o B para o core A
	SAVE A B C: Escreve em B o valor de C por meio do core A.
	PRINTD: Imprime o conteudo do Disco, organizado por paginas.
	PRINTM: Imprime o conteudo da memoria principal organizada por paginas.
	PRINTC2: Imprime o conteudo das Caches L2 de todos os cores.
	PRINTC1: Imprime o conteudo das Caches L1 de todos os cores.
	
	No caso de o drive usado ser o 'drive2' A primeira linha do arquivo deve
	conter um valor inteiro par que representa o numero de cores 
	para inicializa��o do simulador.
	No caso do 'drive.java' o numero de cores deve estar na primeira linha do 
	arg0.txt seguido dos valores a serem carregados no disco.
	
	Exemplos de arquivos podem ser encontrados nas pastas drive1files e drive2files.

Em caso de duvida ler o relatorio do projeto ou os comentarios no c�digo.