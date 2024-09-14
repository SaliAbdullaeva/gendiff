.DEFAULT_GOAL := build-run

install:
    ./gradlew installDist

installDist:
# Очистка от результатов предыдущей сборки
	./gradlew clean
# Создание jar исполняемого файла
	./gradlew installDist

run-dist: installDist
# Запуск исполняемого файла
	./build/install/app/bin/app

build:
	./gradlew build

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

build-run: build run

.PHONY: install build installDist run-dist test report lint build-run