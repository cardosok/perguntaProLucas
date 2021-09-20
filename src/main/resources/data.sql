INSERT INTO categoria (id, nome, descricao)
VALUES (1, 'Teste e Validação de Software', 'Teste e Validação de Software');

-- DIFICULDADE: INICIANTE
INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (1,
        'Visam verificar a funcionalidade e a aderência aos requisitos, em uma ótica externa ou do usuário, sem se basear em qualquer conhecimento do código e da lógica interna do componente testado.',
        'INICIANTE', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Testes nulos', false, 1),
       ('Caixa Preta', true, 1),
       ('Caixa Branca', false, 1),
       ('Testes Assertivos', false, 1);

INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (2,
        'Cleiton está realizando testes em um sistema e revisa o código procurando por problemas. Nesse caso, ele está realizando testes de qual tipo?',
        'INICIANTE', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Unitário', false, 2),
       ('Integração', false, 2),
       ('Caixa Branca', true, 2),
       ('Caixa Preta', false, 2);

INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (3, 'Assinale a alternativa correta, sobre automação de teste de software.', 'INICIANTE', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('É a tarefa executada, pelos analistas de testes, tendo como objetivo descrever os fluxos dos UCs do sistema',
        false, 3),
       ('Tem como principal tarefa, ajudar na concepção do software', false, 3),
       ('É um questionário, aplicado aos usuários finais do sistema', false, 3),
       ('É a utilização de um sistema, para controlar a execução dos testes de um software', true,
        3);

INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (4,
        'Segundo Pressman (2011), a definição de defeito de software é um problema de qualidade encontrado.',
        'INICIANTE', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Somente após liberação de uso do software para os usuários finais', true, 4),
       ('Antes de o software ser liberado para os usuários finais', false, 4),
       ('Na fase de revisão', false, 4),
       ('Na fase de prototipação', false, 4);

-- DIFICULDADE: JUNIOR
INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (5,
        'Na verificação de um projeto existe uma categoria de teste que é realizada especificamente para demonstrar a prova de conceito ou viabilidade do projeto. Esta demonstração é realizada por meio do teste:',
        'JUNIOR', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('De qualificação', false, 5),
       ('De desenvolvimento', true, 5),
       ('De aceitação', false, 5),
       ('Operacional', false, 5);

INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (6,
        'Os testes de software são executados, usando os procedimentos e documentos de script de teste. Para que a fase de execução de teste, seja realizada com sucesso deve(m) ser executado(s):',
        'JUNIOR', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Os casos de uso', false, 6),
       ('Os diagramas de atividade', false, 6),
       ('Os casos de teste', true, 6),
       ('Os testes de Turing', false, 6);

INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (7,
        'Assinale a alternativa que NÃO corresponde a uma das fases do processo de desenvolvimento, dirigido a testes (TDD).',
        'JUNIOR', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Executar o teste, com outros testes implementados, que rodarão e fornecerão o resultado de que o software está sem problemas',
        true, 7),
       ('Escrever o teste para a funcionalidade em implementação', false, 7),
       ('Implementar funcionalidade e executar o teste novamente', true, 7),
       ('Implementar a próxima parte da funcionalidade, após todos os testes terem sido executados, com sucesso',
        false, 7);

INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (8,
        'O objetivo é executar o sistema sob o ponto de vista de seu usuário final, varrendo as funcionalidades em busca de falhas em relação aos objetivos originais. Os testes são executados em condições similares àquelas que um usuário utilizará no seu dia-a-dia de manipulação do sistema. A afirmativa refere-se ao teste de:',
        'JUNIOR', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Aceitação', false, 8),
       ('Sistema ', true, 8),
       ('Unidade', false, 8),
       ('Operação', false, 8);

INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (9,
        'O objetivo é executar o sistema sob o ponto de vista de seu usuário final, varrendo as funcionalidades em busca de falhas em relação aos objetivos originais. Os testes são executados em condições similares àquelas que um usuário utilizará no seu dia-a-dia de manipulação do sistema. A afirmativa refere-se ao teste de:',
        'JUNIOR', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Aceitação', false, 9),
       ('Sistema ', true, 9),
       ('Unidade', false, 9),
       ('Operação', false, 9);

-- DIFICULDADE: PLENO
INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (10,
        'O teste que força o software a falhar de diversos modos e verifica se o restabelecimento às condições normais está adequado é conhecido como teste de:',
        'PLENO', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Estresse', false, 10),
       ('Segurança ', false, 10),
       ('Recuperação', true, 10),
       ('Desempenho', false, 10);

INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (11,
        'O teste é uma atividade importante durante o desenvolvimento de um software, pois ajuda a garantir a qualidade. Acerca de teste de software, assinale a alternativa correta.',
        'PLENO', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('O teste de software permite encontrar todos os erros de um sistema.', false, 11),
       ('O erro é definido como a visualização de um problema pelo usuário. ', false, 11),
       ('O teste de software é uma técnica de V&V dinâmica.', true, 11),
       ('É possível testar todos os valores de entrada de um software.', false, 11);

INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (12,
        'Em relação ao teste de software, assinale a alternativa correta sobre o teste de integração:',
        'PLENO', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Tem como objetivo explorar a menor unidade de um projeto', false, 12),
       ('Visa testar as falhas decorrentes da integração dos módulos do sistema', true, 12),
       ('Teste realizado pelos usuários finais do sistema', false, 12),
       ('Tem a mesma aplicação do teste de aceitação', false, 12);

INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (13,
        'Tipo de teste que focaliza cada componente de um software de forma individual, garantindo que o componente funciona adequadamente:',
        'PLENO', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Teste de Integração', false, 13),
       ('Teste de Unidade', true, 13),
       ('Teste de Regressão', false, 13),
       ('Teste de Validação', false, 13);

-- DIFICULDADE: SENIOR
INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (14,
        'Um dos tipos de teste de software é o denominado teste de recuperação, no qual, basicamente:',
        'SENIOR', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Se exercita o software com uma demanda por recursos acima do limite especificado.', false,
        14),
       ('Se verifica se há brechas de segurança que permitam a invasão do software.', false, 14),
       ('São testados, individualmente, cada um dos módulos de software, registrando-se seus tempos de execução.',
        false, 14),
       ('Se força o software a falhar e verifica-se se ocorre a retomada do processamento normal nessa condição.',
        true, 14);

INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (15,
        'Acerca dos testes ágeis, assinale a opção que corresponde à metodologia caracterizada por sua adaptabilidade às mudanças de cada iteração, e na qual nem sempre é fornecida documentação detalhada sobre como o aplicativo é testado.',
        'SENIOR', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Desenvolvimento orientado a comportamentos (BDD)', false, 15),
       ('Desenvolvimento orientado a testes de aceitação (ATDD)', false, 15),
       ('Teste exploratório', true, 15),
       ('Teste baseado em sessão', false, 15);

INSERT INTO pergunta (id, texto, dificuldade, categoria_id)
VALUES (16, 'Testes de partições são usados para testar', 'SENIOR', 1);

INSERT INTO opcao (texto, correto, pergunta_id)
VALUES ('Os requisitos do software', false, 16),
       ('A estrutura do software', false, 16),
       ('Diferentes tipos ou domínios de entradas e saídas de dados', true, 16),
       ('O armazenamento do software e seus dados', false, 16);
