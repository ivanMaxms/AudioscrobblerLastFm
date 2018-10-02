# Требования к проекту
---

# Содержание
1 [Введение](#intro)<br> 
  1.1 [Назначение](#appointment)<br>
  1.2 [Бизнес-требования](#business_requirements)<br> 
    1.2.1 [Исходные данные](#initial_data)<br>
    1.2.2 [Границы проекта](#project_boundary)<br>
  1.3 [Аналоги](#analogues)<br>
2 [Требования пользователя](#user_requirements)<br>  
2.1 [Программные интерфейсы](#software_interfaces)<br> 
  2.2 [Интерфейс пользователя](#user_interface)<br> 
  2.3 [Характеристики пользователей](#user_specifications)<br> 
    2.3.1 [Классы пользователей](#user_classes)<br>  
    2.3.2 [Аудитория приложения](#application_audience)<br>   
      2.3.2.1 [Целевая аудитория](#target_audience)<br>   
      2.3.2.1 [Побочная аудитория](#collateral_audience)<br>
   2.4 [Предположения и зависимости](#assumptions_and_dependencies)<br>   
3 [Системные требования](#system_requirements)<br> 
  3.1 [Функциональные требования](#functional_requirements)<br>   
    3.1.1 [Основные функции](#main_functions)<br>  
      3.1.1.1 [Вход пользователя в приложение](#user_logon_to_the_application)<br>  
      3.1.1.2 [Настройка профиля активного пользователя](#setting_up_the_profile_of_the_active_user)<br>  
      3.1.1.3 [Загрузка новостей](#download_news)<br> 
      3.1.1.4 [Просмотр информации об отдельной новости](#view_information_about_an_individual_newsletter)<br> 
      3.1.1.5 [Выход пользователя из учётной записи](#active_user_change)<br> 
      3.1.1.6 [Регистрация нового пользователя после входа в приложение](#add_new_user)<br>   
    3.1.2 [Ограничения и исключения](#restrictions_and_exclusions)<br>  
  3.2 [Нефункциональные требования](#non-functional_requirements)<br>   
    3.2.1 [Атрибуты качества](#quality_attributes)<br> 
      3.2.1.1 [Требования к удобству использования](#requirements_for_ease_of_use)<br> 
      3.2.1.2 [Требования к безопасности](#security_requirements)<br>  
    3.2.2 [Внешние интерфейсы](#external_interfaces)<br>
    3.2.3 [Ограничения](#restrictions)<br> 

<a name="intro"/>

# 1 Введение

<a name="appointment"/>

## 1.1 Назначение
В этом документе описаны функциональные и нефункциональные требования к приложению «MXMS-player». Документ предназначен для команды, которая будет реализовывать и проверять корректность работы приложения. 

<a name="business_requirements"/>

## 1.2 Бизнес-требования

<a name="initial_data"/>

### 1.2.1 Исходные данные
На сегодняшний день многие люди слушают музыку. Часто одна и та же музыка надоедает, поэтому многим может быть полезно приложение, которое будет не только давать возможность прослушивать музыку, но и собирать и обрабатывать статистику и ее прослушивании и выдавать рекомендации.


<a name="project_boundary"/>

### 1.2.2 Границы проекта
Приложение «MXMS-player» позволит его пользователям прослушивать треки, находящиеся в памяти его Desktop-устройства.

<a name="analogues"/>

## 1.3 Аналоги
Обзор аналогов представлен в документе [Overview of analogues](../Requirements/Overview%20of%20analogues.md).

<a name="user_requirements"/>

# 2 Требования пользователя

<a name="software_interfaces"/>

## 2.1 Программные интерфейсы
Приложение проигрывает аудиофайлы с расширением .mp3, а также, обменивается данными с сайтом Last.fm с помощью веб-запросов. 

<a name="user_interface"/>

## 2.2 Интерфейс пользователя
Окно входа в приложение.  
![Окно входа в приложение](../../images/mockups/MainWindow.jpg)  
Окно авторизации на сайте.  
![Окно авторизации на сайте](../../images/mockups/LoginWindow.jpg)  
