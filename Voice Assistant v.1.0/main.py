# Voice Assistant 0.9
import pyttsx3
import pyaudio
import speech_recognition as sr
import colorama
from fuzzywuzzy import fuzz
import datetime
from os import system, path, makedirs, startfile
import os
import sys
from random import choice
from pyowm import OWM
from pyowm.utils.config import get_default_config
import webbrowser
import configparser
from psutil import virtual_memory as memory
import nltk
import json
import wikipedia as wiki
import re
import requests
from bs4 import BeautifulSoup
from forex_python.converter import CurrencyRates
import psutil
import docx
# === NEW ===
import winsound
import pyshorteners
from pywhatkit import playonyt
from PyQt5 import QtWidgets, QtCore
import interface
import threading


with open('NEW_BIG_BOT_CONFIG.json', 'r') as f:
    BOT_CONFIG = json.load(f)


class Assistant(QtWidgets.QMainWindow, interface.Ui_MainWindow, threading.Thread):
    settings = configparser.ConfigParser()
    settings.read('settings.ini')

    config_dict = get_default_config()  # Инициализация get_default_config()
    config_dict['language'] = 'ru'  # Установка языка

    last_dir = ''

    def __init__(self):
        # === NEW ===
        super().__init__()
        self.setupUi(self)
        self.pushButton.clicked.connect(self.start_thread)
        self.pushButton_2.clicked.connect(self.stop)
        self.working = False

        self.r = sr.Recognizer()
        self.text = ''

        self.cmds = {
            ('привет', 'добрый день', 'здравствуй'): self.hello,
            ('пока', 'вырубись'): self.quite,
            ('добавить задачу', 'добавить заметку', 'создай заметку', 'создай задачу'): self.task_planner,
            ('открой документ', 'документ'): self.task_list,
        }

        self.ndels = ['морган', 'морген', 'моргэн', 'морг', 'ладно', 'не могла бы ты', 'пожалуйста',
                      'текущее', 'сейчас']

        self.commands = [
            'текущее время', 'сейчас времени', 'который час',
            'открой браузер', 'открой интернет', 'запусти браузер',
            'привет', 'добрый день', 'здравствуй',
            'пока', 'вырубись',
            'выключи компьютер', 'выруби компьютер',
            'какая погода', 'погода', 'погода на улице', 'какая погода на улице',
            'добавить задачу', 'добавить заметку', 'создай заметку', 'создай задачу',
                                                                     'список задач', 'список заметок', 'задачи',
            'заметки',
            'загруженость компьютера', 'загруженость системы', 'какая загрузка',
            'место на диске', 'сколько памяти', 'сколько памяти на диске', 'сколько места',
            'перезагрузи компьютер', 'перезагрузи комп', 'перезагружай комп', 'перезагрузи',
            'открой файл',
    
        ]

        self.num_task = 0
        self.j = 0
        self.ans = ''

        wiki.set_lang('ru')
    #Функции по завершению работы ассистента
    def stop(self):
        self.working = False
        self.quite()


    def cleaner(self, text):
        self.text = text

        for i in self.ndels:
            self.text = self.text.replace(i, '').strip()
            self.text = self.text.replace('  ', ' ').strip()

        self.ans = self.text

        for i in range(len(self.commands)):
            k = fuzz.ratio(text, self.commands[i])
            if (k > 70) & (k > self.j):
                self.ans = self.commands[i]
                self.j = k

        return str(self.ans)
    
    
    def intent_cleaner(self, text):
        cleaned_text = ''
        for i in text.lower():
            if i in 'абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz ':
                cleaned_text += i
        return cleaned_text

    def match(self, text, example):
        return nltk.edit_distance(text, example) / len(example) < 0.4 if len(example) > 0 else False

    def get_intent(self, text):
        for intent in BOT_CONFIG['intents']:
            if 'examples' in BOT_CONFIG['intents'][intent]:
                for example in BOT_CONFIG['intents'][intent]['examples']:
                    if self.match(self.intent_cleaner(text), self.intent_cleaner(example)):
                        return intent

    def intenter(self, text):
        intent = self.get_intent(text)

        if intent is None:
            return

        self.talk(choice(BOT_CONFIG['intents'][intent]['responses']))
        
    # Инициализации команды по добавлению задачи в список задач
    def task_planner(self): 
        self.talk("Что добавить в список задач?")
        task = self.listen()

        with open('TODO_LIST.txt', 'w') as file:
            file.write(f'{task}\n')

        self.talk(f'Файл {task} открыт!')

    # Инициализации команды по открытию задачи в списоке задач
    def task_list(self):
        
        task=docx.Document('Определение KPI для начальника отдела сбыта.docx')
        self.talk(task)
       

    #Функция распознования
    def recognizer(self):
        self.text = self.cleaner(self.listen())
        print(self.text)

        if self.text.startswith(('открой', 'запусти', 'зайди', 'зайди на')):
            self.opener(self.text)

        elif self.text.startswith(('найди', 'найти', 'ищи', 'кто такой', 'что такое')):
            self.web_search(self.text)

        elif self.text.startswith(('поставь будильник', 'поставить будильник', 'запусти будильник', 'запустить будильник')):
            self.alarm(self.text)

        for tasks in self.cmds:
            for task in tasks:
                if fuzz.ratio(task, self.text) >= 80:
                    self.cmds[tasks]()
                    return

        self.intenter(self.text)

    #Функция по открытию
    def opener(self, task):
        links = {
            ('почта', 'почту', 'gmail', 'гмейл', 'гмеил', 'гмаил'): 'http://gmail.com/',
        }
        #Функция по открытию приложения калькулятор
        def open_calc():
            self.talk("открываю калькулятор")
            system("calc")

        programs = {
            'калькулятор':  open_calc, 'calculator':  open_calc
        }

        j = 0
        if 'и' in task:
            task = task.replace('и', '').replace('  ', ' ')
        double_task = task.split()
        if j != len(double_task):
            for i in range(len(double_task)):
                for vals in links:
                    for word in vals:
                        if fuzz.ratio(word, double_task[i]) > 75:
                            webbrowser.open(links[vals])
                            self.talk('Открываю ' + double_task[i])
                            j += 1
                            break
                else:
                    for vals in programs:
                        if fuzz.ratio(vals, double_task[i]) > 75:
                            programs[vals]()
                            j += 1
                            break

    #Функция выдачи ассистентом фраз пользователю при его отключении
    def quite(self):
        self.talk(choice(['Надеюсь мы скоро увидимся', 'Рада была помочь', 'Пока пока', 'Я отключаюсь']))
        self.engine.stop()
        system('cls')
        sys.exit(0)

    #Функция содержащее приветствия ассистента
    def hello(self):
        self.talk(choice(['Привет, чем могу помочь?', 'Здраствуйте', 'Приветствую']))

    #Функция для активации приветствий
    def start_thread(self):
        self.hello()
        self.working = True
        self.thread = threading.Thread(target=self.main)
        self.thread.start()
   
    #Функция по реализации обращения ассистента к пользователю
    def talk(self, text):
        self.engine = pyttsx3.init(debug=True)
        print(text)
        item = QtWidgets.QListWidgetItem()
        item.setTextAlignment(QtCore.Qt.AlignLeft)
        item.setText('Ассистент:' + '\n' + text)
        self.listWidget.addItem(item)
        self.listWidget.scrollToBottom()

        self.engine.say(text)
        self.engine.runAndWait()
        self.engine.stop()

    #Функция разпознования речи
    def listen(self):
        # === NEW ===
        self.text = ''

        with sr.Microphone() as source:
            print(f"{colorama.Fore.LIGHTGREEN_EX}Я вас слушаю...")
            self.r.adjust_for_ambient_noise(source)
            audio = self.r.listen(source)
            try:
                self.text = self.r.recognize_google(audio, language="ru-RU").lower()
            except Exception as e:
                print(e)

            if self.text != '':
                item = QtWidgets.QListWidgetItem()
                item.setTextAlignment(QtCore.Qt.AlignRight)
                item.setText('ВЫ:' + '\n' + self.text) # Выводит то что сказал пользователь
                self.listWidget.addItem(item)
                self.listWidget.scrollToBottom()

            return self.text


    def main(self):

        while self.working:
            try:
                self.recognizer()
            except Exception as ex:
                print(ex)


if __name__ == "__main__":
    app = QtWidgets.QApplication(sys.argv)
    window = Assistant()
    window.show()
    sys.exit(app.exec_())
