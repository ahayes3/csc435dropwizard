#!/bin/bash
read -p "UUID: " id

curl --cookie-jar cookies --cookie cookies -X PUT -H "Content-Type: application/json" -d @test2.json test:1234@localhost:8080/characters/$id > out.json
