@echo off
cd /
mkdir godtest
cd godtest
git init
git remote add origin https://github.com/Aking618/godtest.git
git pull origin master
code .
