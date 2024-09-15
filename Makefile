.DEFAULT_GOAL := build-run  # Определение цели по умолчанию

# Устанавливает приложение
installDist:
	make -C app installDistl

# Запускает приложение из дистрибутива
run-dist:
	make -C app run-dist

# Собирает проект
build:
	make -C app build

# Запускает приложение
run:
	make -C app run

# Запускает тесты
test:
	make -C app test

# Генерирует отчет о тестировании
report:
	make -C app report

# Выполняет статический анализ кода
lint:
	make -C app lint

# Комбинация сборки и запуска
build-run: build run

# Объявляем все цели как псевдонимы
.PHONY: install run-dist build run test report lint build-run