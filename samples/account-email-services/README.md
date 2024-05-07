# Account and Email Services

This sample shows how to author an AsyncAPI spec for a simple 2 microservices
architecture defined in [Understanding AsyncAPIs with a Practical
Example](https://medium.com/event-driven-utopia/understanding-asyncapis-with-a-practical-example-ee2b4be221d8)
blog post:

![Account and Email Services](https://miro.medium.com/v2/resize:fit:720/format:webp/1*pNvIViY4x5vZhOlnM8dqUw.png)

## Installation

First, install the following tools:

* [AsyncAPI CLI](https://www.asyncapi.com/tools/cli)
* [AsyncAPI Generator](https://www.asyncapi.com/tools/generator)

Also install MQTT that we will need later:

```sh
npm install mqtt -g
```

## Define AsyncAPI 2.6.0 specs

Define [account-service-2.6.yaml](./account-service-2.6.yaml) for the Account Service.
The key part is the `subscribe` operation that says "This service publishes
messages to the `user/signedup` channel:

```yaml
channels:
  user/signedup:
    subscribe:
      operationId: publishUserSignedUp
      message:
      ...
```

Define [email-service-2.6.yaml](./email-service-2.6.yaml) for the Email Service.
The key part is the `publish` operation that says "This service receives
messages from the `user/signedup` channel:

```yaml
channels:
  user/signedup:
    publish:
      operationId: receiveUserSignedUp
      message:
      ...
```

Note: Since most of [account-service-2.6.yaml](./account-service-2.6.yaml) and
[email-service-2.6.yaml](./email-service-2.6.yaml) are the same, you can instead created
a [common-2.6.yaml](./commo-2.6.yaml) to encapsulate the common config and simply refer
to hem from [account-service-common-2.6.yaml](./account-service-common-2.6.yaml) and
[email-service-common-2.6.yaml](./email-service-common-2.6.yaml).

## Define AsyncAPI 3.0.0 specs

Here's the same specs in AsyncAPI 3.0.0.

Define [account-service-3.0.yaml](./account-service-3.0.yaml) for the Account Service.
The key part is the `publishUserSignedUp` operation with `send` action that says
"This service sends messages to the `user/signedup` channel:

```yaml
operations:
  publishUserSignedUp:
    action: send
    channel:
      $ref: '#/channels/user~1signedup'
    messages:
      - $ref: '#/channels/user~1signedup/messages/publishUserSignedUp.message'
```

Define [email-service-3.0.yaml](./email-service-3.0.yaml) for the Email Service.
The key part is the `receiveUserSignedUp` operation with `receive` action that
says "This service receives messages on the `user/signedup` channel:

```yaml
operations:
  receiveUserSignedUp:
    action: receive
    channel:
      $ref: '#/channels/user~1signedup'
    messages:
      - $ref: '#/channels/user~1signedup/messages/receiveUserSignedUp.message'
```

## Validate AsyncAPI specs

Validate AsyncAPI file:

```sh
asyncapi validate account-service-2.6.yaml
asyncapi validate email-service-2.6.yaml

asyncapi validate account-service-3.0.yaml
asyncapi validate email-service-3.0.yaml
```

## Generate code from the AsyncAPI specs

Generate code from the AsyncAPI spec:

```sh
asyncapi generate fromTemplate email-service-2.6.yaml @asyncapi/nodejs-template -o email-service-2.6 -p server=test
```

Start the generated application:

```sh
node test-email-service-2.6.js

SUB  Subscribed to user/signedup
Email Service 1.0.0 is ready! 
```

Send message to the generated application:

```sh
mqtt pub -t 'user/signedup' -h 'test.mosquitto.org' -m '{"displayName": "Mete", "email": "info@atamel.com"}'
```

You should see the email service receive the message:

```sh
← user/signedup was received:
{ displayName: 'Mete', email: 'info@atamel.com' }
```

If you feel adventurous, you can try to generate more code for other languages,
for example Java spring:

```sh
asyncapi generate fromTemplate email-service.yaml @asyncapi/java-spring-template -o email-service-spring
```
