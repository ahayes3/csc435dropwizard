#!/bin/bash
read -p "UUID: " id
curl --cookie-jar cookies --cookie cookies -X GET test:1234@localhost:8080/characters/$id > out.json
