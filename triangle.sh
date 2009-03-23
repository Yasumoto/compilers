#!/bin/sh

make

cd cls

java Triangle.Compiler ../tests/joe2.tri

#java TAM.Interpreter
