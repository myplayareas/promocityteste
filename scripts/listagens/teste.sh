#!/bin/bash
clear
echo "Testando as listagens básicas do promocity..."

curl http://localhost:8082/promocity > lista-recursos.json &&
curl http://localhost:8082/promocity/users > lista-usuarios.json &&
curl http://localhost:8082/promocity/stores > lista-lojas.json &&
curl http://localhost:8082/promocity/promotions > lista-promocoes.json &&
curl http://localhost:8082/promocity/coupons > lista-cupons.json &&
curl http://localhost:8082/promocity/users/armando@ufpi.edu.br/armando > retorna-dados-usuario-autenticado.json

echo "Entre em cada um dos arquivos gerados e confirme o conteudo gerado. "