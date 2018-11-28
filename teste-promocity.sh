#!/bin/bash
clear
echo "Testando os principais recursos do promocity..."

curl http://localhost:8082/promocity > lista-recursos.json &&
curl http://localhost:8082/promocity/users > lista-usuarios.json &&
curl http://localhost:8082/promocity/stores > lista-lojas.json &&
curl http://localhost:8082/promocity/promotions > lista-promocoes.json &&
curl http://localhost:8082/promocity/coupons > lista-cupons.json &&
curl http://localhost:8082/promocity/users/armando@ufpi.edu.br/armando > retorna-dados-usuario-autenticado.json &&
curl http://localhost:8082/promocity/users/1/monitoring/location/0.0/0.0 > retorna-promocoes-usuario-localizacao.json &&
curl http://localhost:8082/promocity/users/1/coupons > retorna-cupons-usuario.json &&
curl http://localhost:8082/promocity/users/1/list/friends > retorna-amigos-usuario.json

echo "Entre em cada um dos arquivos gerados e confirme o contedo gerado. "