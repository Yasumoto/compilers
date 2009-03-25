#!/bin/sh

make

cd cls

java Triangle.Compiler ../tests/joe.tri

#java TAM.Interpreter
