# Sistema de gerenciamento da UPE
Sistema busca fazer um gerenciamento das seguintes entidades: Professor, Aluno, Curso e Disciplina.

## Desenvolvedores

- Erick Vinícius Bezerra Martins: Parcerias;
- Ana Beatriz de Oliveira Cavalcanti: Local;
- Melissa Rêgo Rodrigues: Reabastecimento;

## Render

- Link da página do cliente: https://balanco-sistema.onrender.com


## Demandas realizadas

### Semana 1 - Luan Vilaça Nogueira
- [x] Atualizei o .gitignore com os arquivos do Intelijj
- [x] Montei a branch develop e minha própria branch (luanvn)
- [x] Criei o github actions para executar os testes em caso de pull request ou push na develop
- [x] Instalei no DB no Render.com
- [x] Linkei o github com o Render.com através do Render.yaml
- [x] Editei a página sobre com o meu nome
- [ ] Coloquei as demandas no README.md

### Semana 1 - Melissa
- atualizar o .gitgnore
- estrutura de branchs
- github actions
- atualizar página Sobre com seu nome
- atualizar o README com as demandas prontas

### Semana 1 - Erick
- atualizar o .gitignore
- atualizar o about.jsp
- estrutura de branchs
- github actions
- atualizar pagina about com meu nome
- atualizar README com demandas

### Semana 1 - Ana
- atualizar o .gitgnore
- estrutura de branchs
- github actions
- atualizar página Sobre com seu nome
- atualizar o README com as demandas prontas

---

### Semana 2 - Melissa
- Permitir instalar tabela Disciplina pelo /install
- Permitir adicionar uma entidade ao sistema
- Permitir listar todos as disciplas cadastradas
- Preencher about
- atualizar README
- permitir ver propriedas das disciplinas

### Semana 2 - Erick
- Permitir instalar tabela Aluno pelo /install
- Permitir adicionar uma entidade ao sistema
- Permitir listar todos os alunos cadastrados
- Permitir ver propeidades do aluno selecionado
- preencher about
- preencher readme
### Semana 2 - Ana
- o usuario consegue instalar as tabelas BD no install
- o usuario consegue adicionar dado da entidade atraves do menu
- o usuario consegue listar a entidade atraves do menu
- preencher about
- preencher readme

### Semana 2 - Luan Vilaça Nogueira
- [x] O usuário consegue instalar a tabela de professor pelo endpoint /install
- [x] O usuário é capaz de criar um novo professor informando seu nome, salário e se ele está ativo
- [x] O usuário consegue visualizar todos os professores ao selecionar "professores" na navbar. A página de listagem fornece uma tabela com as propriedades de cada professor
- [x] O usuário consegue visualizar as propriedades de um professor ao clicar em um registro da lista
- [x] Coloquei as demandas resolvidas no README.md

### Semana 3 - Melissa
- o usuario ao usar /install popula a tabela disciplinas com 7 disciplinas
- o usuario visualiza todos os itens listados com paginação
- o usuário pode deletar uma entidade cadastrada da listagem, mas a entidade não é apagada do banco, so muda sua visualização
- o usuario atraves de um item do menu pode listar os "ocultos"
- README atualizado com as demandas de hoje

---

### Semana 3 - Luan Vilaça Nogueira
- [X] O usuário instala as novas tabelas do banco com 7 registros de professores pelo
  endpoint "/install"
- [X] O usuário visualiza todos os professores listados, mas com paginação de três
  indivíduos
- [X] O usuário pode deletar um professor cadastrado na listagem,
  mas o indivíduo não é apagado do banco, ele é marcado para
  não visualização
- [X] O usuário, por meio de um item de menu, pode listas os
  usuários ocultos
- [X] Atualizei o README.md com as demandas de hoje

### Semana 3 - Erick
- O usuário instala as novas tabelas do banco com 7 indivíduos pelo endpoint "install"
- O usuário visualiza todos os indivíduos listados com paginação de 3 indivíduos
- O usuário pode deletar um indivíduo cadastrado da listagem, sem apagá-lo do banco
- O usuário através de um item de menu pode listar os "ocultos"
- atualização do README com demandas de hoje.

### Semana 3 - Ana
- pendencia da S2: - o usuario consegue ver as propriedades da entidade
- por meio do endpoint install povoar o BD com 7 entidades de Cursos
- o usuario pode visualizar por meio de paginaçao
- atualizar o readme

---

### Semana 4 - Ana
- pendencia da S3: o user pode deletar ocultando e ver os ocultados pelo menu
- atualizar about - task 3
- atualizar o readme

### Semana 4 - Luan Vilaça Nogueira
- [X] Preencher com a regra de negócio escolhida a página About, entre 2 e 5
- [ ] O usuário pode trocar mensagens com outros usuários ao clicar em um botão de chat na
  listagem de usuários
- [ ] Na tela de chat, ficam listadas todas as pessoas com as quais foi iniciada alguma conversa
- [ ] Na tela de chat, as mensagens são diferenciadas pelo nome dos usuários

### Semana 4 - Melissa
- Preencher com a regra de negócio escolhida a página About, entre 2 e 5
- O usuário pode visualizar uma lista de atividades pelo tempo (timeline) suas e dos outros usuários que cadastraram uma entidade
- E curtir a atividade, a atividade na timeline mostra a quantidade de curtidas

### Semana 4 - Erick
- Preencher com a regra de negócio escolhida a página About, entre 2 e 5
- O usuário pode, na listagem de usuários, selecionar um usuário para seguir
- O usuário pode, atraves do endpoint 'seguindo' visualizar quem ele segue
- O usuário pode, deixar de seguir

