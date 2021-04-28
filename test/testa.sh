#!/bin/bash
curl --cookie-jar cookies --cookie cookies -X POST -H "Content-Type: application/json" -d @test.json localhost:8080/characters > out.json
