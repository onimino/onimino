#!/bin/bash
java -jar target/onimino-server-all-*.jar -M -o target/stdout.txt -r -p target/server.pid -- --nogui "$@"
