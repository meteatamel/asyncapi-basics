# Quickstart

This sample shows how to generate an AsyncAPI spec, validate it, and generate
code from it using AsyncAPI tools.

## Installation

First, install the following tools:

* [AsyncAPI CLI](https://www.asyncapi.com/tools/cli)
* [AsyncAPI Generator](https://www.asyncapi.com/tools/generator)

Also install MQTT that we will need later:

```sh
npm install mqtt -g
```

## Generate an AsyncAPI spec

Generate an AsyncAPI spec using the AsyncAPI CLI tool that can pull in samples
from [asyncapi/spec/examples](https://github.com/asyncapi/spec/tree/master/examples).

```sh
> asyncapi new

? name of the file? asyncapi new -n streetlights.yaml
? would you like to start your new file from one of our examples? Y
? What example would you like to use? Streetlights API Simplified - (protocols:
mqtt)
? open in Studio? n
Created file streetlights.yaml...
```

## Validate the AsyncAPI spec

Validate the AsyncAPI spec:

```sh
asyncapi validate streetlights.yaml
```

## Generate code from the AsyncAPI spec

Generate code from the AsyncAPI spec:

```sh
asyncapi generate fromTemplate streetlights.yaml @asyncapi/nodejs-template -o streetlights -p server=mosquitto
```

## Run

Start generated app:

```sh
cd streetlights
npm install
npm start
```

Send message to the generated app:

```sh
mqtt pub -t 'light/measured' -h 'test.mosquitto.org' -m '{"id": 1, "lumens": 3, "sentAt": "2022-06-07T12:34:32.000Z"}'
```
