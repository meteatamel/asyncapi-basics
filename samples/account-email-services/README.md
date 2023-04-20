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

## Define AsyncAPI specs

Define [account-service.yaml](./account-service.yaml) for the Account Service.
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

Define [email-service.yaml](./email-service.yaml) for the Email Service.
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

Note: Since most of [account-service.yaml](./account-service.yaml) and
[email-service.yaml](./email-service.yaml) are the same, you can instead created
a [common.yaml](./common.yaml) to encapsulate the common config and simply refer
to hem from [account-service-common.yaml](./account-service-common.yaml) and
[email-service-common.yaml](./email-service-common.yaml).

## Validate AsyncAPI specs

Validate AsyncAPI file:

```sh
asyncapi validate account-service.yaml
asyncapi validate email-service.yaml
```

## Generate code from the AsyncAPI specs

Generate code from the AsyncAPI spec:

```sh
asyncapi generate fromTemplate email-service.yaml @asyncapi/nodejs-template -o email-service -p server=test
```

Start the generated application:

```sh
cd email-service
npm install
npm start

> email-service@1.0.0 start
> node src/api/index.js

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
