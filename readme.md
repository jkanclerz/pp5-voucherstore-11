## Voucher Shop

![Java CI with Maven](https://github.com/jkanclerz/pp5-voucherstore-11/workflows/Java%20CI%20with%20Maven/badge.svg)

### Testing

```bash
mvn test
```

### testing via curl

```bash
curl -X POST localhost:9999/api/clients -H 'content-type: application/json' -d '{"firstname": "Kuba", "lastname": "Kanclerz", "address": {"street": "rakowicka"}}'
curl -X POST localhost:9999/api/clients -H 'content-type: application/json' -d '{"firstname": "Kuba", "lastname": "Kanclerz", "address": {"street": "rakowicka"}}'
curl -X POST localhost:9999/api/clients -H 'content-type: application/json' -d '{"firstname": "Kuba", "lastname": "Kanclerz", "address": {"street": "rakowicka"}}'

curl localhost:9999/api/clients | jq
curl localhost:9999/api/clients | python -m json.tool
```