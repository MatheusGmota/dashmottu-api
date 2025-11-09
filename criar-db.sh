#!/bin/bash

RESOURCE_GROUP="rg-dashmottu"
LOCATION="brazilsouth"
ACI_NAME="oracle-instance"
ORACLE_PASSWORD=""

echo "🔹 Criando Container Instance com Oracle FREE..."

az container create \
  --resource-group $RESOURCE_GROUP \
  --name $ACI_NAME \
  --image gvenzl/oracle-free \
  --os-type Linux \
  --ports 1521 \
  --cpu 2 \
  --memory 4 \
  --environment-variables \
    ORACLE_PASSWORD=$ORACLE_PASSWORD \
  --dns-name-label "dashmottu-oracle$RANDOM" \
  --location $LOCATION


DB_FQDN=$(az container show \
  --resource-group $RESOURCE_GROUP \
  --name $ACI_NAME \
  --query ipAddress.fqdn -o tsv)

echo "Banco disponível em: $DB_FQDN:1521/freepdb1"
echo "String de conexão JDBC: jdbc:oracle:thin:@$DB_FQDN:1521/freepdb1"
