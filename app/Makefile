.DEFAULT_GOAL := build-run

install:
# Очистка от результатов предыдущей сборки
	./gradlew clean
# Создание jar исполняемого файла
	./gradlew installDist

run-dist:
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

.PHONY: build install