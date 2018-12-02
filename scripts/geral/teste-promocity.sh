#!/bin/bash
clear
echo "Testando os principais recursos do promocity..."

curl http://localhost:8082/promocity > lista-recursos.json &&
curl http://localhost:8082/promocity/users > lista-usuarios.json &&
curl http://localhost:8082/promocity/stores > lista-lojas.json &&
curl http://localhost:8082/promocity/promotions > lista-promocoes.json &&
curl http://localhost:8082/promocity/coupons > lista-cupons.json &&
curl http://localhost:8082/promocity/users/armando@ufpi.edu.br/armando > retorna-dados-usuario-autenticado.json &&
curl http://localhost:8082/promocity/users/1/monitoring/location/0.0/0.0 > retorna-promocoes-usuario1-localizacao.json &&
curl http://localhost:8082/promocity/users/1/coupons > retorna-cupons-usuario1.json &&
curl http://localhost:8082/promocity/users/2/monitoring/location/0.0/0.0 > retorna-promocoes-usuario2-localizacao.json &&
curl http://localhost:8082/promocity/users/2/coupons > retorna-cupons-usuario2.json &&
curl http://localhost:8082/promocity/users/3/monitoring/location/0.0/0.0 > retorna-promocoes-usuario3-localizacao.json &&
curl http://localhost:8082/promocity/users/3/coupons > retorna-cupons-usuario3.json &&
curl http://localhost:8082/promocity/users/1/add/friend/2 > friends12.json &&
curl http://localhost:8082/promocity/users/1/add/friend/3 > friends13.json &&
curl http://localhost:8082/promocity/users/1/list/friends > retorna-amigos-usuario1.json &&
curl http://localhost:8082/promocity/users/1/activate/coupon/1/store/1/friends/2/3 > retorna-ativacao-cupom1.json &&
curl http://localhost:8082/promocity/stores/1/promotions/1/coupon/consume/1/users/1 > retorna-cupom-consumido.json &&
cat lista-recursos.json lista-usuarios.json lista-lojas.json lista-promocoes.json lista-cupons.json retorna-dados-usuario-autenticado.json retorna-promocoes-usuario1-localizacao.json retorna-cupons-usuario1.json retorna-promocoes-usuario2-localizacao.json retorna-cupons-usuario2.json retorna-promocoes-usuario3-localizacao.json retorna-cupons-usuario3.json friends12.json friends13.json retorna-amigos-usuario1.json retorna-ativacao-cupom1.json retorna-cupom-consumido.json > resultado.json

echo "Entre em cada um dos arquivos gerados ou no arquivo resultados.json e confirme o contedo gerado. "
