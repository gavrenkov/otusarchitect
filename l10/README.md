# Анализ бизнес-процессов

**Цель**: В данном задании тренируются навыки анализа процессов, лежащих в основе системы. Описать бизнес процессы, лежащие в основе проекта в текстовом формате. 

## Проект
Проект описан в [ДЗ к уроку 6](https://github.com/vzaigrin/otusarchitect/tree/master/l06)

У приложения две функции - загрузить данные и показать отчёт.

Бизнес-процессы:
- **Аутентификация пользователя**. Пользователь вводит логин и пароль. Логины и пароли хранятся в базе данных. Логин и пароль, введённый пользователем сравниваются с данными в базе. В случае совпадения пользователь попадает на сайт. В случае несовпадения пользовать получает сообщение о неправильных логине или пароле.
- **Загрузка данных**. При переходе в этот раздел пользователь имеет возможность выбрать файл со своего компьютера для загрузки. Необходимо указать период(месяц и год) за который загружаются данные.
- **Просмотр отчётов**. При переходе в этот раздел пользователь имеет возможность посмотреть отчёты, подготовленные системой. Отчёты хранятся в DWH. Пользователь видит список отчётов. Выбрав отчёт, пользователь может посмотреть его.

