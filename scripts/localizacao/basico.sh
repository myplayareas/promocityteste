#!/bin/bash
clear
echo "Testando monitoramento de localização do promocity..."

curl http://localhost:8082/promocity/users/1/monitoring/location/0.0/0.0 > retorna-promocoes-usuario1-localizacao.json &&
curl http://localhost:8082/promocity/users/1/coupons > retorna-cupons-usuario1.json &&
curl http://localhost:8082/promocity/users/2/monitoring/location/0.0/0.0 > retorna-promocoes-usuario2-localizacao.json &&
curl http://localhost:8082/promocity/users/2/coupons > retorna-cupons-usuario2.json &&
curl http://localhost:8082/promocity/users/3/monitoring/location/0.0/0.0 > retorna-promocoes-usuario3-localizacao.json &&
curl http://localhost:8082/promocity/users/3/coupons > retorna-cupons-usuario3.json

echo "Entre em cada um dos arquivos gerados e confirme o conteudo gerado. "