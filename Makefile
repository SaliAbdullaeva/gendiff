.DEFAULT_GOAL := build-run

run-dist:
	make -C app run-dist

build:
	make -C app build

run:
	make -C app run

test:
	make -C app test

report:
	make -C app report

build-run:
    make -C app build-run

.PHONY: build