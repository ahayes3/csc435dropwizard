#!/bin/bash
curl --cookie-jar cookies --cookie cookies -X POST -H "Content-Type: application/json" -d @testa.json localhost:8080/characters > out.json
