# KidzooToystore
KidzooToystore

## Introduction
API which returns list of all toys to sell. API consumers would be able to filter toys based on price.

### Kidzoo Endpoint

Purpose                                                                                                                  | Method  | Endpoint
------------------------------------------------------------------------------------------------------------------------ | :------ | :-----------------------------------------------------
Service to get all the available toys                                                                                    | GET     | [kidzoo/v1/api/getAvailableToys](http://localhost:8090/kidzoo/v1/api/getAvailableToys)
Service to get all the available toys from the given price                                                               | GET     | [kidzoo/v1/api/getAvailableToys?fromPrice=<Value>](http://localhost:8090/kidzoo/v1/api/getAvailableToys?fromPrice=500)
Service to get all the available toys based till the given price                                                         | GET     | [kidzoo/v1/api/getAvailableToys?toPrice=<Value>](http://localhost:8090/kidzoo/v1/api/getAvailableToys?toPrice=500)
Service to get all the available toys based till the given price                                                         | GET     | [kidzoo/v1/api/getAvailableToys?fromPrice=<Value>&toPrice=<Value>](http://localhost:8090/kidzoo/v1/api/getAvailableToys?fromPrice=500&toPrice=1000)

