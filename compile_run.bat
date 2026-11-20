@echo off
mkdir out 2>nul
javac -d out src\com\auca\interfaces\*.java src\com\auca\exceptions\*.java src\com\auca\abstracts\*.java src\com\auca\individualtaxpayer\*.java src\com\auca\corporatetaxpayer\*.java src\com\auca\revenueofficer\*.java src\com\auca\taxtype\*.java src\com\auca\taxobligation\*.java src\com\auca\taxdeclaration\*.java src\com\auca\taxassessment\*.java src\com\auca\taxpayment\*.java src\com\auca\penalty\*.java src\com\auca\taxrefund\*.java src\com\auca\taxclearance\*.java src\com\auca\main\*.java
if errorlevel 1 pause & exit /b 1
java -cp out com.auca.main.Main
