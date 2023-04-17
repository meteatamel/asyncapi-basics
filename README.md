# AsyncAPI Basics

![AsyncAPI Logo](https://avatars.githubusercontent.com/u/16401334?s=200&v=4)

This repository contains information, references, and samples about AsyncAPI.

## AsyncAPI: Why? What?

[AsyncAPI](https://www.asyncapi.com/) is an open source initiative with the goal
of making event-driven APIs as easy as REST APIs. It has a specification that
allows developers, architects, and product managers to define the interfaces of
an async API, similar to what OpenAPI (aka Swagger) does for REST APIs.

## Concepts

These are the main concepts in AsyncAPI:

* **Broker / Server**: An infrastructure that receives messages and delivers them
to those interested. They often store messages until delivered. Example brokers
are RabbitMQ, Apache Kafka, Solace, etc.

* **Publisher / Producer**: An application that send messages to the broker.

* **Subscriber / Consumer**: An application that listens for particular events
from a broker.

* **Channel**: Created by the server to organize transmission of messages. Users
can define channels as a *topic*, *queue*, *routing key*, *path*, or *subject*
depending on the protocol used.

* **Protocol**: A set of rules that specifies how information is exchanged between
applications and servers such as *WebSockets*, *HTTP*, *Kafka*, *MQTT*.

* **Message**: A message is a piece of information that is sent by publishers to
the broker, and received by all interested subscribers through a channel. Can
also be defined as an *event* (to communicate that a fact has occured) or
*command* (to instruct subscriber to do something).

## OpenAPI vs. AsyncAPI

You can see the [following
diagram](https://www.asyncapi.com/docs/tutorials/getting-started/coming-from-openapi)
for a comparison between OpenAPI and AsyncAPI.

## How does an Async spec look like?

An application that has a single channel called `hello` and capable of receiving
messages that match "hello {name}":

```yaml
asyncapi: 2.6.0
info:
  title: Hello world application
  version: '0.1.0'
channels:
  hello:
    publish:
      message:
        payload:
          type: string
          pattern: '^hello .+$'
```

Another example with a server and multiple channels: [hello-world.yaml](hello-world.yaml).

## Publish vs. Subscribe in AsyncAPI

In a channel, you can have `publish` and `subscribe` semantics. This can be
confusing, depending on which perspective you're considering (server vs. user)
and what you're comparing against (eg. WebSocket).

| AsyncAPI Term | WebSocket Term | From Server Perspective | From User Perspective |
| --- | --- | --- | --- |
| Publish | Send | The server receives the message | The user publishes/sends the message to the server |
| Subscribe | Receive | The server publishes the message | The user subscribes/receives the message from the server |

It's most useful to think of `publish` and `subscribe` from user's perspective. You can read [Demystifying the Semantics of Publish and
Subscribe](https://www.asyncapi.com/blog/publish-subscribe-semantics) for more
details.

## Tools

* [AsyncStudio](https://studio.asyncapi.com/): Browser based tool to author and
  visualize and validate AsyncAPI files.
* [AsyncAPI CLI](https://github.com/asyncapi/cli): CLI based tool to work with
  AsyncAPI files.
* [AsyncAPI Generator](https://github.com/asyncapi/generator): A number of code
  generators for various languages and frameworks.

## Quickstart

Generate an AsyncAPI file using the AsyncAPI CLI tool that can pull in samples
from [asyncapi/spec/examples](https://github.com/asyncapi/spec/tree/master/examples).

```sh
> asyncapi new

? name of the file? asyncapi.yaml
? would you like to start your new file from one of our examples? Yes
? What example would you like to use? Streetlights API Simplified - (protocols:
mqtt)
? open in Studio? No
Created file asyncapi.yaml...
```

Validate the AsyncAPI file:

```sh
asyncapi validate asyncapi.yaml
```

Generate code from the AsyncAPI file:

```sh
asyncapi generate fromTemplate asyncapi.yaml @asyncapi/nodejs-template -o output -p server=mosquitto
```

Start generated application:

```sh
npm install
npm start
```

In a separate terminal, send message to the generated application:

```sh
npm install mqtt -g
mqtt pub -t 'light/measured' -h 'test.mosquitto.org' -m '{"id": 1, "lumens": 3, "sentAt": "2017-06-07T12:34:32.000Z"}'
```

## References

* [AsyncAPI home](https://www.asyncapi.com/)
* [Async API GitHub](https://github.com/asyncapi)
* [Async API 2.6.0 Spec](https://www.asyncapi.com/docs/reference/specification/v2.6.0)
* [Demystifying the Semantics of Publish and Subscribe](https://www.asyncapi.com/blog/publish-subscribe-semantics)