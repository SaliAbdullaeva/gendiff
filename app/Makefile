.DEFAULT_GOAL := build-run  # Определение цели по умолчанию

# Устанавливает дистрибутив
install:
	./gradlew installDist

# Запускает приложение из дистрибутива
run-dist:
	# Очистка от результатов предыдущей сборки
	./gradlew clean
	# Создание jar исполняемого файла
	./gradlew installDist
	# Запуск исполняемого файла
	./build/install/app/bin/app

# Собирает проект
build:
	./gradlew build

# Запускает приложение
run:
	./gradlew run

# Запускает тесты
test:
	./gradlew test

# Генерирует отчет о тестировании
report:
	./gradlew jacocoTestReport

# Выполняет статический анализ кода
lint:
	./gradlew checkstyleMain

# Комбинация сборки и запуска
build-run: build run

# Объявляем все цели как псевдонимы
.PHONY: install run-dist build run test report lint build-run