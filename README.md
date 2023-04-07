# Teste Java

O código aqui apresentado por Pedro Pinheiro de Siqueira, aluno do CEFET-MG campus V - Divinópolis, foi um pedido feito como teste para um projeto de seleção do CEFET-MG.

## Pedido

**Utilizando os conceitos de Orientação a Objetos,** implemente em Java:

* Um método que receba uma lista de funcionários, mês e ano e retorne o valor total pago (salário e benefício) a esses funcionários no mês.
* Um método que receba uma lista de funcionários, mês e ano e retorne o total pago somente em salários no mês.
* Um método que receba uma lista **somente com os funcionários que recebem benefícios**, mês e ano e retorne o total pago em benefícios no mês.
* Um método que receba uma lista de funcionários, mês e ano e retorne o que recebeu o valor mais alto no mês.
* Um método que receba uma lista **somente com os funcionários que recebem benefícios**, mês e ano e retorne o nome do funcionário que recebeu o valor mais alto em benefícios no mês.
* Um método que receba uma lista **de vendedores**, mês e ano e retorne o que mais vendeu no mês

Era conhecido o funcionamento de cada cargo, quando alguns funcionários foram contratados e o histórico de vendas de poucos meses.

## Como testar

Por ser somente em java, algumas coisas que seriam melhor realizadas utilizando-se de um banco de dados acabaram sendo feitas com meros arquivos txt. Por isso, sempre que o programa é rodado, os funcionários são lidos e o histórico de vendas também. Esses arquivos estão na pasta *database* e é necessário que os dados não sejam mudados de como foram feitos. Caso deseje adicionar mais nomes ou vendas, adicione da seguinte forma:

Em funcionários.txt cada linha é um funcionário, as informações são separadas por vírgula e seguem o seguinte padrão:

* *Nome do Funcionário*,*Cargo do Funcionário*,*Mês que foi contratado*,*Ano que foi contratado*

Sendo que Cargo do funcionário é um inteiro de 1 a 3, onde 1 - Secretário, 2 - Vendedor, 3 - Gerente

Em vendas.txt cada linha é um único registro de venda, as informações são separadas por vírgula e seguem o seguinte padrão:

* *Nome do Funcionário*,*Mês da Venda*,*Ano da Venda*,*Valor Vendido*

É pedido que qualquer registro feito tente respeitar a "linha temporal", onde vendas mais recentes de um mesmo funcionário sempre estejam em linhas inferiores a vendas mais antigas.

Ao rodar o programa a leitura desses dois arquivos é feita de forma automática, e depois desta leitura o usuário vê um menu onde ele pode escolher qual dos métodos pedidos deseja testar no momento. Ao escolher, o explorer abrirá para que o usuário escolha qual lista de nomes ele deseja testar. Algumas listas foram deixadas prontas na pasta *arquivos para testes*, mas o usuário pode se sentir livre para fazer outras listas se assim desejar. As listas só precisam seguir essas restrições:

* Nome do funcionário (1 funcionário por linha)
* Mês (um inteiro) que deseja testar a lista - uma linha só para isso
* Ano (um inteiro) que deseja testar a lista - uma linha só para isso

Independentemente dos nomes testados ou do ano em questão, o usuário pode testar quantos arquivos desejar, como é o caso do arquivo *todos3.txt*. Apesar de conter um nome que não pertence aos funcionários e ter uma data anterior à maioria dos funcionários atuais, ele foi escrito seguindo o padrão estabelecido e, portanto, pode ser testado normalmente.

O arquivo *nome.txt* só existe pois foi um pedido feito no teste, não é utilizado no código em momento algum.