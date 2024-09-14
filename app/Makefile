.DEFAULT_GOAL := build-run

installDist:
	./gradlew installDist

run-dist:
	# Очистка от результатов предыдущей сборки
	./gradlew clean
	# Создание jar исполняемого файла
	./gradlew installDist
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

.PHONY: build