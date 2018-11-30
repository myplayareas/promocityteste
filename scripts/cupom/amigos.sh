#!/bin/bash
clear
echo "Insere amigos e ativa o cupom amizade do promocity..."

curl http://localhost:8082/promocity/users/1/add/friend/2 &&
curl http://localhost:8082/promocity/users/1/add/friend/3 &&
curl http://localhost:8082/promocity/users/1/list/friends > retorna-amigos-usuario1.json &&
curl http://localhost:8082/promocity/users/1/activate/coupon/1/store/1/friends/2/3 > retorna-ativacao-cupom1.json &&
curl http://localhost:8082/promocity/stores/1/promotions/1/coupon/consume/1/users/1 > retorna-cupom-consumido.json

echo "Entre em cada um dos arquivos gerados e confirme o conteudo gerado. "