#!/bin/bash
read -p "UUID: " id
curl --cookie-jar cookies --cookie cookies -X GET -H "Content-Type: application/json" localhost:8080/characters/$id > out.json
