@echo off
%~d0
cd %~dp0
cd decompile


REM ɾ���ļ���
rd /s/q apk

echo ���ڷ�����...
java -jar apktool.jar d -f %1  apk >nul 2>nul


REM �����ļ���������
echo f|xcopy %1 apk\ori.apk >nul 2>nul

java -jar smali.jar apk/smali/ -o apk/classes.dex >nul 2>nul

dex2jar/dex2jar.bat -o -D -d apk/ apk/classes.dex 

pause