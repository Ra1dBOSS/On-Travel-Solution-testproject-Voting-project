"Приложение-голосовалка"
=================
1 Создание темы для голосования
-----------------------
Для этого нужно отправить POST запрос по адресу /poll/create c Json представлением темы
Например:
```json
{
	"title": "New Poll",
	"question": "Be or Not to be?",
	"answers": [
		{
			"text": "Be!"
		},
		{
			"text": "Not to Be"
		}
	]
}
```
В ответ нам прийдёт Json представление нашей темы инициализированой статусом CREATED 
и ссылки на действия доступные нам.
Например:
```json
{
   "title": "New Poll",
   "question": "Be or Not to be?",
   "status": "CREATED",
   "answers":    [
            {
         "text": "Be!",
         "statistic": 0
      },
            {
         "text": "Not to Be",
         "statistic": 0
      }
   ],
   "hrefs":    [
            {
         "name": "start this poll",
         "href": "/poll/start/1"
      },
            {
         "name": "create new poll",
         "href": "/poll/create/"
      },
            {
         "name": "get this poll",
         "href": "/poll/read/1"
      },
            {
         "name": "update this poll",
         "href": "/poll/update/1"
      },
            {
         "name": "delete this poll",
         "href": "/poll/delete/1"
      }
   ]
}
```
Так же над нашей темой можно производить остальные CRUD действия, ссылки на которые всегда находятся в ответе от сервера.
Найти - GET /poll/read/{id}, изменить - PUT /poll/update/{id}, удалить - DELETE /poll/delete/{id}, создать POST /poll/create/ (где {id} это индефикационный номер нашей темы).

2 Cтарт голосования 
----------
Для этого нужно отправить PUT запрос по адресу /poll/start/{id}, где {id} это индефикационный номер нашей темы
В ответ нам прийдёт Json представление нашей темы инициализированой статусом STARTED 
и ссылки на действия доступные нам, в том числе ссылки для регистрации голоса
```json
{
   "title": "New Poll",
   "question": "Be or Not to be?",
   "status": "STARTED",
   "answers":    [
            {
         "text": "Be!",
         "statistic": 0
      },
            {
         "text": "Not to Be",
         "statistic": 0
      }
   ],
   "hrefs":    [
            {
         "name": "finish this poll",
         "href": "/poll/finish/1"
      },
            {
         "name": "vote for answer number 0 :Be!",
         "href": "/poll/vote/1/2"
      },
            {
         "name": "vote for answer number 1 :Not to Be",
         "href": "/poll/vote/1/3"
      },
            {
         "name": "check out statistic",
         "href": "/poll/stat/1"
      },
            {
         "name": "create new poll",
         "href": "/poll/create/"
      },
            {
         "name": "get this poll",
         "href": "/poll/read/1"
      },
            {
         "name": "update this poll",
         "href": "/poll/update/1"
      },
            {
         "name": "delete this poll",
         "href": "/poll/delete/1"
      }
   ]
}
```
3 Регистрация голоса
-----
Для регистрации голоса нужно отправить PUT запрос по адресу /poll/vote/{id}/{ansid}
, где {id} это индентификационный номер нашей темы, а {ansid} индентификационный номер ответа в голосовании
В ответ нам прийдёт Json представление нашей темы с увеличенным счетчиком голосов рядом с выбраным ответом
и ссылки на действия доступные нам
Например: 
```json
{
   "title": "New Poll",
   "question": "Be or Not to be?",
   "status": "STARTED",
   "answers":    [
            {
         "text": "Be!",
         "statistic": 5
      },
            {
         "text": "Not to Be",
         "statistic": 8
      }
   ],
   "hrefs":    [
            {
         "name": "finish this poll",
         "href": "/poll/finish/1"
      },
            {
         "name": "vote for answer number 0 :Be!",
         "href": "/poll/vote/1/2"
      },
            {
         "name": "vote for answer number 1 :Not to Be",
         "href": "/poll/vote/1/3"
      },
            {
         "name": "check out statistic",
         "href": "/poll/stat/1"
      },
            {
         "name": "create new poll",
         "href": "/poll/create/"
      },
            {
         "name": "get this poll",
         "href": "/poll/read/1"
      },
            {
         "name": "update this poll",
         "href": "/poll/update/1"
      },
            {
         "name": "delete this poll",
         "href": "/poll/delete/1"
      }
   ]
}
```
4 Закрытие темы голосования
-------
Для закрытия темы  нужно отправить PUT запрос по адресу /poll/finis/{id}, где {id} это индентификационный номер нашей темы
В ответ нам прийдёт Json представление нашей темы инициализированой статусом FINISHED
и ссылки на действия доступные нам, в том числе ссылки для просмотра статистики

5 Просмотр статистики
---------
Для просмотра статистики нужно отправить GET запрос по адресу /poll/stat/{id}, где {id} это индентификационный номер нашей темы
В ответ нам прийдёт Json представление нашей темы, ссылки на действия доступные нам и статистика.
Например:
```json
{
   "poll information":    {
      "title": "New Poll",
      "question": "Be or Not to be?",
      "status": "STARTED",
      "answers":       [
                  {
            "id": 2,
            "text": "Be!",
            "statistic": 5
         },
                  {
            "id": 3,
            "text": "Not to Be",
            "statistic": 8
         }
      ],
      "hrefs":       [
                  {
            "name": "finish this poll",
            "href": "/poll/finish/1"
         },
                  {
            "name": "vote for answer with id = 0 :Be!",
            "href": "/poll/vote/1/2"
         },
                  {
            "name": "vote for answer with id = 1 :Not to Be",
            "href": "/poll/vote/1/3"
         },
                  {
            "name": "check out statistic",
            "href": "/poll/stat/1"
         },
                  {
            "name": "create new poll",
            "href": "/poll/create/"
         },
                  {
            "name": "get this poll",
            "href": "/poll/read/1"
         },
                  {
            "name": "update this poll",
            "href": "/poll/update/1"
         },
                  {
            "name": "delete this poll",
            "href": "/poll/delete/1"
         }
      ]
   },
   "poll statistic": "Number of all voted is 13\nLeader is answer with id 3: Not to Be\nWith 8(0) number of votes\n\n5(0) votes for number 0 :Be!\n8(0) votes for number 1 :Not to Be\n"
}
```
P.S.
---
Все данные в приложении сохраняются на базу данных (по умолчанию стоят настройки для подключения к бд PostgreSQL).
Типы запросов в приложении были использованы в соответствии со спецификацией RESTful API (соответственные запросы являются идемпотентными)
