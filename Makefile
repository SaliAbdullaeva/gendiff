# Определяет цель (target), которая будет выполнена по умолчанию при вызове make без явного указания цели
.DEFAULT_GOAL := build-run

install:
# Выполнение команды 'install' из поддиректории 'app'
    make -C app install

# Вызывает make в директории 'app' для установки приложения
installDist:
	make -C app installDist

# ..запуска приложения из дистрибутива
run-dist:
	make -C app run-dist

# ..сборки проекта
build:
	make -C app build

# ..запуска приложения
run:
	make -C app run

# ..запуска тестов
test:
	make -C app test

# ..генерации отчета о тестировании
report:
	make -C app report

# ..выполнения статического анализа кода
lint:
	make -C app lint

# ..комбинации сборки и запуска
build-run: build run

# Фиктивная цель для предотвращения конфликтов с файлами
.PHONY: install build installDist run-dist test report lint build-run