#!/bin/sh

make clean && make && cd cls && echo "java Triangle.Compiler ../joe.tri" && java Triangle.Compiler ../joe.tri

echo "java TAM.Intepreter obj.tam\n"
java TAM.Interpreter obj.tam
